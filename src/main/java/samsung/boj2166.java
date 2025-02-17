package samsung;

// https://www.acmicpc.net/problem/2166 다각형의 면적

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
첫 시도; 헤른의 공식 사용 -> 세 변의 길이를 알 때 삼각형의 넓이를 구할 수 있음
=> but, 삼각형의 방향을 알 수 없어 주어지는 다각형이 오목 다각형인 경우에는 틀림

신발끈 공식을 사용하자. -> 오목/볼록 다각형의 넓이를 계산하는 공식
 */
public class boj2166 {
    private static long[][] points;
    private static int N;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(String.format("%.1f", shoelace(points)));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new long[N+1][2];
        StringTokenizer st;
        for (int i=0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Long.parseLong(st.nextToken());
            points[i][1] = Long.parseLong(st.nextToken());
        }
        points[N] = points[0];
    }

    private static double shoelace(long[][] points) {
        long red = 0;
        long blue = 0;
        for (int i=0; i < points.length-1; i++) {
            red += points[i][0] * points[i+1][1];
            blue += points[i][1] * points[i+1][0];
        }
        return Math.abs(red - blue) / 2.0;
    }
}
