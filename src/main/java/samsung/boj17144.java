package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://github.com/brave-people/Dev-Event 미세먼지 안녕!
/*
확산이 '동시에' 일어난다는 것이 포인트
-> 순환하면서 더해주면 안됨. -> 순환 순서에 따라 다음 확산에 영향을 줄 수 있음
=> spreadMap을 만들어서 확산되는 양만 저장 후, 마지막에 한 번에 더해줌
 */
public class boj17144 {
    private static final int[][] MOVES = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int R;
    private static int C;
    private static int T;
    private static int[][] room;
    private static int[] airPurifier;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < T; i++) {
            whileSecond();
        }
        int result = sum();
        System.out.println(result);
    }

    private static int sum() {
        int result = 2;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                result += room[i][j];
            }
        }
        return result;
    }

    private static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        room = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1 && airPurifier == null) {
                    airPurifier = new int[2];
                    airPurifier[0] = i; // 공기청정기의 윗부분 r 좌표
                    airPurifier[1] = i + 1;
                }
            }
        }
    }

    private static void whileSecond() {
        spread();
        blow();
    }

    private static void spread() {
        int[][] spreadMap = new int[R][C]; // 확산은 미세먼지가 있는 모든 칸에서 '동시에' 일어난다
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) {
                    int amount = room[i][j] / 5;
                    int count = 0;
                    for (int[] move : MOVES) {
                        int nr = i + move[0];
                        int nc = j + move[1];
                        if (isSpreadable(nr, nc)) {
                            count++;
//                            room[nr][nc] += amount; // spreadMap을 사용하지 않는 방식에서는 루프 순환 순서에 따라 결과가 바뀜
                            spreadMap[nr][nc] += amount;
                        }
                    }
                    room[i][j] -= amount * count;
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                room[i][j] += spreadMap[i][j];
            }
        }
    }

    private static boolean isSpreadable(int r, int c) {
        return (0 <= r && r < R) && (0 <= c && c < C) && (room[r][c] != -1);
    }

    private static void blow() {
        // 윗부분 (거꾸로)
        upside();

        // 아랫부분
        int r = airPurifier[1];

        // up
        for (int i = r + 1; i < R - 1; i++) {  // 공기청정기에 들어가면 정화됨
            room[i][0] = room[i + 1][0];
        }

        // left
        for (int i = 0; i < C - 1; i++) {
            room[R - 1][i] = room[R - 1][i + 1];
        }

        // down
        for (int i = R - 1; i > r; i--) {
            room[i][C - 1] = room[i - 1][C - 1];
        }

        // right
        for (int i = C - 1; i > 1; i--) {
            room[r][i] = room[r][i - 1];
        }
        room[r][1] = 0;
    }

    private static void upside() {
        int r = airPurifier[0];

        // down
        for (int i = r - 1; i > 0; i--) { // 공기청정기에 들어가면 정화됨
            room[i][0] = room[i - 1][0];
        }
        // left
        for (int i = 0; i < C - 1; i++) {
            room[0][i] = room[0][i + 1];
        }
        // up
        for (int i = 0; i < r; i++) {
            room[i][C - 1] = room[i + 1][C - 1];
        }
        // right
        for (int i = C - 1; i > 1; i--) {
            room[r][i] = room[r][i - 1];
        }
        room[r][1] = 0;
    }
}
