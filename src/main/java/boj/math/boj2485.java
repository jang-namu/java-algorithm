// 2485 가로수
package boj.math;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    주어지는 가로수 사이 거리를 측정해 새 배열을 만들고
    새 배열의 수들의 최대 공약수를 구한다.
 */
public class boj2485 {
    /*
        gcd 메서드는 유클리드 호제법으로 최대공약수를 구한다. 한쪽이 0이 될 때까지 modulo 연산을 이용한다.
     */
    public static long gcd(long a, long b) {
        while (a > 0 && b > 0) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }

        if (a == 0) return b;
        else return a;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] trees = new long[n];
        for (int i=0; i < trees.length; i++) {
            trees[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(trees);

        long[] gaps = new long[n-1];
        for (int i=0; i < gaps.length; i++) {
            gaps[i] = trees[i+1] - trees[i];
        }

        long gcd = gaps[0];
        for (int i=1; i < gaps.length; i++) {
            gcd = gcd(gcd, gaps[i]);
        }

        /*
            Gap과 가로수 설치 거리를 알면, 현재 나무 사이에 몇개를 더 추가해야하는지 구할 수 있다.
         */
        int answer= 0;
        for (long gap : gaps) {
            answer += gap / gcd - 1;
        }

        System.out.println(answer);
    }
}
