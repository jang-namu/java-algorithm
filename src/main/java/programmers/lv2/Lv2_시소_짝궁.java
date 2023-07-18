package programmers.lv2;
import java.util.stream.*;
import java.util.*;

public class Lv2_시소_짝궁 {
}



class Solution2312323 {
    public long solution(int[] weights) {
        long answer = 0;

        int[] dp = new int[2001];
        Arrays.fill(dp, 0);

        Arrays.sort(weights);

        var set = new HashSet<Integer>();

        for (int w : weights) {
            dp[w] += 1;
            set.add(w);
        }

        var it = set.iterator();
        while (it.hasNext()) {
            long cnt = dp[it.next()];       // cnt를 long으로 사용해야한다. 그렇지않으면, 바로 다음 라인에서 문제 발생.
            answer += cnt * (cnt-1) / 2;    // 우변을 먼저 평가할 때, 100000 * 99999으로 int범위를 벗어나고 쓰레기값이 더해진다.
        }                                   // answer이 long이어도 우변은 모두 int형으로 형변환되어 계산된다.

        for (int w : weights) {
            if ((w % 2 == 0) && (dp[w * 3 / 2] != 0)) {
                answer += dp[w * 3 / 2];
            }
            if (dp[w * 2] != 0) {
                answer += dp[w * 2];
            }
            if ((w % 3 == 0) && (dp[w * 4 / 3] != 0)) {
                answer += dp[w * 4 / 3];
            }
        }

        return answer;
    }
}