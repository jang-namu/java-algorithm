package programmers.lv2;

import java.util.*;

public class Lv2_뒤에_있는_큰_수_찾기 {
}
/*
    주어진 numbers를 역순으로 읽는다. 스택을 사용하여 문제를 해결했다.

    numbers를 역순으로 확인하면서 올 때, if 스택이 비어있으면 뒤에 더 큰 숫자가 없다. 따라서 -1이 되고 현재 수가 가장 크므로 스택에 집어넣는다.
    if 스택이 비어있지 않으면, 지금 스택에 맨 위에 있는 요소가 현재 확인하고 있는 요소보다 큰지 확인해야 된다. 만일 true라면 그 원소를 answer[i]에 넣고 현재 값도 스택에 넣는다.
    만일 스택 최상단 요소가 더 작을 경우에는 그 요소는 더 이상 필요가 없다. (현재 확인하고 있는 요소가 그보다 더 뒤에오고, 작은 요소를 무시한다.)
    따라서 해당 요소는 pop하게 된다. 언제까지? 스택이 비거나(이런 경우 -1이 들어간다.) 스택에 최상단 요소가 현재요소보다 더 클 때까지.
*/

class Solution4141 {
    public int[] solution(int[] numbers) {
        var st1 = new Stack<Integer>();

        int[] answer = new int[numbers.length];
        Arrays.fill(answer, 0);

        for (int i = numbers.length - 1; i >= 0; i--) {
            while (st1.size() != 0) {
                if (st1.peek() > numbers[i]) {
                    answer[i] = st1.peek();
                    st1.push(numbers[i]);
                    break;
                } else {
                    while (st1.size() > 0 && st1.peek() <= numbers[i]) {
                        st1.pop();
                    }
                }
            }

            if (answer[i] == 0) {
                answer[i] = -1;
                st1.push(numbers[i]);
            }
        }
        return answer;
    }
}