package samsung;

// https://www.acmicpc.net/problem/17143 낚시왕

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj17143 {
    // 방향 상수 정의 (가독성 향상)
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int LEFT = 4;

    private static final int[][] MOVES = {{}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static final int[] REVERSE_MOVES = {0, DOWN, UP, LEFT, RIGHT}; // 상수 사용으로 가독성 향상

    private static int R;
    private static int C;
    private static int M;
    private static int fishermanPosition;
    private static int totalCaughtSize;
    private static Shark[][] river;
    private static List<Shark> sharks = new ArrayList<>();

    private static class Shark {
        int r;
        int c;
        int speed;
        int direction;
        int size;

        public Shark(int r, int c, int speed, int direction, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        simulate();
        System.out.println(totalCaughtSize);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        river = new Shark[R + 1][C + 1]; // 1-base

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            // 불필요한 이동 계산 최적화
            if (direction <= 2) { // 상하 이동
                speed %= (R - 1) * 2;
            } else { // 좌우 이동
                speed %= (C - 1) * 2;
            }

            Shark shark = new Shark(r, c, speed, direction, size);
            river[r][c] = shark;
            sharks.add(shark);
        }
    }

    private static void simulate() {
        for (int i = 1; i <= C; i++) {
            fishermanPosition = i;
            fishing();
            moveShark();
        }
    }

    private static void fishing() {
        for (int i = 1; i <= R; i++) {
            if (river[i][fishermanPosition] != null) {
                Shark caught = river[i][fishermanPosition];
                totalCaughtSize += caught.size;
                sharks.remove(caught);
                river[i][fishermanPosition] = null;
                return; // 한 마리만 잡으면 종료
            }
        }
    }

    private static void moveShark() {
        // 상어 위치 초기화
        for (Shark shark : sharks) {
            river[shark.r][shark.c] = null;
            move(shark);
        }
        // 상어 배치 및 충돌 처리
        placeSharks();
    }

    private static void placeSharks() {
        List<Shark> survivors = new ArrayList<>();

        for (Shark shark : sharks) {
            Shark existing = river[shark.r][shark.c];

            if (existing == null) {
                river[shark.r][shark.c] = shark;
                survivors.add(shark);
            } else if (existing.size < shark.size) {
                // 큰 상어가 작은 상어를 잡아먹음
                river[shark.r][shark.c] = shark;
                survivors.remove(existing);
                survivors.add(shark);
            }
            // 작은 상어는 무시 (survivors에 추가하지 않음)
        }

        sharks = survivors;
    }

    private static void move(Shark shark) {
        if (shark.direction == RIGHT || shark.direction == LEFT) {
            moveHorizontally(shark);
        } else {
            moveVertically(shark);
        }
    }

    private static void moveHorizontally(Shark shark) {
        int move = (shark.direction == RIGHT) ? 1 : -1;
        int steps = shark.speed;
        int c = shark.c;

        while (steps > 0) {
            // 벽에 부딪히는 경우
            if ((c == 1 && move == -1) || (c == C && move == 1)) {
                move *= -1;  // 방향 전환
                shark.direction = (move == 1) ? RIGHT : LEFT;
            }

            c += move;
            steps--;
        }

        shark.c = c;
    }

    private static void moveVertically(Shark shark) {
        int move = (shark.direction == UP) ? -1 : 1;
        int steps = shark.speed;
        int r = shark.r;

        while (steps > 0) {
            // 벽에 부딪히는 경우
            if ((r == 1 && move == -1) || (r == R && move == 1)) {
                move *= -1;  // 방향 전환
                shark.direction = (move == -1) ? UP : DOWN;
            }

            r += move;
            steps--;
        }

        shark.r = r;
    }
}