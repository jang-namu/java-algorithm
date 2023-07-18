// 2156 포도주 시식
/*
    ++ 중요!! dp[2]와 전체 dp에 dp[-1]을 같이 비교해서 넣어야한다.

 */
package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2156 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] grapes = new int[n];
        for (int i=0; i < n; i++) {
            grapes[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 0);

        if (grapes.length == 1) {
            System.out.println(grapes[0]);
            return;
        }
        if (grapes.length == 2) {
            System.out.println(grapes[0] + grapes[1]);
            return;
        }

        dp[0] = grapes[0];
        dp[1] = grapes[0] + grapes[1];
        dp[2] = Math.max(Math.max(grapes[1], grapes[0]) + grapes[2], dp[1]);
        for (int i=3; i < n; i++) {
            dp[i] = Math.max(Math.max(dp[i-2], dp[i-3] + grapes[i-1]) + grapes[i], dp[i-1]);
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
