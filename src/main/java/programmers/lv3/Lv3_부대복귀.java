package programmers.lv3;

import java.util.*;
/*
    모든 경로가 1인 다익스트라, '목적지'에서 출발해서 각 노드로의 최단거리를 구한다.
    bfs를 통해 최초 방문 시에만 길이를 저장.
 */
public class Lv3_부대복귀 {

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        ArrayList<Integer>[] adj = new ArrayList[n+1];
        for (int i=0; i < adj.length; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        // [a, b]와 [b, a] 중 하나만 주어진다. -> set 쓸 필요 없음.
        for (int[] road : roads) {
            adj[road[0]].add(road[1]);
            adj[road[1]].add(road[0]);
        }

        var deque = new LinkedList<Integer>();
        deque.addLast(destination);

        int[] distance = new int[n+1];
        Arrays.fill(distance, -1);
        distance[destination] = 0;

        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);

        while (deque.size() > 0) {
            int position = deque.pollFirst();
            if (visited[position]) continue;
            visited[position] = true;

            for (int i=0; i < adj[position].size(); i++) {
                int idx = (Integer)adj[position].get(i);
                if (distance[idx] == -1){
                    deque.addLast(idx);
                    distance[idx] = distance[position] + 1;
                }
            }
        }

        for (int i=0; i < sources.length; i++) {
            answer[i] = (distance[sources[i]]);
        }

        return answer;
    }
}