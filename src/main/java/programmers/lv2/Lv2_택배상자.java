package programmers.lv2;

import java.util.*;

/*
  보조 컨테이너 벨트 = 스택
  order가 현재 들어오는 box보다 크면 현재 박스를 스택에 저장
  box보다 작으면 스택에 맨위를 찾아본다.
  스택에 맨위에 없으면 실패!
*/
public class Lv2_택배상자 {
    public int solution(int[] order) {
        int answer = 0;

        var stack = new Stack<Integer>();

        int box = 1;
        for (int od : order) {
            while (od > box) {
                stack.push(box++);
            }
            if (od == box) {
                answer++;
                box++;
            } else if (od == stack.peek()) {
                answer++;
                stack.pop();
            } else {
                break;
            }
        }

        return answer;
    }
}