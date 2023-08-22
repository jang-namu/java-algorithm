package programmers.lv2;

/*
    2023.08.22
    left와 right가 long이고 (right - left)는 int 범위에 들어간다는 것에 주의한다. 반환값은 int[]이다.

    1 2 3
    2 2 3
    3 3 3
    
    위와 같은 방식으로 2차원 배열이 생성된다.
    i행의 원소는 i열까지는 'i'라는 값을 갖는다.
    그 이후 열에 해당하는 값을 갖는다. (3행 4열 = 4, 2행 5열 = 5....)
 */

public class Lv2_n제곱_배열_자르기 {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left) + 1];

        for (long i = left; i <= right; i++) {
            int o = (int)(i / n);
            int m = (int)(i % n);

            int idx = (int)(i - left);
            if (o >= m) {
                answer[idx] = o + 1;
            } else {
                answer[idx] = m + 1;
            }
        }

        return answer;
    }
}