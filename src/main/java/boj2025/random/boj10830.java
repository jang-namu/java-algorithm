/*
25.01.01 boj10830 행렬 제곱
행렬 A * B * C = 행렬 A * (B * C) 규칙을 활용한다.
틀린이유: 각 행렬 element를 1000으로 나눈 나머지를 구해야하는데, B=1인 경우 나머지 값 계산을 고려하지 않음
ex)
    1 1
    1000
=> 위 입력은 0이 나와야함
 */

package boj2025.random;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class boj10830 {
    private static final int ROUND = 1000;

    private static Map<Long, int[][]> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] head = br.readLine().split(" ");
        int N = Integer.parseInt(head[0]);
        long B = Long.parseLong(head[1]);

        int[][] matrix = new int[N][N];
        for (int i=0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j=0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }
        int[][] result = powMatrixRound(matrix, B);

        for (int[] rows : result) {
            for (int ele : rows) {
                System.out.print(ele % 1000 + " ");
            }
            System.out.println();
        }
    }

    private static int[][] powMatrixRound(int[][] matrix, long pow) {
        if (pow == 1) return matrix;
        if (cache.containsKey(pow)) return cache.get(pow);

        long a, b;

        if (pow % 2 == 0) {
            a =  pow / 2;
            b =  pow / 2;
        } else {
            a =  pow / 2 + 1;
            b =  pow / 2;
        }

        int[][] matrixA = powMatrixRound(matrix, a);
        int[][] matrixB = powMatrixRound(matrix, b);

        return multiplyMatrix(matrixA, matrixB, pow);
    }

    private static int[][] multiplyMatrix(int[][] a, int[][] b, long pow) {
        if (cache.containsKey(pow)) return cache.get(pow);
        int[][] mul = multiplyMatrixHelper(a, b);

        cache.put(pow, mul);
        return mul;
    }

    private static int[][] multiplyMatrixHelper(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int ele = 0;
                for (int k = 0; k < n; k++) {
                    ele += a[i][k] * b[k][j];
                }
                ele %= ROUND;
                result[i][j] = ele;
            }
        }
        return result;
    }
}
