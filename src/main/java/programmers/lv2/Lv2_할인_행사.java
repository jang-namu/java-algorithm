package programmers.lv2;

import java.util.*;

/*
    할인품목의 10일치씩 세어둔다. -> map 이용 '이름:수량'으로 저장
    그 후 앞에서 하나 뺴고 뒤에서 하나 넣으면서 확인.
*/

public class Lv2_할인_행사 {

    public boolean check(String[] want, int[] number, HashMap<String, Integer> map) {
        for (int i=0; i < want.length; i++) {
            if (map.getOrDefault(want[i], 0) < number[i]) {
                return false;
            }
        }
        return true;
    }

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        var map = new HashMap<String,Integer>();

        for (int i = 0; i < 10; i++) {
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
        }
        if (check(want, number, map)) {
            answer++;
        }

        for (int i = 10; i < discount.length; i++) {
            map.put(discount[i-10], map.get(discount[i-10])  - 1);
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
            if (check(want, number, map)) {
                answer++;
            }
        }

        return answer;
    }
}