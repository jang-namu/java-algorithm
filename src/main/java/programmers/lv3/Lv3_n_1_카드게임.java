package programmers.lv3;

// https://school.programmers.co.kr/learn/courses/30/lessons/258707?language=java
public class Lv3_n_1_카드게임 {
    public int solution(int coin, int[] cards) {

        int target = cards.length + 1;
        int round = (cards.length - (cards.length / 3)) / 2;

        int[] myCards = new int[cards.length + 1]; // 카드를 얻을 수 있는 라운드 정보
        int[] cost = new int[cards.length + 1]; // 카드를 얻는데 드는 비용
        for (int i = 0; i < cards.length; i++) {
            if (i < (cards.length / 3)) {
                myCards[cards[i]] = 1;
                cost[cards[i]] = 0;
            } else {
                myCards[cards[i]] = (i - (cards.length / 3)) / 2 + 1;
                cost[cards[i]] = 1;
            }
        }

        // n이 짝수이므로, n+1 = 홀수 + 짝수로 나타낼 수 있음
        // 홀수 + 짝수 == n+1을 만족하는 홀수/짝수 쌍에 대해 홀수를 기준으로 정리
        // oper[][0]: 쌍을 획득할 수 있는 시점, oper[][1]: 쌍을 얻는데 드는 비용
        int[][] oper = new int[cards.length + 1][2];
        for (int i = 1; i < cards.length + 1; i += 2) {
            oper[i][0] = Math.max(myCards[i], myCards[target - i]);
            oper[i][1] = cost[i] + cost[target - i];
        }

        // operByRound[1]: 1라운드에 추가되는 쌍의 개수와 비용
        // operByRound[1][0]: 1라운드에 추가되는 비용 0짜리 쌍의 개수
        // operByRound[1][1]: 1라운드에 추가되는 비용 1짜리 쌍의 개수
        int[][] operByRound = new int[round + 1][3];
        for (int i = 1; i < cards.length + 1; i += 2) {
            operByRound[oper[i][0]][oper[i][1]] += 1;
        }

        // 최초 상태 [0, 0, 0] 부터 시작해
        // 1라운드 부터 각 비용-쌍을 누적함
        // 각 라운드마다 최소 비용으로 넘길 수 있는 조건을 사용
        int[] current = new int[3];
        int maxRound = 1;
        for (int i = 1; i < round + 1; i++) {
            for (int j = 0; j < 3; j++) {
                current[j] += operByRound[i][j];
            }

            if (current[0] > 0) {
                current[0] -= 1;
                maxRound = i + 1;
                continue;
            }
            if (coin > 0 && current[1] > 0) {
                current[1] -= 1;
                coin--;
                maxRound = i + 1;
                continue;
            }

            if (coin > 1 && current[2] > 0) {
                current[2] -= 1;
                coin -= 2;
                maxRound = i + 1;
                continue;
            }
            break;
        }

        return maxRound;
    }
}