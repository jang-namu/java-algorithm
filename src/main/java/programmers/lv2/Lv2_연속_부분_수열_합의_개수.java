package programmers.lv2;

import java.util.*;

public class Lv2_연속_부분_수열_합의_개수 {
    public static void main(String[] args) {
        int[] ele = {7, 9, 1, 1, 4};
        System.out.println(solution(ele));
    }
    public static int solution(int[] elements) {
        // set으로 중복된 요소 처리
        var set = new HashSet<Integer>();
        for (int i=0; i < elements.length; i++) {
            int sum = 0;
            // 거꾸로 돌아서도 가능 [1, 1, 4, 7, 9]이면
            // 4, 7, 9, 1도 가능하므로 모듈로연산으로 가능한 경우의수 모두 계산
            for(int j=0; j < elements.length - 1;j++) {
                int k = (i + j) % elements.length;
                sum += elements[k];
                set.add(sum);
            }
        }
        // 배열의 모든 수를 포함했을 경우 + 1개
        return set.size() + 1;

    }
}