package samsung;

// https://www.acmicpc.net/problem/16236 아기 상어

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj16236 {
    private static final int[][] MOVES = {{-1,0}, {0, -1}, {0, 1}, {1, 0}};
    private static int N;
    private static int[][] sea;
    private static Shark shark;
    private static boolean terminate;
    private static int count;

    private static class Shark {
        int r;
        int c;
        int numberOfEat;
        int age;

        public Shark(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        public void eat(int nr, int nc) {
            this.r = nr;
            this.c = nc;
            numberOfEat++;
            if (numberOfEat == age) {
                age++;
                numberOfEat = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        while (true) {
            bfs(shark.r, shark.c);
            if (terminate) {
                break;
            }
        }
        System.out.println(count);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sea = new int[N][N];
        for (int i=0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j < N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] == 9) {
                    shark = new Shark(i, j, 2);
                    sea[i][j] = 0; // 상어 위치는 계속 변하므로 0
                }
            }
        }
    }

    private static void bfs(int r, int c) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        List<int[]> position = new ArrayList<>();
        position.add(new int[]{r, c});
        boolean[][] visited = new boolean[N][N];
        visited[r][c] = true;

        int distance = 0;
        while (!position.isEmpty()) {
            List<int[]> next = new ArrayList<>();
            distance++;
            for (int[] pos: position) {
                for (int[] move : MOVES) {
                    int nr = pos[0] + move[0];
                    int nc = pos[1] + move[1];
                    if (isInArray(nr, nc) && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        // 상어와 같거나, 물고기가 없는 경우
                        if (shark.age == sea[nr][nc] || sea[nr][nc] == 0) {
                            next.add(new int[]{nr, nc});
                            continue;
                        }
                        // 상어보다 크면
                        if (shark.age < sea[nr][nc]) {
                            continue;
                        }
                        // 물고기가 있고, 상어보다 작으면
                        pq.add(new int[]{nr, nc});
                    }
                }
            }
            if (!pq.isEmpty()) {
                int[] pos = pq.peek();
                shark.eat(pos[0], pos[1]);
                sea[pos[0]][pos[1]] = 0;
                count += distance;
                return;
            }
            position = next;
        }
        terminate = true;
    }

    private static boolean isInArray(int nr, int nc) {
        return 0 <= nr && nr < N && 0 <= nc && nc < N;
    }


}
