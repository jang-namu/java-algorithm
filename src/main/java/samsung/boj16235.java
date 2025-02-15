package samsung;

// https://www.acmicpc.net/problem/16235 나무 재테크

/*
문제에서 '입력으로 주어지는 나무의 위치는 모두 서로 다름'와 나무가 번식하는 메커니즘을 살펴보면,
PriorityQueue를 쓸 필요없이 단순히 새로 추가되는 나무들을 배열의 앞에 넣어서 순방향을 읽거나,
뒤에 넣어서 역방향으로 읽으면된다. 성장하지 못하는 나무는 죽기 때문에 두 나무의 나이가 역전당할일은 없다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj16235 {
    private static final int[][] NEAR = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private static int N;
    private static int M;
    private static int K;

    private static int[][] soil;
    private static int[][] S2D2;
    private static PriorityQueue<Tree> trees = new PriorityQueue<>();
    private static List<Tree> deadTrees;

    private static class Tree implements Comparable<Tree> {
        int r;
        int c;
        int age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }
        System.out.println(trees.size());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        soil = new int[N][N];
        S2D2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                soil[i][j] = 5;
                S2D2[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            trees.add(new Tree(Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken())));
        }
    }

    private static void spring() {
        PriorityQueue<Tree> alive = new PriorityQueue<>();
        deadTrees = new ArrayList<>(); // 다시 초기화
        while (!trees.isEmpty()) {
            Tree tree = trees.poll();
            if (soil[tree.r][tree.c] >= tree.age) {
                soil[tree.r][tree.c] -= tree.age;
                tree.age += 1;
                alive.add(tree);
            } else {
                deadTrees.add(tree);
            }
        }
        trees = alive;
    }

    private static void summer() {
        for (Tree tree : deadTrees) {
            soil[tree.r][tree.c] += tree.age / 2;
        }
    }

    private static void autumn() {
        PriorityQueue<Tree> next = new PriorityQueue<>();
        while (!trees.isEmpty()) {
            Tree tree = trees.poll();
            if (tree.age % 5 == 0) {
                for (int[] near : NEAR) {
                    int r = tree.r + near[0];
                    int c = tree.c + near[1];
                    if (isInIndex(r, c)) {
                        next.add(new Tree(r, c, 1));
                    }
                }
            }
            next.add(tree);
        }
        trees = next;
    }

    private static boolean isInIndex(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                soil[i][j] += S2D2[i][j];
            }
        }
    }
}
