package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
dp: 현재 집을 R:0, G:1, B:2로 칠했을 때 (현재까지 비용합) 최솟값

첫 집을 기준으로 R, G, B 각각 칠하고 시작하는 경우를 따로 구함
첫 집이 R이면 - 끝 집 R은 불가능. 따라서, min(끝 집 G,  끝 집 B)
첫 집이 G이면 - min(끝 집 R,  끝 집 B)
첫 집이 B이면 - min(끝 집 R,  끝 집 G)

6개 경우의 수 중 최솟값이 정답

 */
public class boj17404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N][3]; // 0: R, 1: G, 2: B
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        // dimension1 => 0번째 집을 각각 R, G, B로 칠할 때
        // dimension3 => 현재 집 0: R, 1: G, 2: B일로 칠하는 최솟값
        int[][][] dps = new int[3][N][3];

        for (int d1 = 0; d1 < 3; d1++) {
            int[][] dp = dps[d1];

            dp[0][d1] = cost[0][d1];
            for (int color = 0; color < 3; color++) {
                if (color == d1) {
                    dp[1][color] = Integer.MAX_VALUE;
                    continue;
                }
                dp[1][color] = dp[0][d1] + cost[1][color];
            }

            for (int i = 2; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
            }
        }

        System.out.println(Math.min(
                Math.min(Math.min(dps[0][N - 1][1], dps[0][N - 1][2]), Math.min(dps[1][N - 1][0], dps[1][N - 1][2])),
                Math.min(dps[2][N - 1][0], dps[2][N - 1][1])));
    }
}
