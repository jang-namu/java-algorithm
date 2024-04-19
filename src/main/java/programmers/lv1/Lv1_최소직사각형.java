package programmers.lv1;

/*
사실 이 문제는 Math.max와 Math.min을 사용하면 더 깔끔하다.
**근데 내꺼가 더 빠름.**

    public int solution(int[][] sizes) {
        int x=0, y=0;
        for (int[] size : sizes) {
            x = Math.max(x, Math.max(size[0], size[1]));
            y = Math.max(y, Math.min(size[0], size[1]));
        }
        return x * y;
    }
 */


public class Lv1_최소직사각형 {

    public int solution(int[][] sizes) {
        int x=0, y=0;
        for (int[] size : sizes) {
            boolean bigFirst = size[0] > size[1] ? true : false;
            if (bigFirst) {
                if (x < size[0]) {
                    x = size[0];
                }
                if (y < size[1]) {
                    y= size[1];
                }
            } else {
                if (x < size[1]) {
                    x = size[1];
                }
                if (y < size[0]) {
                    y= size[0];
                }
            }
        }

        return x * y;
    }
}