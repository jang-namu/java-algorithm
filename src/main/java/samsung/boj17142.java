package samsung;

// https://www.acmicpc.net/problem/17142

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class boj17142 {
    private static final int[][] MOVES = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    // 0: 빈곳, 1: 벽, 2: 비활성 바이러스, 3: 활성 바이러스
    private static int[][] lab;
    private static int N;
    private static int M;
    private static List<int[]> viruses = new ArrayList<>();
    private static int numOfViruses;
    private static int empty;

    private static int minTime = 2500;

    public static void main(String[] args) throws IOException {
        init();
        if (isFinished(empty)) {
            System.out.println(0);
            return;
        }

        List<int[]> activate = new LinkedList<>();

        combination(activate, 0, 0);

        if (minTime == 2500) { // 불가능
            minTime = -1;
        }
        System.out.println(minTime);
    }

    private static void combination(List<int[]> activate, int size, int start) {
        if (size == M) {
            // 원본상태 복사
            int[][] copy = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    copy[i][j] = lab[i][j];
                }
            }

            infect(copy, empty, 0, activate);
            return;
        }

        for (int i = start; i < numOfViruses; i++) {
            activate.add(viruses.get(i));
            combination(activate, size + 1, i + 1);
            activate.remove(activate.size() - 1);
        }
    }

    private static void infect(int[][] lab, int remain, int depth, List<int[]> previous) {
        if (minTime <= depth) {
            return;
        }
        if (isFinished(remain)) {
            minTime = depth;
            return;
        }

        int active = 0;
        List<int[]> activate = new ArrayList<>();
        for (int[] pos : previous) {
            for (int[] move : MOVES) {
                int nr = pos[0] + move[0];
                int nc = pos[1] + move[1];
                if (isOutOfIndex(nr, nc)) {
                    continue;
                }
                if (lab[nr][nc] == 0) {
                    lab[nr][nc] = 3;
                    active += 1;
                    activate.add(new int[]{nr, nc});
                } else if (lab[nr][nc] == 2) {
                    lab[nr][nc] = 3;
                    activate.add(new int[]{nr, nc});
                }
            }
        }
        infect(lab, remain - active, depth + 1, activate);
    }

    private static boolean isOutOfIndex(int nr, int nc) {
        return nr < 0 || nr >= N || nc < 0 || nc >= N;
    }

    private static boolean isFinished(int remain) {
        return remain == 0;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][N];

        empty = N * N;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int token = Integer.parseInt(st.nextToken());
                lab[i][j] = token;

                if (token == 2) {
                    viruses.add(new int[]{i, j});
                    numOfViruses += 1;
                }
                if (token == 1) {
                    empty -= 1;
                }
            }
        }
        empty -= numOfViruses;
    }
}
