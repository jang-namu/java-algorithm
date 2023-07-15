//중복 빼고 정렬하기
package boj.sort;

import java.io.*;
import java.util.*;

public class boj10867 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var set = new HashSet<Integer>();
        br.readLine();

        var st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        set.stream().sorted(Integer::compareTo).forEach(ele -> {
            System.out.print(ele + " ");
        });
    }
}
