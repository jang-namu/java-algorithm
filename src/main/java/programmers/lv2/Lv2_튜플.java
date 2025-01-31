package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
풀이 시, Set을 이용하고 싶었지만 모든 배열을 Set으로 바꾸는게 부담이 되서 int[]로 다룸.
반면, 아래 코드를 보면 모든 배열을 Set으로 바꾸지않고, Set 하나면 충분하다는 것을 알 수 있음.
연이은 replaceAll과 trim, split하는 과정이나, List를 만들지 않고 Arrays.sort를 이용하는 것도 눈여겨볼만함.

가장 인상적인 부분은 역시
'if (set.add(s2))' 부분으로 값이 정상적으로 추가됐을 때만 true를 반환하는 것을 사용한 점임.
이를 통해 '일단 넣어보고 중복 확인해보지' 전략이 가능함

# 프로그래머스 정답 코드
class Solution {
    public int[] solution(String s) {
        Set<String> set = new HashSet<>();
        String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
        Arrays.sort(arr, (a, b)->{return a.length() - b.length();});
        int[] answer = new int[arr.length];
        int idx = 0;
        for(String s1 : arr) {
            for(String s2 : s1.split(",")) {
                if(set.add(s2)) answer[idx++] = Integer.parseInt(s2);
            }
        }
        return answer;
    }
}
 */

public class Lv2_튜플 {
    public int[] solution(String s) {

        List<String> parsed = parse(s);

        List<int[]> converted = parsed.stream().map(str -> convertStringToIntArray(str))
                .collect(Collectors.toList());
        converted.sort((arr1, arr2) -> {
            return arr1.length - arr2.length;
        });

        int[] answer = inferTuple(converted);
        return answer;
    }

    private int[] inferTuple(List<int[]> converted) {
        int[] answer = new int[converted.size()];
        int idx = 0;

        for (int[] set : converted) {
            for (int e : set) {
                if (contains(answer, e, 0, idx)) {
                    continue;
                }
                answer[idx++] = e;
                break;
            }
        }
        return answer;
    }

    private boolean contains(int[] answer, int target, int start, int end) {
        for (int i = start; i < end; i++) {
            if (answer[i] == target) {
                return true;
            }
        }
        return false;
    }

    private List<String> parse(String s) {
        List<String> parsed = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(s, "{}");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.equals(",")) {
                continue;
            }
            parsed.add(token);
        }
        return parsed;
    }

    private int[] convertStringToIntArray(String s) {
        String[] converted = s.split(",");
        return Arrays.stream(converted).mapToInt(Integer::parseInt).toArray();
    }
}