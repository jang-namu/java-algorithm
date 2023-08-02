package programmers.lv2;

import java.util.*;

public class Lv2_의상 {
    public int solution(String[][] clothes) {
        int answer = 1;

        var map = new HashMap<String, Integer>();

        for (String[] clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }

        var set = map.keySet();
        for (String key : set) {
            answer *= map.get(key) + 1;
        }

        return answer - 1;
    }
}