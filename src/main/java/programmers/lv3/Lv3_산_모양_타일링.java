package programmers.lv3;

// https://1eaf.site/cote/programmers_258705/
/*
답지.
유형: DP
해설: 범위를 오른쪽으로 한 칸씩 늘려가며 DP 수행
 */
public class Lv3_산_모양_타일링 {
    public int solution(int n, int[] tops) {
        int[] dp = new int[2*n+1];
        dp[0] = 1;

        if (n == 1) {
            return 1;
        }

        if (tops[0] == 0) {
            dp[1] = 2;
        } else {
            dp[1] = 3;
        }

        for (int i = 2; i < 2*n + 1; i++) {
            if (i % 2 == 1 && tops[(i - 1) / 2] == 1) {
                dp[i] = (2 * dp[i-1] + dp[i-2]) % 10007;
            } else {
                dp[i] = (dp[i-1] + dp[i-2]) % 10007;
            }
        }

        return dp[2*n];
    }
}