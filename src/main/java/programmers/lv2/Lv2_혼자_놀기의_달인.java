package programmers.lv2;

import java.util.*;

/*
    모든 그룹은 하나의 사이클을 이룬다.
    사이클을 하나씩 다 만든다음. 개수가 많은 거 두개 고르면 됨
*/

public class Lv2_혼자_놀기의_달인 {
    public int solution(int[] cards) {
        int answer = 0;
        int[] count = new int[cards.length];
        Arrays.fill(count, 0);

        for (int i=0; i < cards.length; i++) {
            int num = 0;
            int idx = i;
            while (cards[idx] != 0) {
                num++;
                int temp = cards[idx] - 1;  // 1-base to 0-base
                cards[idx] = 0;
                idx = temp;
            }
            count[i] = num;
        }

        // 스트림으로 만든 후 박싱(Integer로), 이후 내림차순 정렬 후 다시 int 타입의 배열로 만들어서 반환.
        count = Arrays.stream(count).boxed().sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue).toArray();

        return count[0] * count[1];
    }
}