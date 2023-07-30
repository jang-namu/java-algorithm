package programmers.lv2;

import java.util.*;


public class Lv2_우박수열_정적분 {
}


class Solutionqwekl230124 {
    public double[] solution(int k, int[][] ranges) {
        var answer = new ArrayList<Double>();

        var sequence = new ArrayList<Integer>();
        sequence.add(k);
        while (k != 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
            sequence.add(k);
        }

        int[] sequenceArr = sequence.stream().mapToInt(Integer::intValue).toArray();
        double[] dp = new double[sequenceArr.length];   // 각 구간(x축 길이가 1인)의 넓이를 dp 배열에 기록
        Arrays.fill(dp, 0.0);

        for (int[] range : ranges) {
            int start = range[0];
            int end = sequenceArr.length - 1 + range[1];

            // 구간을 벗어나거나 start > end인 경우 -1
            if ((start > end) || (start < 0) || (end > sequenceArr.length)) {
                answer.add(-1.0);
                continue;
            }

            double space = 0.0;
            for (int idx = start; idx < end; idx++) {
                if (dp[idx] > 0) {  // 이전에 계산한 결과가 있으면 재사용
                    space += dp[idx];
                    continue;
                }
                // sequenceArr은 int이므로 double로 나눠준다.
                dp[idx] = (sequenceArr[idx] + sequenceArr[idx + 1]) / 2.0;
                space += dp[idx];

            }
            answer.add(space);
        }


        return answer.stream().mapToDouble(Double::doubleValue).toArray();
    }
}