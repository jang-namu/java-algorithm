package samsung;

// https://www.acmicpc.net/problem/15685 드래곤 커브

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
(x,y)를 기준으로 점 (a,b)를 시계방향으로 90도 회전한 점은 (x+y-b, -x+y+a)
 */
public class boj15685 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[101][101];

        List<List<Point>> dragonCurve = init();

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            drawCurve(map, dragonCurve, new Point(x, y), d, g);
        }

        int answer = 0;
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[0].length - 1; j++) {
                if (checkSquare(map, i, j)) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private static List<List<Point>> init() {
        List<List<Point>> dragonCurve = new ArrayList<>();
        for (int i = 0; i < 11; i++) { // 0 <= g <= 10
            dragonCurve.add(new ArrayList<>());
        }
        Point base = new Point(1, 0);
        dragonCurve.get(0).add(new Point(0, 0));
        dragonCurve.get(0).add(base);

        for (int i = 1; i < 11; i++) { // 0 <= g <= 10
            List<Point> pointer = dragonCurve.get(i);
            List<Point> prevPointer = dragonCurve.get(i - 1);
            for (Point target : prevPointer) {
                pointer.add(target);
            }
            Point rotated = null;
            for (int j = prevPointer.size() - 2; j >= 0 ; j--) { // 마지막 요소 중복하지 않기위함.
                Point target = prevPointer.get(j);
                rotated = base.rotate(target);
                pointer.add(base.rotate(target));
            }
            base = rotated; // 0번째 요소만 순서를 보장함 (다음 base로 쓰기위해)
        }
        return dragonCurve;
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private Point rotate(Point target) {
            return new Point(x + y - target.y, -x + y + target.x);
        }

        private Point moveToBase(Point move, int d) {
            Point distance = direction(move, d);
            return new Point(x + distance.x, y + distance.y);
        }

        private Point direction(Point move, int d) {
            if (d == 0) {
                return move;
            }
            if (d == 1) {
                return new Point(move.y, -move.x);
            }
            if (d == 2) {
                return new Point(-move.x, -move.y);
            }

            return new Point(-move.y, move.x);
        }
    }

    private static void drawCurve(boolean[][] map, List<List<Point>> dragonCurve, Point base, int d, int g) {
        for (Point p : dragonCurve.get(g)) {
            Point tmp = base.moveToBase(p, d);
            map[tmp.x][tmp.y] = true;
        }
    }

    private static boolean checkSquare(boolean[][] map, int i, int j) {
        return map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1];
    }

}
