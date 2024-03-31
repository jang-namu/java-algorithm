package programmers.lv1;

import java.util.*;

public class Lv1_개인정보_수집_유효기간 {

    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> hashTerms = new HashMap<>();
        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);
            hashTerms.put(st.nextToken(), Integer.valueOf(st.nextToken()));
        }

        StringTokenizer st = new StringTokenizer(today, ".");
        int year = Integer.valueOf(st.nextToken());
        int month = Integer.valueOf(st.nextToken());
        int day = Integer.valueOf(st.nextToken());

        int todayDate = (year * 12 + month) * 28 + day;

        for (int i = 0; i < privacies.length; i++) {
            st = new StringTokenizer(privacies[i], ". ");
            int expirate = Integer.valueOf(st.nextToken()) * 12;
            expirate += Integer.valueOf(st.nextToken());
            int d = Integer.valueOf(st.nextToken());
            expirate += hashTerms.get(st.nextToken());

            expirate = expirate * 28 + d;
            if (todayDate >= expirate) {
                answer.add(i+1);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

/*
import java.util.stream.Collectors;
import java.util.*;


class Solution {
    private int year;
    private int month;
    private int day;

    private boolean isExpired(int y, int m, int d, int t) {
        int tmpYear = y + (m+t) / 12;
        int tmpMonth = (m + t) % 12;
        if (tmpMonth == 0) {
            tmpYear--;
            tmpMonth=12;
        }
        if (tmpYear > year) {
            return false;
        } else if (tmpYear == year) {
            if (tmpMonth > month) {
                return false;
            } else if (tmpMonth == month) {
                if (d > day) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> hashTerms = new HashMap<>();

        StringTokenizer st2 = new StringTokenizer(today, ".");
        year = Integer.valueOf(st2.nextToken());
        month = Integer.valueOf(st2.nextToken());
        day = Integer.valueOf(st2.nextToken());

        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);
            hashTerms.put(st.nextToken(), Integer.valueOf(st.nextToken()));
        }

        int idx = 1;
        for (String privacy : privacies) {
            StringTokenizer st = new StringTokenizer(privacy, ". ");
            int y = Integer.valueOf(st.nextToken());
            int m = Integer.valueOf(st.nextToken());
            int d = Integer.valueOf(st.nextToken());
            int t = hashTerms.get(st.nextToken());
            if (isExpired(y, m, d, t)) {
                answer.add(idx);
            }
            idx++;
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
*/