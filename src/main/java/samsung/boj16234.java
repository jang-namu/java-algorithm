package samsung;

// https://www.acmicpc.net/problem/16234 인구 이동

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class boj16234 {
    private static int round;
    private static int[][] map;
    private static int N;
    private static int L;
    private static int R;

    private static final int[][] MOVES = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        init();
        simulate();
        System.out.println(round);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void simulate() {
        while (true) {
            boolean flag = false;

            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    int numberOfAlly = dfs(visited, i, j);
                    if (numberOfAlly > 1) {
                        flag = true;
                    }
                }
            }

            if (!flag) {
                break;
            }
            round++;
        }
    }

    private static int dfs(boolean[][] visited, int r, int c) {
        List<int[]> alliance = new ArrayList<>();
        int sum = map[r][c];
        int count = 1;
        visited[r][c] = true;
        alliance.add(new int[]{r, c});

        Deque<int[]> stack = new ArrayDeque<>();
        stack.add(new int[]{r, c});

        while (!stack.isEmpty()) {
            int[] pos = stack.pop();

            for (int[] move : MOVES) {
                int nr = pos[0] + move[0];
                int nc = pos[1] + move[1];
                if (isInArrayIndex(nr, nc) && !visited[nr][nc] && gateIsOpen(map[pos[0]][pos[1]], map[nr][nc])) {
                    visited[nr][nc] = true;
                    count++;
                    sum += map[nr][nc];

                    int[] nextPos = new int[]{nr, nc};
                    stack.add(nextPos);
                    alliance.add(nextPos);
                }
            }
        }
        fillMean(alliance, sum / count);
        return count;
    }

    private static boolean gateIsOpen(int origin, int target) {
        int tmp = Math.abs(origin - target);
        return (L <= tmp) && (tmp <= R);
    }

    private static boolean isInArrayIndex(int r, int c) {
        return (0 <= r) && (r < N) && (0 <= c) && (c < N);
    }

    private static void fillMean(List<int[]> alliance, int mean) {
        alliance.forEach(arr -> map[arr[0]][arr[1]] = mean);
    }
}
