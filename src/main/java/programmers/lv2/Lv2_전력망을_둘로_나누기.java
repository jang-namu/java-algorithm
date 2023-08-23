package programmers.lv2;

/*
    하나씩 간선을 제외하고 dfs로 그래프(트리) 탐색을 한다.
    '트리'라는 특징으로 한쪽(a개)만 구하면 반대쪽 노드는 (n-a)개 임을 사용하여 쉽게 계산할 수 있다.
 */

import java.util.*;

public class Lv2_전력망을_둘로_나누기 {

    public int dfs(List<Integer>[] wires, int src) {
        boolean[] visited = new boolean[wires.length + 2];
        var stack = new Stack<Integer>();

        visited[src] = true;
        stack.push(src);

        int num = 1;
        while (!stack.isEmpty()) {
            int nSrc = stack.pop();

            for (Integer des : wires[nSrc]) {
                if (visited[des]) {
                    continue;
                }
                visited[des] = true;
                stack.push(des);
                num++;
            }
        }
        return num;
    }

    public int solution(int n, int[][] wires) {
        int answer = 100;

        ArrayList<Integer>[] wiresVector = new ArrayList[n+1];
        for (int i=0; i < n+1; i++) {
            wiresVector[i] = new ArrayList<Integer>();
        }


        for (int i = 0; i < wires.length - 1; i++) {
            wiresVector[wires[i][0]].add(wires[i][1]);
            wiresVector[wires[i][1]].add(wires[i][0]);
        }
        int tmp = dfs(wiresVector, wires[0][0]);
        answer = Math.min(answer, Math.abs(2 * tmp - n));


        for (int i = wires.length - 2; i >= 0; i--) {
            wiresVector[wires[i][0]].remove(Integer.valueOf(wires[i][1]));
            wiresVector[wires[i][1]].remove(Integer.valueOf(wires[i][0]));

            wiresVector[wires[i+1][0]].add(wires[i+1][1]);
            wiresVector[wires[i+1][1]].add(wires[i+1][0]);

            tmp = dfs(wiresVector, wires[0][0]);
            answer = Math.min(answer, Math.abs(2 * tmp - n));
        }

        return answer;
    }
}

/*
// 자기의 자식의 개수만을 가지고 구한다.
// 의미상으로 dfs()를 호출하기 전 노드와 호출한 후 노드가 단절된다.
class Solution {
    int N, min;
    int[][] map;
    int[] vst;
    int dfs(int n){
        vst[n] = 1;
        int child = 1;
        for(int i = 1; i <= N; i++) {
            if(vst[i] == 0 && map[n][i] == 1) {
                vst[i] = 1;
                child += dfs(i);
            }
        }
        min = Math.min(min, Math.abs(child - (N - child)));
        return child;
    }
    public int solution(int n, int[][] wires) {
        N = n;
        min = n;
        map = new int[n+1][n+1];
        vst = new int[n+1];
        for(int[] wire : wires) {
            int a = wire[0], b = wire[1];
            map[a][b] = map[b][a] = 1;
        }
        dfs(1);
        return min;
    }
}

 */