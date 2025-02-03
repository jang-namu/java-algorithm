package programmers.lv2;

import java.util.Deque;
import java.util.LinkedList;

/*
dfs로 탐색하며 영역 갯수, 크기 구하기
 */
public class Lv2_카카오프렌즈_컬러링북 {
    int[][] MOVES = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int numberOfArea;
    int maxSizeOfOneArea;
    int[][] visited;

    public int[] solution(int m, int n, int[][] picture) {
        init(m, n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && visited[i][j] == 0) { // 새로운 영역 발견
                    numberOfArea++;
                    dfs(picture, i, j);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private void init(int m, int n) {
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        visited = new int[m][n];
    }

    private static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private void dfs(int[][] picture, int i, int j) {
        int colorNumber = picture[i][j]; // 한 번에 하나의 색/영역만 살펴본다.

        Deque<Point> st = new LinkedList<>();

        int numberOfSearch = 1; // 현재 탐색중인 영역의 크기를 계산할 변수
        visited[i][j] = 1;
        st.addLast(new Point(i, j));

        while (!st.isEmpty()) {
            Point p = st.removeLast();
            for (int[] move : MOVES) {
                int nr = p.r + move[0];
                int nc = p.c + move[1];
                if (isAvailable(picture, nr, nc, colorNumber) && visited[nr][nc] == 0) {
                    visited[nr][nc] = 1;
                    numberOfSearch++;
                    st.addLast(new Point(nr, nc));
                }
            }
        }
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, numberOfSearch);
    }

    private boolean isAvailable(int[][] picture, int nr, int nc, int colorNumber) { // 한 번에 하나의 색/영역만 살펴본다.
        return 0 <= nr && nr < picture.length && 0 <= nc && nc < picture[0].length && picture[nr][nc] == colorNumber;
    }
}