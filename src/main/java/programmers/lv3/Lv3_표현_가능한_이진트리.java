package programmers.lv3;

import java.util.Arrays;

/*
리프가 아닌 노드가 1이어야 한다.
리프가 아닌 노드가 0이면 그 노드의 자식 서브트리는 모두 0이어야 한다.
 */
public class Lv3_표현_가능한_이진트리 {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, 1);     // 기본값으로 1설정

        int idx = 0;    // answer의 인덱스
        for (long number: numbers) {
            // System.out.println(Long.toString(number, 2));
            String formatted = Long.toString(number, 2);
            int length = formatted.length();
            int depth = 1;  // 포화 이진트리의 크기는 1 -> 3 -> 7 -> 15 ... depth는 높이를 의미
            while ((1<<depth) - 1 < length) {
                depth++;
            }

            // 포화 이진트리를 완성(0은 기존 수에 왼쪽에만 추가될수 있음)
            StringBuilder sb = new StringBuilder(formatted);
            for (int i = 0; i < (1<<depth) - 1 - length; i++) {
                sb.insert(0, "0");
            }

            int newLength = sb.length();

            int pos = 1;    // 홀수 번쨰만 검사(리프가 아닌 노드는 다 홀수번호임)
            while (pos < newLength) {
                /*
                1. 현재 위치가 1인 경우 -> 통과
                2. 현재 위치가 0인 경우 -> 현재 위치를 기준으로 양쪽 서브트리의 원소가 모두 0인 경우에만 통과
                 */
                if (sb.charAt(pos) == '0') {
                    int end = newLength / 2;
                    if (pos == end) {   // 현재 위치가 그래프의 중심인 경우 -> 무조건 틀림
                        answer[idx] = 0;
                        break;
                    }

                    int bias = depth - 1;   // 깊이를 통해 좌/우 서브트리의 크기를 구함
                    while (pos != end) {
                        bias--;
                        int tmp = 1<<bias;
                        if (pos > end) {
                            end += tmp;
                        } else {
                            end -= tmp;
                        }
                    }
                    int gap = 1<<bias - 1;  // 한 쪽 서브트리의 크기
                    for (int i = pos - gap; i <= pos + gap; i++) {
                        if (sb.charAt(i) == '1') {
                            answer[idx] = 0;
                            break;
                        }
                    }
                }
                pos+=2;
                if (answer[idx] == 0) break;    // 이미 틀렸으면 반복문 종료
            }
            idx++;
        }

        return answer;
    }
}