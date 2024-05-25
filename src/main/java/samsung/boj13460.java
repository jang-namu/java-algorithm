// 구슬 탈출 2

/*
주의 1. 구슬이 더 이상 움직일떄까지 기울이는 것은. 마지막 빨간 구슬이 빠져나간 이후로도 유효하다.
#.O..RB# 이면 => 왼쪽으로 기울여서 R이 빠져나가고 B가 빠져나감 => 실패 (-1) 처리가 필요하다.

주의 2. 구슬 R, B를 지도상에 계속 새로 표현하거나, 비교 시 서로의 위치로 비교해야함.
 */
/*
// 11번만에 탈출하는 예제

8 8
########
#BR.#.O#
###.#..#
#...#..#
#.###..#
#..#..##
##...#.#
########

정답 = -1
 */
package samsung;

import java.util.StringTokenizer;
import java.io.*;
import java.util.*;

public class boj13460 {
    public static int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static char[][] board;
    public static LinkedList<List<Integer>> queue = new LinkedList<>();
    static int rr = -1, rc = -1;
    static int br = -1, bc = -1;

    public static int bfs() {
        queue.add(List.of(rr, rc, br, bc));

        int turn = 0;

        while (turn < 10) { // while (turn <= 10) 으로 써서 계속 틀렸다. => 11번째까지 허용되게 된다.
            turn += 1;

            int length = queue.size();
            for (int i = 0; i < length; i++) {
                List<Integer> position = queue.poll();
                int orr = position.get(0);
                int orc = position.get(1);
                int obr = position.get(2);
                int obc = position.get(3);

                for (int[] move: moves) {
                    int trr = orr; int trc = orc;
                    int tbr = obr; int tbc = obc;

                    boolean isOut = false;
                    int mr = move[0]; int mc = move[1];

                    boolean red = false; boolean blue = false;

                    while (true) {
                        //Red
                        int nrr = mr + trr; int nrc = mc + trc;

                        if (board[nrr][nrc] == '#') {
                            red = true;
                        }
                        else if (board[nrr][nrc] == 'O') {
                            while (true) {
                                int nbr = tbr + mr; int nbc = tbc + mc;
                                if (board[nbr][nbc] == '#') {
                                    return turn;
                                } else if (board[nbr][nbc] == 'O') {
                                    isOut = true;
                                    red = true;
                                    blue = true;
                                    break;
                                }
                                tbr = nbr; tbc = nbc;
                            }
                        }
                        else {
                            if (nrr == tbr && nrc == tbc) {  // 앞애 Blue가 가로막고 있는 경우 => Blue 먼저 처리
                                int nbr = tbr + mr; int nbc = tbc + mc;
                                if (board[nbr][nbc] == '#') {
                                    break;  // => blue도 막힘 => 이동불가
                                } else if (board[nbr][nbc] == 'O') {
                                    isOut = true;
                                    break;
                                }
                                tbr = nbr; tbc = nbc;
                                trr = nrr; trc = nrc;
                                continue;
                            }
                            // 이동
                            trr = nrr; trc = nrc;
                        }
                        if (red && blue) break;


                        //Blue
                        int nbr = tbr + mr; int nbc = tbc + mc;
                        if (board[nbr][nbc] == '#') {
                            blue = true;
                        } else if (board[nbr][nbc] == 'O') {
                            isOut = true;
                            break;
                        } else {
                            if (nbr == trr && nbc == trc) {
                                if (red) {
                                    break;
                                }
                                continue;
                            }
                            tbr = nbr; tbc = nbc;
                        }

                        if (red && blue) break;
                    }

                    if (isOut) continue;
                    queue.add(List.of(trr, trc, tbr, tbc));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(sbr.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < board.length; i++) {
            char[] line = sbr.readLine().toCharArray();
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = line[j];
                if (line[j] == 'R') {
                    rr = i; rc = j;
                }
                if (line[j] == 'B') {
                    br = i; bc = j;
                }
            }
        }

        System.out.println(bfs());
    }
}
