package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5373 큐빙
/*
  B   |    3    |   G
L U R |  4 0 5  | O W B
  F   |    2    |   R
  D   |    1    |   Y
 */
public class boj5373 {
    private static int T;
    private static char[][][] cube;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            solve(br);
        }
        System.out.println(sb);
    }

    public static void init() {
        cube = new char[9][3][3];
        paint(0, 'w');
        paint(1, 'y');
        paint(2, 'r');
        paint(3, 'o');
        paint(4, 'g');
        paint(5, 'b');
    }

    private static void paint(int idx, char color) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[idx][i][j] = color;
            }
        }
    }

    public static void solve(BufferedReader br) throws IOException {
        init();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            turn(token);
        }
        printU();
    }

    private static void printU() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(cube[0][i][j]);
            }
            sb.append("\n");
        }
    }

    private static void turn(String token) {
        if (token.charAt(1) == '-') {
            for (int i = 0; i < 3; i++) {
                turnHelper(token.charAt(0));
            }
        } else {
            turnHelper(token.charAt(0));
        }
    }

// JDK 11은 안됨
//    private static void turnHelper(char op) {
//        switch (op) {
//            case 'U' -> URight();
//            case 'D' -> DRight();
//            case 'F' -> FRight();
//            case 'B' -> BRight();
//            case 'L' -> LRight();
//            case 'R' -> RRight();
//        }
//    }
    private static void turnHelper(char op) {
        switch (op) {
            case 'U':
                URight();
                break;
            case 'D':
                DRight();
                break;
            case 'F':
                FRight();
                break;
            case 'B':
                BRight();
                break;
            case 'L':
                LRight();
                break;
            case 'R':
                RRight();
                break;
        }
    }

    public static void turnRight(char[][] face) {
        char tmp = face[0][0];
        face[0][0] = face[2][0];
        face[2][0] = face[2][2];
        face[2][2] = face[0][2];
        face[0][2] = tmp;
        tmp = face[0][1];
        face[0][1] = face[1][0];
        face[1][0] = face[2][1];
        face[2][1] = face[1][2];
        face[1][2] = tmp;
    }

    public static void URight() {
        turnRight(cube[0]);
        char tmp1 = cube[3][2][0];
        char tmp2 = cube[3][2][1];
        char tmp3 = cube[3][2][2];

        cube[3][2][0] = cube[4][2][2];
        cube[3][2][1] = cube[4][1][2];
        cube[3][2][2] = cube[4][0][2];

        cube[4][0][2] = cube[2][0][0];
        cube[4][1][2] = cube[2][0][1];
        cube[4][2][2] = cube[2][0][2];

        cube[2][0][0] = cube[5][2][0];
        cube[2][0][1] = cube[5][1][0];
        cube[2][0][2] = cube[5][0][0];

        cube[5][0][0] = tmp1;
        cube[5][1][0] = tmp2;
        cube[5][2][0] = tmp3;
    }

    public static void DRight() {
        turnRight(cube[1]);
        char tmp1 = cube[3][0][0];
        char tmp2 = cube[3][0][1];
        char tmp3 = cube[3][0][2];

        cube[3][0][0] = cube[5][0][2];
        cube[3][0][1] = cube[5][1][2];
        cube[3][0][2] = cube[5][2][2];

        cube[5][0][2] = cube[2][2][2];
        cube[5][1][2] = cube[2][2][1];
        cube[5][2][2] = cube[2][2][0];

        cube[2][2][2] = cube[4][2][0];
        cube[2][2][1] = cube[4][1][0];
        cube[2][2][0] = cube[4][0][0];

        cube[4][2][0] = tmp1;
        cube[4][1][0] = tmp2;
        cube[4][0][0] = tmp3;
    }

    public static void FRight() {
        turnRight(cube[2]);
        char tmp1 = cube[0][2][0];
        char tmp2 = cube[0][2][1];
        char tmp3 = cube[0][2][2];

        cube[0][2][0] = cube[4][2][0];
        cube[0][2][1] = cube[4][2][1];
        cube[0][2][2] = cube[4][2][2];

        cube[4][2][0] = cube[1][0][2];
        cube[4][2][1] = cube[1][0][1];
        cube[4][2][2] = cube[1][0][0];

        cube[1][0][2] = cube[5][2][0];
        cube[1][0][1] = cube[5][2][1];
        cube[1][0][0] = cube[5][2][2];

        cube[5][2][0] = tmp1;
        cube[5][2][1] = tmp2;
        cube[5][2][2] = tmp3;
    }

    public static void BRight() {
        turnRight(cube[3]);
        char tmp1 = cube[1][2][0];
        char tmp2 = cube[1][2][1];
        char tmp3 = cube[1][2][2];

        cube[1][2][0] = cube[4][0][2];
        cube[1][2][1] = cube[4][0][1];
        cube[1][2][2] = cube[4][0][0];

        cube[4][0][2] = cube[0][0][2];
        cube[4][0][1] = cube[0][0][1];
        cube[4][0][0] = cube[0][0][0];

        cube[0][0][2] = cube[5][0][2];
        cube[0][0][1] = cube[5][0][1];
        cube[0][0][0] = cube[5][0][0];

        cube[5][0][2] = tmp1;
        cube[5][0][1] = tmp2;
        cube[5][0][0] = tmp3;
    }

    public static void LRight() {
        turnRight(cube[4]);
        char tmp1 = cube[3][0][0];
        char tmp2 = cube[3][1][0];
        char tmp3 = cube[3][2][0];

        cube[3][0][0] = cube[1][0][0];
        cube[3][1][0] = cube[1][1][0];
        cube[3][2][0] = cube[1][2][0];

        cube[1][0][0] = cube[2][0][0];
        cube[1][1][0] = cube[2][1][0];
        cube[1][2][0] = cube[2][2][0];

        cube[2][0][0] = cube[0][0][0];
        cube[2][1][0] = cube[0][1][0];
        cube[2][2][0] = cube[0][2][0];

        cube[0][0][0] = tmp1;
        cube[0][1][0] = tmp2;
        cube[0][2][0] = tmp3;
    }

    public static void RRight() {
        turnRight(cube[5]);
        char tmp1 = cube[3][2][2];
        char tmp2 = cube[3][1][2];
        char tmp3 = cube[3][0][2];

        cube[3][2][2] = cube[0][2][2];
        cube[3][1][2] = cube[0][1][2];
        cube[3][0][2] = cube[0][0][2];

        cube[0][2][2] = cube[2][2][2];
        cube[0][1][2] = cube[2][1][2];
        cube[0][0][2] = cube[2][0][2];

        cube[2][2][2] = cube[1][2][2];
        cube[2][1][2] = cube[1][1][2];
        cube[2][0][2] = cube[1][0][2];

        cube[1][2][2] = tmp1;
        cube[1][1][2] = tmp2;
        cube[1][0][2] = tmp3;
    }

}
