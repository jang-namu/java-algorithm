package programmers.lv2;

import java.util.stream.Stream;
import java.util.*;

public class Lv2_무인도_여행 {
}

/*
    이전에 많이 풀어봤던 유형. dfs 또는 bfs와 함께, 상하좌우의 move를 사용하는 문제.
    visited를 이용해서 방문한 노드들을 체크하고, 전체 맵을 돌아보며 아직 방문하지 않은 노드에서 dfs를 돌려 해당 회차에 포함되는 노드의 합을 구한다
*/


class Solution46346 {
    static String[][] world;
    static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int dfs(boolean[][] visited, int a, int b) {
        int days = Integer.parseInt(world[b][a]);
        var st = new Stack<Pair>();

        st.push(new Pair(a, b));
        visited[b][a] = true;
        while (st.size() != 0) {
            Pair temp = st.pop();
            int x = temp.x;
            int y = temp.y;

            for (int i = 0; i < move.length; i++) {
                int nx =  x + move[i][0];
                int ny =  y + move[i][1];
                if (ny < 0 || ny >= world.length || nx < 0 || nx >= world[0].length)
                    continue;
                if (!visited[ny][nx] && !world[ny][nx].equals("X")) {
                    visited[ny][nx] = true;
                    st.push(new Pair(nx, ny));
                    days += Integer.parseInt(world[ny][nx]);
                }
            }

        }
        return days;
    }

    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<Integer>();

        world = new String[maps.length][];
        for (int i=0; i < maps.length; i++) {
            world[i] = maps[i].split("");
        }

        boolean[][] visited = new boolean[world.length][world[0].length];
        for (int i = 0; i < world.length; i++) {
            Arrays.fill(visited[i], false);
        }
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {

                if (!visited[i][j] && !world[i][j].equals("X")) {
                    System.out.println(i+ " " + j);
                    answer.add(dfs(visited, j, i));
                }
            }

        }

        if (answer.size() == 0) {
            int[] res = { -1 };
            return res;
        } else {
            return answer.stream().mapToInt(Integer::intValue).sorted().toArray();
        }

    }
}