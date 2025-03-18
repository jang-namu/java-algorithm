package boj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj15681 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        List<List<Integer>> edges = new ArrayList<>();
        int[] vertexes = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st2.nextToken());
            int e = Integer.parseInt(st2.nextToken());
            edges.get(s).add(e);
            edges.get(e).add(s);
        }

        boolean[] visited = new boolean[N + 1];
        dfs(edges, visited, vertexes, R);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            sb.append(vertexes[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

    private static int dfs(List<List<Integer>> edges, boolean[] visited, int[] vertexes, int start) {
        int count = 1;
        visited[start] = true;
        for (Integer next : edges.get(start)) {
            if (visited[next]) {
                continue;
            }
            count += dfs(edges, visited, vertexes, next);
        }
        vertexes[start] = count;
        return count;
    }
}