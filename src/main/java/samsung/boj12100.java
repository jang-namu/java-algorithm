// 2048 (Easy)
package samsung;

import java.io.*;
import java.util.*;

import static java.lang.Math.max;

public class boj12100 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int[][] start;
    public static void init(int N) throws IOException {
        start = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                start[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static int dfs(int[][] board, int depth) {
//        print(board, depth);
        if (depth == 5) {
            int max = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] > max) {
                        max = board[i][j];
                    }
                }
            }
            return max;
        }
        int result = 0;

        int[][] tmp = copy(board);
        up(tmp);
        result = max(result, dfs(tmp, depth + 1));

        int[][] tmp2 = copy(board);
        down(tmp2);
        result = max(result, dfs(tmp2, depth + 1));

        int[][] tmp3 = copy(board);
        left(tmp3);
        result = max(result, dfs(tmp3, depth + 1));

        int[][] tmp4 = copy(board);
        right(tmp4);
        result = max(result, dfs(tmp4, depth + 1));

        return result;
    }

    public static int[][] copy(int[][] board) {
        int[][] tmp = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                tmp[i][j] = board[i][j];
            }
        }
        return tmp;
    }

    public static void sum(int[][] board, int sx, int sy, int dx, int dy) {
        board[sy][sx] += board[dy][dx];
        board[dy][dx] = 0;
    }

    public static void up(int[][] board) { // {-1, 0}
        boolean[][] check = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board.length; j++) {
                for (int d = 1; d < j + 1; d++) {
                    if (board[j - d][i] == 0) {
                        if (j == d) {
                            board[0][i] = board[j][i];
                            board[j][i] = 0;
                        }
                        continue;
                    } else if (board[j - d][i] == board[j][i]) {
                        if (!check[j-d][i] && !check[j][i]) {
                            sum(board, i, j, i, j-d);
                            check[j][i] = true;
                            if (j == d) {
                                board[0][i] = board[j][i];
                                board[j][i] = 0;
                                check[j - d][i] = check[j][i];
                                check[j][i] = false;
                            }
                            continue;
                        }
                    }

                    board[j - d + 1][i] = board[j][i];
                    if (j - d + 1 != j) {         // 병합된 자리에서 멈춘 경우 => true 유지해야..
                        board[j][i] = 0;
                        check[j - d + 1][i] = check[j][i];
                        check[j][i] = false;
                    }
                    break;
                }
//                print(board.length);

            }
        }
    }

    public static void down(int[][] board) { // {1, 0}
        boolean[][] check = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = board.length-2; j >= 0; j--) {
                for (int d = 1; d < board.length - j; d++) {
                    if (board[j + d][i] == 0) {
                        if (j + d == board.length-1) {
                            board[board.length-1][i] = board[j][i];
                            board[j][i] = 0;
                        }
                        continue;
                    } else if (board[j + d][i] == board[j][i]) {
                        if (!check[j+d][i] && !check[j][i]) {
                            sum(board, i, j, i, j+d);
                            check[j][i] = true;
                            if (j + d == board.length-1) {
                                board[board.length-1][i] = board[j][i];
                                board[j][i] = 0;
                                check[j + d][i] = check[j][i];
                                check[j][i] = false;
                            }
                            continue;
                        }
                    }

                    board[j + d - 1][i] = board[j][i];
                    if (j + d - 1 != j) {         // 병합된 자리에서 멈춘 경우 => true 유지해야..
                        board[j][i] = 0;
                        check[j + d - 1][i] = check[j][i];
                        check[j][i] = false;
                    }
                    break;
                }
//                print(board.length);
            }
        }
    }

    public static void left(int[][] board) { // {0, -1}
        boolean[][] check = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board.length; j++) {
                for (int d = 1; d < j + 1; d++) {
                    if (board[i][j - d] == 0) {
                        if (j == d) {
                            board[i][0] = board[i][j];
                            board[i][j] = 0;
                        }
                        continue;
                    } else if (board[i][j - d] == board[i][j]) {
                        if (!check[i][j-d] && !check[i][j]) {
                            sum(board, j, i, j-d, i);
                            check[i][j] = true;
                            if (j == d) {
                                board[i][0] = board[i][j];
                                board[i][j] = 0;
                                check[i][j - d] = check[i][j];
                                check[i][j] = false;
                            }
                            continue;
                        }
                    }

                    board[i][j - d + 1] = board[i][j];
                    if (j - d + 1 != j) {         // 병합된 자리에서 멈춘 경우 => true 유지해야..
                        board[i][j] = 0;
                        check[i][j - d + 1] = check[i][j];
                        check[i][j] = false;
                    }
                    break;
                }
//                print(board.length);
            }
        }
    }

    public static void right(int[][] board) { // {1, 0}
        boolean[][] check = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = board.length-2; j >= 0; j--) {
                for (int d = 1; d < board.length - j; d++) {
                    if (board[i][j + d] == 0) {
                        if (j + d == board.length-1) {
                            board[i][board.length-1] = board[i][j];
                            board[i][j] = 0;
                        }
                        continue;
                    } else if (board[i][j + d] == board[i][j]) {
                        if (!check[i][j+d] && !check[i][j]) {
                            sum(board, j, i, j+d, i);
                            check[i][j] = true;
                            if (j + d == board.length-1) {
                                board[i][board.length-1] = board[i][j];
                                board[i][j] = 0;
                                check[i][j + d] = check[i][j];
                                check[i][j] = false;
                            }
                            continue;
                        }
                    }

                    board[i][j + d - 1] = board[i][j];
                    if (j + d - 1 != j) {         // 병합된 자리에서 멈춘 경우 => true 유지해야..
                        board[i][j] = 0;
                        check[i][j + d - 1] = check[i][j];
                        check[i][j] = false;
                    }
                    break;
                }
//                print(board.length);
            }
        }
    }


    public static void print(int[][] board, int depth) {
        int N = board.length;
        System.out.println("Lv: " + depth);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        init(N);
        System.out.println(dfs(start, 0));

//        right();
//        print(board.length);
//
//        left();
//        print(board.length);
//
//        up();
//        print(board.length);
//
//        down();
//        print(board.length);

    }
}
