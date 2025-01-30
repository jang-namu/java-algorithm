package programmers.lv2;

// https://school.programmers.co.kr/learn/courses/30/lessons/68645

/*
Left(아래), Under(오른쪽), Right(위) 방향으로 채워나간다.

개선점: Left, Under, Right로 순환하며 값을 채워나갈 때, 이를 잘 살펴보면 최초 N개, N-1개, N-2개 ... 마지막 1개로
한 턴에 채우는 값이 하나씩 줄어듬을 알 수 있다.
이를 사용해서 coloring을 대체하고, 알기 어려운 depth를 유지하는 것보다 더 쉬운 방법으로 알고리즘 개선이 가능하다.
 */
public class Lv2_삼각_달팽이 {
    private int coloring = 0;
    private int idx = 0;
    private int number = 1;
    private int depth = 0;

    public int[] solution(int n) {
        int[] answer = new int[(n * (n+1)) / 2];
        fill(answer);
        return answer;
    }

    private void fill(int[] answer) {
        while (coloring < answer.length) {
            fillLeft(answer);
            fillUnder(answer);
            fillRight(answer);
        }
    }

    // 1회차, idx:0 / number:1
    private void fillLeft(int[] answer) {
        while (idx + depth < answer.length) {
            if (answer[idx + depth] != 0) {
                break;
            }
            idx = idx + depth++;
            answer[idx] = number++;
            coloring++;
        }
    }

    private void fillUnder(int[] answer) {
        for (int i = 0; i < depth; i++) {
            if (idx + 1 < answer.length && answer[idx + 1] == 0) {
                idx++;
                answer[idx] = number++;
                coloring++;
                continue;
            }
            break;
        }
    }

    private void fillRight(int[] answer) {
        for (; depth > 0; depth--) {
            if (idx - depth > 0 && answer[idx - depth] == 0) {
                idx = idx - depth;
                answer[idx] = number++;
                coloring++;
                continue;
            }
            break;
        }
    }

}