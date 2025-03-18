package programmers.lv3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    dp[N]: N개 사용해서 구할 수 있는 값

    i = k(N 미만 자연수), j = N - i라고 할 때
    dp[N]은 모든 가능한 (i, j) 쌍에 대해 사칙연산을 수행한 결과
    (-), (/) 연산은 순서에 따라 값이 달라지므로 두 가지 경우 모두 수행
 */
public class Lv3_N으로_표현 {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();
        for (int i=0; i < 8; i++) {
            dp.add(new HashSet<>());
        }

        int num = N;
        for (int i=0; i < 8; i++) {
            dp.get(i).add(num);
            num = num * 10 + N;
        }

        for (int i=2; i < 9; i++) {
            Set<Integer> comb = dp.get(i-1);

            for (int j=1; j <= i / 2; j++) {
                int k = i - j;

                for (Integer first : dp.get(j-1)) {
                    for (Integer second : dp.get(k-1)) {
                        comb.add(first + second);
                        comb.add(first - second);
                        comb.add(first * second);
                        if (second != 0)
                            comb.add(first / second);
                        // 반대로도 수행 (+, * 연산은 순서를 바꿔도 같음)
                        comb.add(second - first);
                        if (first != 0)
                            comb.add(second / first);
                    }
                }

            }
        }

        for (int i=0; i < 8; i++) {
            if (dp.get(i).contains(number)) {
                return i + 1;
            }
        }

        return -1;
    }
}