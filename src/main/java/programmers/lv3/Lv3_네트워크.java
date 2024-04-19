package programmers.lv3;

import java.util.LinkedList;
import java.util.Queue;

public class Lv3_네트워크 {
    private void tour(int[][] computers, int from) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(from);

        while (q.size() > 0) {
            from = q.poll();
            for (int to = 0; to < computers[0].length; to++) {
                if (computers[from][to] == 1) {
                    q.offer(to);
                    computers[from][to] = 0;
                    computers[to][from] = 0;
                }
            }
        }
    }

    private int sum(int[] arr) {
        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            answer += arr[i];
        }
        return answer;
    }

    // private void print(int[][] arrs) {
    //     for (int[] arr : arrs) {
    //         for (int a : arr) {
    //             System.out.print(a + ", ");
    //         }
    //         System.out.println();
    //     }
    // }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        for (int i=0; i < computers.length; i++) {
            if (sum(computers[i]) != 0) {
                tour(computers, i);
                // print(computers);
                answer++;
            }
        }

        return answer;
    }
}