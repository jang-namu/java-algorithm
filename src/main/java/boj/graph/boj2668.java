// 2668 숫자 고르기
/*
    Set이나 재귀를 이용하면 좀 더 편하다.
    현재 한번에 하나의 원소만을 추가하는 데 비해, 한번에 루프 성공 시 해당 루프의 원소를 모두 추가하고
    밖에 for문에서 visited를 체크해서 이미 포함된 항목은 skip하도록 개선할 수 있다.
 */
package boj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class boj2668 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n+1];

        for (int i=1; i < n+1; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        var list = new LinkedList<>();
        for (int i=1; i < n+1; i++) {
            int idx = i;
            int count = 0;
            while (nums[idx] != i) {
                idx = nums[idx];
                count++;
                if (count > 100) {
                    break;
                }
            }

            if (count < 100) {
                list.add(i);
            }
        }
        System.out.println(list.size());
        list.forEach(i -> {
            System.out.println(i);
        });
    }
}
