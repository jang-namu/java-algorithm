package programmers.lv1;

import java.util.HashMap;
import java.util.Map;

public class Lv1_성격_유형_검사하기 {
    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> score = new HashMap<>();

        for (int i = 0; i < survey.length; i++) {
            String key1 = survey[i].substring(0, 1);
            String key2 = survey[i].substring(1, 2);
            if (choices[i] > 4) {
                score.put(key2, score.getOrDefault(key2, 0) + choices[i] - 4);
            } else {
                score.put(key1, score.getOrDefault(key1, 0) + 4 - choices[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append((score.getOrDefault("R", 0) >= score.getOrDefault("T", 0)) ? "R" : "T");
        sb.append((score.getOrDefault("C", 0) >= score.getOrDefault("F", 0)) ? "C" : "F");
        sb.append((score.getOrDefault("J", 0) >= score.getOrDefault("M", 0)) ? "J" : "M");
        sb.append((score.getOrDefault("A", 0) >= score.getOrDefault("N", 0)) ? "A" : "N");

        return sb.toString();
    }
}