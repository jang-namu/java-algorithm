package programmers.lv2;

import java.util.*;

/*
    map 두개 사용 철수, 동생
    처음 토핑의 모든 원소를 동생 map에다 넣는다. -> 앞에서부터 하나씩 빼면수 철수에게 넣는다.
    종류의 개수 (size())가 같으면 answer++
*/

public class Lv2_롤케이크_자르기 {

    public int solution(int[] topping) {
        int answer = 0;

        var chulsoo = new HashMap<Integer, Integer>();
        var brother = new HashMap<Integer, Integer>();

        for (int i : topping) {
            brother.put(i, brother.getOrDefault(i, 0) + 1);
        }

        for (int i=0; i < topping.length; i++) {
            brother.put(topping[i], brother.get(topping[i]) - 1);
            if (brother.get(topping[i]) == 0) {
                brother.remove(topping[i]);
            }

            chulsoo.put(topping[i], brother.getOrDefault(topping[i], 0) + 1);

            if (chulsoo.size() == brother.size()) {
                answer++;
            }
        }

        return answer;
    }
}