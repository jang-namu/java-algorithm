package samsung;

// 사다리 조작

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj15684 {
    private static final int LIMIT = 3;

    public static void main(String[] args) throws IOException {
        // 1. 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        // crossways[b][a] (0-base) -> b번째 세로선과 b+1번째 세로선을 a번째 가로선으로 연결
        boolean[][] crossways = new boolean[H][N - 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            crossways[a - 1][b - 1] = true;
        }

        int answer = dfsSolve(crossways);
//        int answer = bruteforce(crossways);
        System.out.println(answer);
    }

    private static boolean isAvailable(boolean[][] crossways, int r, int c) {
        if (crossways[r][c]) {
            return false;
        }
        if (c - 1 >= 0 && crossways[r][c - 1]) {
            return false;
        }
        if (c + 1 < crossways[0].length && crossways[r][c + 1]) {
            return false;
        }
        return true;
    }

    private static int dfsSolve(boolean[][] crossways) {
        for (int limit = 0; limit <= LIMIT; limit++) {
            if (dfs(crossways, 0, 0, limit)) {
                return limit;
            }
        }
        return -1;
    }

    private static boolean dfs(boolean[][] crossways, int start, int depth, int limit) {
        if (depth == limit) {
            return exactCheck(crossways);
        }

        int maxIdx = crossways.length * crossways[0].length;
        for (int i = start; i < maxIdx; i++) {
            int r = i / crossways[0].length;
            int c = i % crossways[0].length;
            if (isAvailable(crossways, r, c)) {
                crossways[r][c] = true;
                if (dfs(crossways, i + 1, depth + 1, limit)) {
                    return true;
                }
                crossways[r][c] = false;
            }
        }
        return false;
    }

    private static int bruteforce(boolean[][] crossways) {
        int maxIdx = crossways.length * crossways[0].length;

        //
        int count = 0;
        for (int i = -1; i < maxIdx; i++) {
            boolean iFlag = false;
            int ir = i / crossways[0].length;
            int ic = i % crossways[0].length;
            if (i != -1 && isAvailable(crossways, ir, ic)) {
                iFlag = true;
                count++;
                crossways[ir][ic] = true;
            }

            //
            for (int j = -1; j < maxIdx; j++) {
                boolean jFlag = false;
                int jr = j / crossways[0].length;
                int jc = j % crossways[0].length;
                if (j != -1 && isAvailable(crossways, jr, jc)) {
                    jFlag = true;
                    count++;
                    crossways[jr][jc] = true;
                }

                //
                for (int k = -1; k < maxIdx; k++) {
                    boolean kFlag = false;
                    int kr = k / crossways[0].length;
                    int kc = k % crossways[0].length;
                    if (k != -1 && isAvailable(crossways, kr, kc)) {
                        kFlag = true;
                        count++;
                        crossways[kr][kc] = true;
                    }

                    // 3. 퀵 체크
                    if (exactCheck(crossways)) {
                        return count;
                    }

                    if (kFlag) {
                        count--;
                        crossways[kr][kc] = false;
                    }
                }

                if (jFlag) {
                    count--;
                    crossways[jr][jc] = false;
                }
            }

            if (iFlag) {
                count--;
                crossways[ir][ic] = false;
            }
        }
        return -1;
    }


    private static boolean exactCheck(boolean[][] crossways) {
        for (int i = 0; i < crossways[0].length + 1; i++) {
            if (getResult(i, crossways) != i) {
                return false;
            }
        }
        return true;
    }

    private static int getResult(int i, boolean[][] crossways) {
        for (int j = 0; j < crossways.length; j++) {
            if (i < crossways[0].length && crossways[j][i]) {
                i += 1;
            } else if (i > 0 && crossways[j][i - 1]) {
                i -= 1;
            }
        }
        return i;
    }

    private static void print(boolean[][] crossways) {
        for (int i = 0; i < crossways.length; i++) {
            for (int j = 0; j < crossways[i].length; j++) {
                System.out.print(crossways[i][j] + " ");
            }
            System.out.println();
        }
    }
}
