package programmers.lv1;

import java.util.LinkedList;
import java.util.List;

public class Lv1_모의고사 {
    public int[] solution(int[] answers) {
        int[] ones = {1,2,3,4,5};
        int[] twos = {2,1,2,3,2,4,2,5};
        int[] threes = {3,3,1,1,2,2,4,4,5,5};

        int[] answer = new int[3];
        for (int i=0; i < answers.length; i++) {
            if (answers[i] == ones[i % ones.length]) answer[0]++;
            if (answers[i] == twos[i % twos.length]) answer[1]++;
            if (answers[i] == threes[i % threes.length]) answer[2]++;
        }

        List<Integer> lst = new LinkedList<>();
        int max = Math.max(Math.max(answer[0], answer[1]), answer[2]);
        for (int i = 0; i < answer.length; i++) {
            if (max == answer[i]) lst.add(i+1);
        }

        return lst.stream().mapToInt(Integer::intValue).toArray(); // 주의! 느리다.
    }
}