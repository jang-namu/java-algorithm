// 테트로미노
/*
    static int[][][] tetrominos = {
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            {{0, 1}, {1, 1}, {2, 1}, {2, 0}},
            {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}}
    };
   이 문제는 도형을 가지고 돌리려면 답이없다.
   => 그래프 문제로 생각하자.
   테트로미노는 결국 칸 4개를 차지하는 연이은 도형
   => 깊이가 4인 그래프 탐색

   그래프 탐색으로 바꾸고 나면 회전, 대칭 모두 더 이상 문제가 되지 않는다.

   주의! 'ㅜ'는 dfs, bfs와 같은 방법으로 알 수 없다!
 */
package samsung;
import java.io.*;
import java.util.StringTokenizer;

public class boj14500 {

    static int N, M;
    static int[][] map;
    static int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean[][] visited;

    // 'ㄱ', 'ㅁ', 'ㅡ', 'ㄹ' 모양 처리
    public static int dfs(int r, int c, int depth) {
        int result = map[r][c];
        if (depth == 3) return map[r][c];

        int next = 0;
        for (int[] move : moves) {
            if ((r + move[0] < 0 || r + move[0] >= N) ||
                (c + move[1] < 0 || c + move[1] >= M) ||
                visited[r+ move[0]][c + move[1]])
                continue;

            visited[r+ move[0]][c + move[1]] = true;
            next = Math.max(next, dfs(r + move[0], c + move[1], depth + 1));
            visited[r+ move[0]][c + move[1]] = false;
        }
        return result + next;
    }

    // 'ㅜ' 모양 처리
    public static int sum(int r, int c) {
        int max = 0;
        int result = map[r][c];
        for (int i = 0; i < moves.length; i++) {
            if (boundary(r, c, i)) continue;
            result += map[r + moves[i][0]][c + moves[i][1]];
            for (int j = i+1; j < moves.length; j++) {
                if (boundary(r, c, j)) continue;
                result += map[r + moves[j][0]][c + moves[j][1]];
                for (int k = j+1; k < moves.length; k++) {
                    if (boundary(r, c, k)) continue;
                    max = Math.max(max, result + map[r + moves[k][0]][c + moves[k][1]]);
                }
                result -= map[r + moves[j][0]][c + moves[j][1]];
            }
            result -= map[r + moves[i][0]][c + moves[i][1]];
        }
        return max;
    }

    private static boolean boundary(int r, int c, int i) {
        return (r + moves[i][0] < 0 || r + moves[i][0] >= N) || (c + moves[i][1] < 0 || c + moves[i][1] >= M);
    }

    public static void main(String[] args) throws IOException {
        init();

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                result = Math.max(result, dfs(i, j, 0));
                visited[i][j] = false;
                result = Math.max(result, sum(i, j));
            }
        }
        System.out.println(result);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
    }
}
