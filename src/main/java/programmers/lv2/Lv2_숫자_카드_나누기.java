package programmers.lv2;
import java.util.*;

public class Lv2_숫자_카드_나누기 {
}

/*
    모두 나눠진다 => 최대공약수의 약수들 중 하나

    1. 철수와 영희가 가진 카드들의 각각 최대공약수를 구한다. gcdA, gcdB
    2. 각각의 최대공약수의 약수를 모두 구한다. numsA, numsB
    3. numsA, numsB를 내림차순으로 정렬한다.
    4. numsA, numsB를 각각 앞에서부터 살펴보면서 상대방의 카드를 나눌수없는 가장 큰 수를 찾는다.
        => resA, resB
    5. resA, resB 중 큰것이 정답. 둘 다 -1(초기값)이면 0
*/

class Solutioneqwlke2309 {
    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        int gcdA = arrayA[0];
        for (int i=1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
        }

        int gcdB = arrayB[0];
        for (int i=1; i < arrayB.length; i++) {
            gcdB = gcd(gcdB, arrayB[i]);
        }

        List<Integer> numsA = new ArrayList<Integer>();
        for (int i = gcdA; i >= (int)Math.sqrt(gcdA); i--) {
            if (gcdA % i == 0) {
                numsA.add(i);
                numsA.add(gcdA/i);
            }
        }
        Collections.sort(numsA, Collections.reverseOrder());

        List<Integer> numsB = new ArrayList<Integer>();
        for (int i = gcdB; i >= (int)Math.sqrt(gcdB); i--) {
            if (gcdB % i == 0) {
                numsB.add(i);
                numsB.add(gcdB/i);
            }
        }
        Collections.sort(numsB, Collections.reverseOrder());

        int resB = -1;
        for (int i : numsA) {
            boolean sign = true;
            for (int j : arrayB) {
                if (j % i == 0) {
                    sign = false;
                    break;
                }
            }
            if (sign) {
                resB = i;
                break;
            }
        }

        int resA = -1;
        for (int i : numsB) {
            boolean sign = true;
            for (int j : arrayA) {
                if (j % i == 0) {
                    sign = false;
                    break;
                }
            }
            if (sign) {
                resA = i;
                break;
            }
        }

        if (resA > resB) {
            answer = resA;
        } else {
            answer = resB;
        }
        return (answer == -1) ? 0 : answer;
    }
}