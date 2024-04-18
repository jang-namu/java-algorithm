package programmers.lv2;


import java.util.*;

public class Lv2_타겟_넘버 {

    public int solution(int[] numbers, int target) {
        List<Integer> q = new LinkedList<>();

        int c = 1;
        for (int t : numbers) {
            for (int i = 0; i < c; i++) {
                if (q.size() == 0) {
                    q.add(t);
                    q.add(-t);
                } else {
                    int tmp = q.get(0);
                    q.remove(0);
                    q.add(tmp + t);
                    q.add(tmp - t);
                }
            }
            c *= 2;
        }
        int answer= 0;
        for (Integer i : q) {
            if (i == target) {
                answer++;
            }
        }

        return answer;
    }

}
