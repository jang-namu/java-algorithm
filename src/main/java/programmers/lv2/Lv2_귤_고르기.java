package programmers.lv2;

import java.util.*;

public class Lv2_귤_고르기 {
}


class Solution123902we {
    public int solution(int k, int[] tangerine) {

        var map = new HashMap<Integer, Integer>();

        for (int tang : tangerine) {
            map.put(tang, map.getOrDefault(tang, 0) + 1);
        }

        List<Integer> tangers = new ArrayList<Integer>(map.keySet());
        tangers.sort((o1, o2) -> {
            return map.get(o2) - map.get(o1);
        });

        /*
        var tangers = new Integer[10000001];
        Arrays.fill(tangers, 0);
        for (int tang : tangerine) {
            tangers[tang] += 1;
        }

        // Arrays.sort(tangers, Collections.reverseOrder());

        Arrays.sort(tangers, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });

                int answer = 0;
        int count = 0;
        for (int i : tangers) {
            answer++;
            count += i;
            if (count >= k) {
                break;
            }
        }

        return answer;
 */

        int answer = 0;
        int count = 0;
        for (int i : tangers) {
            answer++;
            count += map.get(i);
            if (count >= k) {
                break;
            }
        }


        return answer;
    }
}