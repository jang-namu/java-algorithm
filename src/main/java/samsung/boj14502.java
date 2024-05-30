// 연구소

package samsung;

import java.io.*;
import java.util.*;

public class boj14502 {
    static int N, M;
    static int[][] map;
    static List<int[]> viruses;
    static int init_safe, unsafe, result;
    static int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static void dfsHelper(int[] virus, boolean[][] visited) {
        Stack<int[]> stack = new Stack<>();
        stack.push(virus);

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            for (int[] move : moves) {
                int dx = cur[0] + move[0];
                int dy = cur[1] + move[1];
                if (dx < 0 || dy < 0 || dx >= N || dy >= M) continue;
                if (!visited[dx][dy] && map[dx][dy] == 0) {
                    visited[dx][dy] = true;
                    stack.push(new int[]{dx,dy});
                    unsafe += 1;
                }
            }
        }
    }


    public static void dfs() {
        boolean[][] visited = new boolean[N][M];
        for (int[] virus : viruses) {
            dfsHelper(virus, visited);
        }
        result = Math.max(result, init_safe - unsafe);
        unsafe = 0;
    }


    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(result);
    }

    private static void solution() {
        int size = N * M;
        for (int i = 0; i < size; i++) {
            if (map[i / M][i % M] != 0) continue;
            map[i / M][i % M] = 1;
            for (int j = i+1; j < size; j++) {
                if (map[j / M][j % M] != 0) continue;
                map[j / M][j % M] = 1;
                for (int k = j; k < size; k++) {
                    if (map[k / M][k % M] != 0) continue;
                    map[k / M][k % M] = 1;
                    dfs();
                    map[k / M][k % M] = 0;
                }
                map[j / M][j % M] = 0;
            }
            map[i / M][i % M] = 0;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        viruses = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) init_safe += 1;
                if (map[i][j] == 2) {
                    viruses.add(new int[]{i, j});
                }
            }
        }
        init_safe -= 3; // 벽 3개 설치
    }
}
