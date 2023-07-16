package programmers.lv2;

import java.util.*;
import java.util.stream.*;

public class Lv2_미로_탈출 {
}

/*
    못 풀면 바보.
    Java의 Deque는 대표적으로 두 가지 구현체가 있다.
    ArrayDeque, LinkedList (속도는 전자가 좀 더 빠름.)
*/


class Solution {
    static char[][] world;
    static boolean[][] visited;
    static int[][] move = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };

    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs(int start_x, int start_y, char goalLetter) {
        Deque<Pair> deq = new ArrayDeque<Pair>();
        deq.add(new Pair(start_x, start_y));
        visited[start_x][start_y] = true;

        int count = 0;
        while (!deq.isEmpty()) {
            int length = deq.size();
            count++;
            for (int i=0; i < length; i++) {
                Pair temp = deq.poll();
                int x = temp.x;
                int y = temp.y;

                for (int[] mv : move) {
                    int nx = x + mv[0];
                    int ny = y + mv[1];
                    if (nx < 0 || nx >= world.length || ny < 0 || ny >= world[0].length || visited[nx][ny]) {
                        continue;
                    } else if (world[nx][ny] == goalLetter) {
                        return count;
                    } else if (world[nx][ny] != 'X') {
                        deq.add(new Pair(nx, ny));
                    }
                    visited[nx][ny] = true;
                }
            }
        }

        return -1;
    }

    public int solution(String[] maps) {
        int answer = 0;
        int sx = -1; int sy = -1;
        int lx = -1; int ly = -1;

        world = new char[maps.length][maps[0].length()];
        for (int i=0; i < maps.length; i++) {
            char[] s = maps[i].toCharArray();
            for (int j=0; j < s.length; j++) {
                if (s[j] == 'S') {
                    sx = i;
                    sy = j;
                }
                if (s[j] == 'L') {
                    lx = i;
                    ly = j;
                }
                world[i][j] = s[j];
            }
        }

        visited = new boolean[maps.length][maps[0].length()];
        for (boolean[] v : visited) {
            Arrays.fill(v, false);
        }

        int startToLever = bfs(sx, sy, 'L');
        if (startToLever == -1) return -1;

        for (boolean[] v : visited) {
            Arrays.fill(v, false);
        }

        int leverToGoal = bfs(lx, ly, 'E');
        if (leverToGoal == -1) return -1;

        return answer + startToLever + leverToGoal;
    }
}