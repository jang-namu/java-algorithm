package programmers.lv3;
// 정수 삼각형
public class pro43105 {
    public int solution(int[][] triangle) {

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    triangle[i][j] += triangle[i-1][0];
                } else if (j == i) {
                    triangle[i][j] += triangle[i-1][j-1];
                } else {
                    triangle[i][j] += Math.max(triangle[i-1][j-1],
                            triangle[i-1][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < triangle.length; i++) {
            if (answer < triangle[triangle.length-1][i])
                answer = triangle[triangle.length-1][i];
        }

        return answer;
    }
}