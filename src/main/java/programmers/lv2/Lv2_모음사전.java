package programmers.lv2;

/*
    2023.08.23
    각 자리는 기본적으로 a e i o u 5가지의 경우의수가 있다.
    다만, 각 자리는 없을 수도 있다. but, 이를 6가지 경우의수로 계산하면 안됨.
    단순히 6 x 6 x 6 x 6 x 6을 할 경우, 앞에 수가 존재하지 않는데 뒷수는 존재하는 경우까지 세버린다.

    예를들어 "I" 라는 문자 앞에는
    A : 1
    A[] : 5 x 5
    A[][] : 5 x 5 x 5
    A[][][] : 5 x 5 x 5 x 5
    A[][][][] : 5 x 5 x 5 x 5 x 5
    = 1 + 780

    맨앞이 B의 경우 ~ : 781개

    즉, 781 * 2 = 1562개의 문자가 있고
    "I"는 1563번째가 된다.
 */
public class Lv2_모음사전 {
    public int alphabet(char ch) {
        switch (ch) {
            case 'A':
                return 0;
            case 'E':
                return 1;
            case 'I':
                return 2;
            case 'O':
                return 3;
            case 'U':
                return 4;
        }
        return -1;
    }
    public int solution(String word) {
        int answer = 0;
        char[] chs = word.toCharArray();

        for (int i=chs.length - 1; i >= 0; i--) {
            int n = alphabet(chs[i]);

            for (int j = 4-i; j >= 0; j--) {
                answer += n * Math.pow(5, j);
            }
            answer++;
        }

        return answer;
    }
}