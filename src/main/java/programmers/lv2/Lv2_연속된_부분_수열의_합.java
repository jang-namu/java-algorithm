package programmers.lv2;
/*
처음 풀이에는 min_length를 유지해서 min_length - 1보다 더 커질경우 removeFirst() 했다.
=> 문제: 마지막 한 요소가 k 라면??

위치도 중요. 처음풀이에선 위에 min_length-1 과 sum > k를 for문 처음에 사용.
=> 문제: min_length가 4인 상태에서 3개의 원소로 k를 달성하면?
    -> for문을 돌며 min_legnth-1을 통해 앞의 원소 하나가 빠져버린다.
    
    
수정: deque의 사이즈는 if (sum==k) 내부에서 같이 확인한다.
*/

import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, 1000000};
        int min_length = 1000001;

        int sum = 0;
        Deque<Integer> deque = new LinkedList<>();

        for (int i=0; i < sequence.length; i++) {
            deque.addLast(i);
            sum += sequence[i];

            while (sum > k) {
                sum -= sequence[deque.removeFirst()];
            }

            if (sum == k && deque.size() < min_length) {
                min_length = deque.size();
                answer[0] = deque.getFirst();
                answer[1] = deque.getLast();
            }
        }

        return answer;
    }
}