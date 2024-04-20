package programmers.lv2;

import java.util.LinkedList;

public class Lv2_게임_맵_최단거리 {
    static class Point {
        int x;
        int y;
        int n;
        public Point(int a, int b, int c) {
            x = a;
            y = b;
            n = c;
        }
    }

    int[] moveX = {1, 0, -1, 0};
    int[] moveY = {0, 1, 0, -1};

    private int dfs(int[][] maps) {
        LinkedList<Point> stack = new LinkedList<>();
        stack.add(new Point(0, 0, 0));
        while (stack.size() > 0) {
            Point tmp = stack.pop();
            int x = tmp.x;
            int y = tmp.y;

            if (x == maps.length - 1 && y == maps[0].length - 1) {
                return tmp.n + 1;
            }
            for (int i = 0; i < moveX.length; i++) {
                int newX = x + moveX[i];
                int newY = y + moveY[i];

                if ((newX >= 0 && newX < maps.length) &&
                    (newY >= 0 && newY < maps[0].length) &&
                    (maps[newX][newY] == 1)) {
                    maps[newX][newY] = 0;
                    stack.add(new Point(newX, newY, tmp.n + 1));
                    // System.out.println(newX + " " + newY + " " +  tmp.n+1);
                }
            }
        }

        return -1;
    }

    public int solution(int[][] maps) {
        int answer = dfs(maps);

        return answer;
    }
}