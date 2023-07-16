package programmers.lv2;

import java.util.*;


public class Lv2_택배_배달과_수거하기 {
}

/*
    deliveries = goIdx, goCap, goDistnace, sign1
    pickups = backIdx, backCap, backDistance, sign2

    배열의 뒷부분부터 먼저 해결하면서 앞으로 온다.
    [xx]Idx: 배열에서 현재 해결중인 부분의 인덱스
    [xx]Cap: 현재 iteration(전체 while문)에서 더 처리할 수 있는 박스 수(capacity)
    [xx]Distance: 현재 iteration에서 들려야 할 가장 먼 집의 위치 (default = -1, 더 이상 들릴 집이 없는 경우.)
    sign1, sign2: true = 각각 deli~, pick~을 모두 둘러봄. false = 아직 둘러볼 집이 더 남았다.
*/


class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int goIdx = n-1; int backIdx = n-1;

        while (true) {
            int goCap = cap; int backCap = cap;
            int goDistance = -1; int backDistance = -1;

            boolean sign1 = true;
            for (int i=goIdx; i >= 0; i--) {
                if (deliveries[i] == 0) {
                    goIdx = i;
                    continue;
                }
                if (goCap == cap) goDistance = i+1;
                if (goCap - deliveries[i] < 0) {
                    deliveries[i] -= goCap;
                    goIdx = i;
                    sign1 = false;
                    break;
                } else {
                    goCap -= deliveries[i];
                    deliveries[i] = 0;
                }
            }

            boolean sign2 = true;
            for (int i=backIdx; i >= 0; i--) {
                if (pickups[i] == 0) {
                    backIdx = i;
                    continue;
                }
                if (backCap == cap) backDistance = i+1;
                if (backCap - pickups[i] < 0) {
                    pickups[i] -= backCap;
                    backIdx = i;
                    sign2 = false;
                    break;
                } else {
                    backCap -= pickups[i];
                    pickups[i] = 0;
                }
            }

            // 테스트 2: if 위에 go, back 모두에서 0인경우 즉, deliveries와 pickups이 모두 0 인경우,
            // goDistnace와 backDistance는 모두 -1이므로 answer에 이상한 값을 더하게된다.
            // 이를 위한 예외처리
            if (goDistance != -1 || backDistance != -1)
                answer += Math.max(goDistance, backDistance) * 2;

            if (sign1 && sign2) {
                break;
            }
        }
        return answer;
    }
}