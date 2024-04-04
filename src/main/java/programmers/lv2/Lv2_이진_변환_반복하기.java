package programmers.lv2;

public class Lv2_이진_변환_반복하기 {
    int[] answer = {1, 0};

    private int trans(int num) {
        int next = 0;
        while(num >= 2) {
            if (num % 2 == 0) {
                answer[1]++;
            } else {
                next++;
            }
            num /= 2;
        }
        if (num != 0) next++;
        return next;
    }

    public int[] solution(String s) {

        int num = 0;
        for (char i : s.toCharArray()) {
            if (i == '0')
                answer[1]++;
            else
                num++;
        }
        while (num > 1) {
            num = trans(num);
            answer[0]++;
        }

        return answer;
    }
}