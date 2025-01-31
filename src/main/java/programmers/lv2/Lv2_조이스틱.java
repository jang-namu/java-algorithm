package programmers.lv2;

import java.util.Arrays;
import java.util.LinkedList;

/*
해설: 두 부분으로 나눈다.
1. 각 위치에 문자를 target의 대응되는 문자로 바꾸는 거리
2. 필요한 모든 문자를 최소한 한 번씩 방문하는 거리

1은 굉장히 쉽다. calculateCharDistance
이 문제의 핵심은 2번이다.

나는 bfs를 통해 커서 이동방식(MOVE 이용)을 통해 최단거리를 구했다.
but, 이 방법에는 메모리가 많이들고 느리다.

아래는 프로그래머스에서 가져온 다른 사람의 풀이다.
주된 아이디어는 2의 최소 횟수를 구할 때 연이은 "A" 갯수를 활용한다는 것이다.
1. 최소횟수는 한방향으로 모든 위치를 들리는 경우보다 무조건 작다. -> 'length -1'
2. 한 방향으로 모든 위치를 들리지 않을 경우, 오른쪽으로 갔다가 왼쪽으로 돌아가거나 또는 그 반대의 경우밖에 없다.
3. 한 방향에서 다른 방향으로 바꾸는 경우, 짧은 쪽을 먼저가는 것이 좋다. -> 결국, 돌아가기 때문에 먼저가는 쪽을 두번 거치게된다.
4. 아래 코드 상의 주석 참조

// 프로그래머스 답
class Solution {
    public int solution(String name) {
        int answer = 0;
        int[] diff={0,1,2,3,4,5,6,7,8,9,10,11,12,13,12,11,10,9,8,7,6,5,4,3,2,1};
        for(char c:name.toCharArray())
            answer+=diff[c-'A'];

        int length=name.length();
        int min=length-1;

        for(int i=0;i<length;i++){
            int next=i+1;
            while(next<length && name.charAt(next)=='A'){
                next++;
            }

            // 4.
            // 원본의 길이가 -> "---XAA--" 일 경우
            // i + length - next -> (좌측) i:"----"  (우측)length-next:"--"
            // Math.min(i, length-next) -> 위 두 거리 중 짧은 것을 고른다.(먼저 왔다갈 쪽)
            min=Math.min(min,i+length-next + Math.min(i,length-next));
        }

        return answer+min;
    }
}
 */
public class Lv2_조이스틱 {
    public int solution(String name) {
        char[] parsed = name.toCharArray();
        char[] original = init(parsed.length);

        int answer = 0;

        for (char ch : parsed) {
            answer += calculateCharDistance('A', ch);
        }
        answer += minHorizontalMove(original, parsed);

        return answer;
    }

    private char[] init(int length) {
        char[] original = new char[length];
        for (int i = 0; i < length; i++) {
            original[i] = 'A';
        }
        return original;
    }

    private int calculateCharDistance(char original, char target) {
        int distance = target - original;
        if (distance > 13) {
            return 26 - distance;
        }
        return distance;
    }

    private static class Point {
        int x;
        boolean[] checked;

        public Point(int x, boolean[] checked) {
            this.x = x;
            this.checked = Arrays.copyOf(checked, checked.length);
        }
    }

    private int minHorizontalMove(char[] original, char[] target) {
        // init
        boolean[] checked = new boolean[original.length];
        checked[0] = true;
        for (int i = 1; i < target.length; i++) {
            if (target[i] == 'A') {
                checked[i] = true;
            }
        }

        int distance = 0;
        if (isFinished(checked)) return distance; // 모두 A인 경우 (idx=0는 신경쓰지 않아도 됨)

        // bfs
        LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(0, checked));
        while (!points.isEmpty()) {
            distance++;
            LinkedList<Point> newPoints = new LinkedList<>();
            while (!points.isEmpty()) {
                Point point = points.poll();

                // 좌측 이동
                int xLeft = left(point.x, target.length);
                boolean[] left = visit(xLeft, point.checked);
                if (isFinished(left)) return distance;
                newPoints.add(new Point(xLeft, left));
                // 우측 이동
                int xRight = right(point.x, target.length);
                boolean[] right = visit(xRight, point.checked);
                if (isFinished(right)) return distance;
                newPoints.add(new Point(xRight, right));
            }
            points = newPoints;
        }
        return -1;
    }

    private boolean isFinished(boolean[] arr) {
        for (boolean flag: arr) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    private boolean[] visit(int idx, boolean[] target) {
        boolean[] copied = Arrays.copyOf(target, target.length);
        copied[idx] = true;
        return copied;
    }

    private int left(int idx, int length) {
        return (idx + length - 1) % length;
    }

    private int right(int idx, int length) {
        return (idx + 1) % length;
    }
}