package programmers.lv3;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
메모리 초과 -> 답지 참고해서 수정

처음에는
class Cell {
    String value;
    List<Point> merged;
}
를 각 칸마다 만들어서 해결하려 했음 -> 메모리 초과

수정된 방식: Union-Find의 아이디어 차용
+ 좌표 계산을 위해 10000 * r + c로 표현

 */

public class Lv3_표_병합 {
    static String[][] values;
    static int[][] parents;

    public String[] solution(String[] commands) {
        init();
        List<String> answer = new ArrayList<>();

        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command, " ");
            String cmdType = st.nextToken();

            if (cmdType.equals("UPDATE")) {
                String positionOrValue1 = st.nextToken();
                String positionOrValue2 = st.nextToken();

                if (st.hasMoreTokens()) {
                    // UPDATE r c V
                    int r = Integer.parseInt(positionOrValue1);
                    int c = Integer.parseInt(positionOrValue2);

                    int[] root = findRoot(r, c);
                    values[root[0]][root[1]] = st.nextToken();
                    continue;
                }
                // UPDATE V1 V2
                for (int i = 1; i <= 50; i++) {
                    for (int j = 1; j <= 50; j++) {
                        if (values[i][j] != null && values[i][j].equals(positionOrValue1)) {
                            values[i][j] = positionOrValue2;
                        }
                    }
                }
                continue;
            }
            if (cmdType.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());

                int[] root1 = findRoot(r1, c1);
                int[] root2 = findRoot(r2, c2);
                if (root1[0] == root2[0] && root1[1] == root2[1]) {
                    continue;
                }

                String value = values[root1[0]][root1[1]];
                if (value == null) {
                    value = values[root2[0]][root2[1]];
                }
                values[root1[0]][root1[1]] = value;
                parents[root2[0]][root2[1]] = 10000 * root1[0] + root1[1];

                continue;
            }
            if (cmdType.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                int[] root = findRoot(r, c);
                String value = values[root[0]][root[1]];
                List<int[]> shouldUnmerged = new ArrayList<>();
                for (int i = 1; i <= 50; i++) {
                    for (int j = 1; j <= 50; j++) {
                        int[] currentRoot = findRoot(i, j);
                        if (root[0] == currentRoot[0] && root[1] == currentRoot[1]) {
                            shouldUnmerged.add(new int[]{i, j});
                        }
                    }
                }

                for (int[] pos : shouldUnmerged) {
                    parents[pos[0]][pos[1]] = 10000 * pos[0] + pos[1];
                    values[pos[0]][pos[1]] = null;
                }

                values[r][c] = value;

                continue;
            }
            if (cmdType.equals("PRINT")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int[] root = findRoot(r, c);
                String value = values[root[0]][root[1]];
                answer.add(value == null ? "EMPTY" : value);
            }

        }

        return answer.toArray(new String[0]);
    }

    private int[] findRoot(int r, int c) {
        int nr = parents[r][c] / 10000;
        int nc = parents[r][c] % 10000;
        if (nr == r && nc == c) {
            return new int[]{r, c};
        }
        int[] root = findRoot(nr, nc);
        parents[r][c] = 10000 * root[0] + root[1];
        return root;
    }

    private void init() {
        values = new String[52][52];
        parents = new int[52][52];
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                parents[i][j] = 10000 * i + j;
            }
        }
    }


}