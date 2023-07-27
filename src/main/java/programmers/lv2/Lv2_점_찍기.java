package programmers.lv2;

public class Lv2_점_찍기 {
    /*
        프로그래머스 정답, 피타고라스 정리
        삼각형의 대각선의 제곱에서 높이 i의 제곱을 빼고 제곱근(sqrt)을 구한다.
        제곱근 / k + 1개가 높이 i일 때, 가능한 x의 개수;
     */
    public long solution(int k, int d) {
        long answer = 0;

        long powD = (long)d * d;

        for (long i = 0; i <= d; i = i + k) {
            answer += (long)Math.sqrt(powD - i * i) / k + 1;
        }

        return answer;
    }
}

/*
    제곱을 해서 양변을 비교할 때,
    1,000,000의 제곱은 int의 범위를 벗어난다.
    따라서 x, y, d의 제곱은 long으로 선언.
*/
class Solution13239qwe {
    public long solution(int k, int d) {
        long answer = 0l;

        long powD = (long)d * d;
        long high = (long)k * (d / k);

        long y = high;
        for (long x = 0l; x <= high; x = x + k) {
            if (y * y + x * x <= powD) {
                answer += y / k + 1;
                continue;
            }

            do {
                y -= k;
            } while (y * y + x * x > powD);

            answer += y / k + 1;
        }

        return answer;
    }
}