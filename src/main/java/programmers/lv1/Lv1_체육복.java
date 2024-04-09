package programmers.lv1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Lv1_체육복 {

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        Set<Integer> lst = Arrays.stream(lost).boxed().collect(Collectors.toSet());
        Set<Integer> rsv = Arrays.stream(reserve).boxed().collect(Collectors.toSet());

        Set<Integer> lt = new HashSet<>(lst);
        for (Integer l : lt) {
            if (rsv.contains(l)) {
                answer++;
                rsv.remove(l);
                lst.remove(l);
            }
        }

        for (Integer l : lst) {
            if (rsv.contains(l - 1)) {
                answer++;
                rsv.remove(l - 1);
                continue;
            }
            if (rsv.contains(l + 1)) {
                answer++;
                rsv.remove(l + 1);
            }
        }
        return answer;
    }
}
