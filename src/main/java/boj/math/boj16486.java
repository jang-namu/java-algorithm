package boj.math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// 16486 운동장 한 바퀴
public class boj16486 {
    final static double PI = 3.141592;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d1 = Integer.parseInt(br.readLine());
        int d2 = Integer.parseInt(br.readLine());

        System.out.println(2 * d1 + 2 * d2 * PI);
    }

}
