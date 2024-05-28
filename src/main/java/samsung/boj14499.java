// 주사위 굴리기

/*
  2
4 1 3
  5
  6
 */
package samsung;

import java.io.*;
import java.util.*;

// 위(idx=0), 밑(idx=5)
public class boj14499 {
    static int n, m, x, y, k;
    static int[][] map;
    static int[] dice;
    static int[] command;

    public static void move(int command) {
        switch (command) {
            case 1:
                if (y + 1 >= m) {
                    return;
                }
                dice = right(dice);
                y += 1;
                rule(dice, x, y);
                break;
            case 2:
                if (y - 1 < 0) {
                    return;
                }
                y -= 1;
                dice = left(dice);
                rule(dice, x, y);
                break;
            case 3:
                if (x - 1 < 0) {
                    return;
                }
                x -= 1;
                dice = up(dice);
                rule(dice, x, y);
                break;
            case 4:
                if (x + 1 >= n) {
                    return;
                }
                x += 1;
                dice = down(dice);
                rule(dice, x, y);
                break;
        };

        System.out.println(dice[0]);
    }

    public static void rule(int[] dice, int r, int c) {
        if (map[r][c] == 0) {
            map[r][c] = dice[5];
        } else {
            dice[5] = map[r][c];
            map[r][c] = 0;
        }
    }

    public static int[] up(int[] dice) {
        int[] alternate = new int[dice.length];
        alternate[0] = dice[4];
        alternate[1] = dice[0];
        alternate[2] = dice[2];
        alternate[3] = dice[3];
        alternate[4] = dice[5];
        alternate[5] = dice[1];
        return alternate;
    }

    public static int[] down(int[] dice) {
        int[] alternate = new int[dice.length];
        alternate[0] = dice[1];
        alternate[1] = dice[5];
        alternate[2] = dice[2];
        alternate[3] = dice[3];
        alternate[4] = dice[0];
        alternate[5] = dice[4];
        return alternate;
    }

    public static int[] left(int[] dice) {
        int[] alternate = new int[dice.length];
        alternate[0] = dice[2];
        alternate[1] = dice[1];
        alternate[2] = dice[5];
        alternate[3] = dice[0];
        alternate[4] = dice[4];
        alternate[5] = dice[3];
        return alternate;
    }

    public static int[] right(int[] dice) {
        int[] alternate = new int[dice.length];
        alternate[0] = dice[3];
        alternate[1] = dice[1];
        alternate[2] = dice[0];
        alternate[3] = dice[5];
        alternate[4] = dice[4];
        alternate[5] = dice[2];
        return alternate;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new int[]{0, 0, 0, 0, 0, 0};

        command = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        for (int c : command) {
            move(c);
        }

    }
}
