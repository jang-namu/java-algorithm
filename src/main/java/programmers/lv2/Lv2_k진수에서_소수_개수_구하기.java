package programmers.lv2;

import java.util.ArrayList;
import java.util.List;

// 3^13 > 1500000 -> 아무리 길어도 13자 2_222_222_222_222
// 10진수에서 13자까지의 소수를 가져와야함? 너무 느린데 => 소수를 먼저 구하지말고, 패턴에 일치하는 수가 소수인지 판단
// 소수도 각 자릿수가 k 보다 작아야함

/*
1. k진수 표현으로 변환
2. X, X0, 0X, 0X0 패턴에 맞는 '모든 자연수 X' 구하기
3. 2에서 구한 자연수가 소수인지 판단

소수 판별 알고리즘은 다음을 참고 https://www.geeksforgeeks.org/java-prime-number-program/
처음 알았는데, 모든 소수는 2와 3을 제외하고 (6N + 1) or (6N - 1)의 형태를 이룬다고 한다.

by Claude
'소수의 휠 인수분해'라고 한다는데,

k=자연수일 때, 모든 자연수는
6k + 0
6k + 1
6k + 2
6k + 3
6k + 4 = 6k - 2
6k + 5 = 6k - 1
위와 같이 표현할 수 있다.

6은 2와 3의 배수이므로
6k -> 2와 3의 배수이므로 소수가 아님
6k + 2 -> 2의 배수
6k + 3 -> 3의 배수
6k + 4 -> 2의 배수
따라서 2와 3을 제외한 모든 소수는
'6k + 1'
6k + 5 = '6k - 1'로 나타낼 수 있다.

From 프로그래머스 답
Integer.toString(int i, int radix)으로 k진수 표현
"0"을 기준으로 split하면 모든 candidates를 얻을 수 있다.

class Solution {
    public int solution(int n, int k) {

        int ans = 0;
        String temp[] = Integer.toString(n, k).split("0");

        Loop : for(String t : temp) {
            if(t.length() == 0) continue;
            long a = Long.parseLong(t);
            if(a == 1) continue;
            for(int i=2; i<=Math.sqrt(a); i++)
                if(a%i == 0) continue Loop;

            ans++;
        }
        return ans;
    }
}
 */
public class Lv2_k진수에서_소수_개수_구하기 {
    public int solution(int n, int k) {

        String expression = toKExpression(n, k);
        // System.out.println(expression);
        int answer = countPrimeOnRules(expression);
        return answer;
    }

    private String toKExpression(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n = n / k;
        }
        return sb.reverse().toString();
    }

    private boolean isPrime(long k) {
        if (k == 1) return false;
        if (k == 2 || k == 3) return true;
        if (k % 2 == 0 || k % 3 == 0) return false;
        for (long i = 5; i <= Math.sqrt(k); i += 6) {
            if (k % i == 0 || k % (i + 2) == 0)
                return false;
        }
        return true;
    }

    private int countPrimeOnRules(String expression) {
        int count = 0;
        List<Long> candidates = infer(expression);

        for (Long candidate: candidates) {
            // System.out.println(candidate);
            if (isPrime(candidate)) {
                count++;
            }
        }
        return count;
    }

    private List<Long> infer(String expression) {
        List<Long> candidates = new ArrayList<>();
        char[] parsed = expression.toCharArray();

        // isRightZero: P0
        StringBuilder sb = new StringBuilder();
        for (char ch: parsed) {
            if (ch == '0') {
                break;
            }
            sb.append(ch);
        }
        if (sb.length() > 0 && sb.length() < parsed.length) {
            candidates.add(Long.parseLong(sb.toString()));
        }

        // isLeftZero: 0P
        sb = new StringBuilder();
        for (int i=parsed.length-1; i >= 0; i--) {
            if (parsed[i] == '0') {
                break;
            }
            sb.append(parsed[i]);
        }
        if (sb.length() > 0 && sb.length() < parsed.length) {
            candidates.add(Long.parseLong(sb.reverse().toString()));
        }

        // notContainsZero: P
        if (!expression.contains("0")) {
            candidates.add(Long.parseLong(expression));
        }

        // between Zero: 0P0
        List<Long> betweens = inferBetweenZero(parsed);
        candidates.addAll(betweens);
        return candidates;
    }

    private List<Long> inferBetweenZero(char[] parsed) {
        List<Long> betweens = new ArrayList<>();

        boolean start = false;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < parsed.length; i++) {
            if (parsed[i] == '0') {
                if (start) {
                    if (sb.length() > 0) {
                        betweens.add(Long.parseLong(sb.toString()));
                        sb = new StringBuilder();
                    }
                    // start = false; // 한 candidate의 끝은, 다음 candidate의 시작
                    // ex) 02'0'20  -> 가운데 '0'은 다음 소수의 시작
                } else {
                    start = true;
                }
                continue;
            }
            if (start) {
                sb.append(parsed[i]);
            }
        }
        return betweens;
    }

}