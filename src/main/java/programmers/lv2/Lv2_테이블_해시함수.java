package programmers.lv2;

import java.util.stream.*;
import java.util.*;

public class Lv2_테이블_해시함수 {
}


/*
    정렬 후, 문제 설명과 인덱스의 관계를 잘 생각해서 짜면된다.
*/


class Solution2321425 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {

        /*
            'int[][]::new'를 주의깊게 보자. (이유는 그냥 신기해서.)
            toArray()에 'int[][]::new'를 써주지 않으면, 최종결과가 Object[]를 반환한다.
        */
        int[][] temp = Stream.of(data).sorted((i, j) -> {
            if (i[col-1] > j[col-1]) {
                return 1;
            } else if (i[col-1] < j[col-1]) {
                return -1;
            } else {
                return (i[0] >= j[0]) ? -1 : 1;
            }
        }).toArray(int[][]::new);

        int S_i = 0;
        for (int i=0; i < temp[row_begin-1].length; i++) {
            S_i += temp[row_begin-1][i] % (row_begin);
        }
        for (int i=row_begin; i < row_end; i++) {
            int now = 0;
            for (int j=0; j < temp[i].length; j++) {
                now += temp[i][j] % (i+1);
            }
            System.out.println(now + " " + S_i);
            S_i ^= now;
        }

        return S_i;
    }
}