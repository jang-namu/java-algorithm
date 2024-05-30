// 퇴사

package samsung;

import java.io.*;
import java.util.*;


public class boj14501 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] reward = new int[N + 1];
        int[][] schedules = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            schedules[i][0] = Integer.parseInt(st.nextToken());
            schedules[i][1] = Integer.parseInt(st.nextToken());
        }

        int currentMax = 0;
        for (int i = 0; i < N; i++) {
            currentMax = Math.max(currentMax, reward[i]);
            if (i + schedules[i][0] > N) continue;
            if (reward[i + schedules[i][0]] < currentMax + schedules[i][1]) {
                reward[i + schedules[i][0]] = currentMax + schedules[i][1];
            }
        }
        currentMax = Math.max(currentMax, reward[N]);
        System.out.println(currentMax);
    }
}
