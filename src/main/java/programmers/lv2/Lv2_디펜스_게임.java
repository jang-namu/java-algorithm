package programmers.lv2;

import java.util.*;

public class Lv2_디펜스_게임 {
}

/*
    현재 n으로 깰 수 있는 라운드까지 간다. 가면서 그동안에 enemy[i]들을 최대힙에 넣는다.
    더 이상 깰 수 없을 때, 최대힙에서 뺀만큼 n을 회복시킨다.(무적권 사용) => k -= 1
*/


class Solution1230123qwelj {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;

        var a = new PriorityQueue<Integer>();

        int i = -1;
        for (i=0; i < enemy.length; i++) {
            n -= enemy[i];
            a.add(-enemy[i]);
            if (n >= 0) {
                continue;
            }

            while ((n < 0) && (k > 0)) {
                n += -a.poll();
                k--;
            }

            if (n < 0) {
                break;
            }
        }
        answer = i;

        return answer;
    }
}