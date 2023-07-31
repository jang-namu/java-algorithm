package programmers.lv3;

/*
    유명한 문제 좌측 상단부터 -> 우측상단 -> 바로 밑에줄 좌측 상단 -> 우측 ...으로 이동하며
    '지나온 동전은 더 이상 뒤집을 수 없다'라는 제약을 건다.
*/

public class Lv3_2차원_동전_뒤집기 {

    public int solutionHelper(int[][] beginning, int[][] target) {
        int answer = 0;

        for (int j=0; j < beginning[0].length; j++) {
            if (beginning[0][j] != target[0][j]) {
                answer++;
                for (int k=0; k < beginning.length; k++) {
                    // 같은 열의 동전을 모두 뒤집는다.
                    beginning[k][j] = (beginning[k][j] + 1) % 2;
                }
            }
        }

        for (int i=1; i < beginning.length; i++) {
            // for (int j=0; j < beginning[0].length; j++) {
            // 1열만이 행을 뒤집을 수 있다.
            for (int j=0; j < 1; j++) {     // for문 없애고 if (beginning[i][0] != target[i][0])과 같음.
                if (beginning[i][j] != target[i][j]) {
                    answer++;
                    for (int k=0; k < beginning[i].length; k++) {
                        // 같은 행의 동전을 모두 뒤집는다.
                        beginning[i][k] = (beginning[i][k] + 1) % 2;
                    }
                }
            }
        }

        for (int i=1; i < beginning.length; i++) {
            for (int j=0; j < beginning[0].length; j++) {
                if (beginning[i][j] != target[i][j]) {
                    return -1;
                }
            }
        }

        return answer;
    }
    public int solution(int[][] beginning, int[][] target) {
        int answer = 0;

        /*
        //실패
        if (beginning[0][0] != target[0][0]) {
            int[][] begin = new int[beginning.length][];
            int[][] end = new int[target.length][];

            for (int i=0; i < beginning.length; i++) {
                begin[i] = new int[beginning[0].length];
                begin[i] = beginning[i].clone();
                end[i] = new int[target[0].length];
                end[i] = target[i].clone();
            }
            for (int j=0; j < begin[0].length; j++) {
                begin[0][j] = (begin[0][j] + 1) % 2;
            }

            answer = solutionHelper(beginning, target);

            int answer2 = solutionHelper(begin, end) + 1;   //
            System.out.println(answer + " " + answer2);
            if (answer2 != 0 && ((answer == -1) || (answer > answer2))) {
                answer = answer2;
            }
        } else {
            answer = solutionHelper(beginning, target);
        }
        */

        // 1행 1열(첫번째)이 다르면? -> 가로로 뒤집었을 때 한 번, 세로로 뒤집었을 때 한 번
        /*
            !!!!! => but, 1행 1열(첫번째칸)은 행을 뒤집을수도 열을 뒤집을수도 있다.
            물론! 행과 열 둘다 뒤집을 수도 있다. 위의 풀이로 1행 1열이 같을 때와 다를 때를 나눠 풀었을 때,
            (다를 때만 복사 배열을 만들어서 함수 두번 호출 했음)
            간과한 것은 1행 1열이 같아도 행, 열 둘다 뒤집을 수 있다는 것.
            따라서 1행 1열이 같거나 다르거나 나누지 않고 모든 경우의 복사배열을 만들어 함수 두번 호출

            1행 1열은 행, 열을 모두 뒤집을 수 있다.
            1행은 열을 뒤집을 수 있다.
            1열은 행을 뒤집을 수 있다.
            나머지 칸에서는 모두 이전에 맞춰놓은 칸을 깨버리므로 그 무엇도 뒤집을 수 없다.
        */
        int[][] begin = new int[beginning.length][];
        int[][] end = new int[target.length][];

        for (int i=0; i < beginning.length; i++) {
            begin[i] = new int[beginning[0].length];
            begin[i] = beginning[i].clone();
            end[i] = new int[target[0].length];
            end[i] = target[i].clone();
        }
        for (int j=0; j < begin[0].length; j++) {
            begin[0][j] = (begin[0][j] + 1) % 2;
        }

        answer = solutionHelper(beginning, target);

        int answer2 = solutionHelper(begin, end) + 1;   // begin, end는 이미 1행을 한번 뒤집은 것, 1회 더해야함
        System.out.println(answer + " " + answer2);
        // answer2 == 0? => 함수호출결과 -1이라는 뜻.
        if (answer2 != 0 && ((answer == -1) || (answer > answer2))) {
            answer = answer2;
        }
        return answer;

    }
}