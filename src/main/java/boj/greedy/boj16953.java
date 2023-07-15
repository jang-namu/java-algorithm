//A → B
package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj16953 {
    public static void main(String[] args) throws IOException  {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int cnt = 1;
        while (a < b) {
            if (b % 2 == 1) {
                if (b % 10 != 1) {
                    System.out.println(-1);
                    return;
                } else {
                    b -= 1;
                    b /= 10;
                }
            } else {
                b /= 2;
            }
            cnt++;
        }
        if (a != b)
            System.out.println(-1);
        else
            System.out.println(cnt);

    }
}
