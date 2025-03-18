package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] t = new int[N + 1];
        int[] p = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int currentMax = 0;
        int[] dp = new int[N+2];
        for (int i = 1; i <= N; i++) {
            currentMax = Math.max(currentMax, dp[i]);
            if (i + t[i] < N + 2) {
                dp[i + t[i]] = Math.max(dp[i + t[i]], currentMax + p[i]);
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
     }
}
