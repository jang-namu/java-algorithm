package programmers;

import java.util.*;

public class Solve {
    static class Pt implements Comparable {
        public int x; int y;
        public int count;

        public Pt(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public int compareTo(Object o) {
            Pt pt = (Pt) o;
            return -(this.count - pt.count);
        }
    }

    static int[][] move = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}, {0, 0}};

    public static int solution(int[][] clockHands) {
        int answer = 0;

        int[][] clockHand = new int[clockHands.length + 2][];
        clockHand[0] = new int[clockHands.length + 2];
        Arrays.fill(clockHand[0], -1);
        clockHand[clockHands.length + 1] = new int[clockHands.length + 2];
        Arrays.fill(clockHand[clockHands.length + 1], -1);

        for (int i = 0; i < clockHands.length; i++) {
            clockHand[i + 1] = new int[clockHands.length + 2];
            clockHand[i + 1][0] = -1;
            clockHand[i + 1][clockHands.length + 1] = -1;
            for (int j = 0; j < clockHands.length; j++) {
                clockHand[i+1][j+1] = clockHands[i][j];
            }
        }

        var counts = new PriorityQueue<Pt>();
        int beforeMax = 5;
        while (true) {
            counts.clear();
            for (int i = 1; i < clockHand.length; i++) {
                for (int j = 1; j < clockHand[0].length; j++) {

                    int now = 0;
                    for (int[] mv : move) {
                        int ni = i + mv[0];
                        int nj = j + mv[1];

                        if (clockHand[ni][nj] == -1) {
                            continue;
                        }
                        if (clockHand[ni][nj] % 4 != 0) {
                            now++;
                        }
                    }
                    if (now != 0) {
                        counts.add(new Pt(i, j, now));
                    }
                    if (beforeMax == now) break;
                }
            }

            if (counts.size() == 0) break;
            Pt pt = counts.poll();
            beforeMax = pt.count;
            for (int[] mv : move) {
                int ni = pt.x + mv[0];
                int nj = pt.y + mv[1];
                if (clockHand[ni][nj] == -1) {
                    continue;
                }
                clockHand[ni][nj] = (clockHand[ni][nj] + 1) % 4;
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] s = {{0,3,3,0},{3,2,2,3},{0,3,2,0},{0,3,3,3}};
        System.out.println(solution(s));

    }
}
