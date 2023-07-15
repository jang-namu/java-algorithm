//AC
package boj.implement;

import java.io.*;
import java.util.*;

public class boj5430 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        var sb = new StringBuilder();

        for (int i=0; i < t; i++) {
            String method = br.readLine();
            int n = Integer.parseInt(br.readLine());
            var nums = new int[n];

            var st = new StringTokenizer(br.readLine(), "[,]");
            for (int j = 0; j < n; j++) {
                nums[j] = Integer.parseInt(st.nextToken());
            }

            int start = 0;
            int end = nums.length;
            boolean is_empty = false;
            boolean flag = true;

            for (char op : method.toCharArray()) {
                switch (op) {
                    case 'R':
                        flag ^= true;
                        break;
                    case 'D':
                        if (start < end) {
                            if (flag) {
                                start++;
                            } else {
                                end--;
                            }
                        } else {
                            is_empty = true;
                            break;
                        }
                        break;
                }

                if (start > end) break;
            }

            if (is_empty) {
                sb.append("error\n");
            } else if (start >= end) {
                sb.append("[]\n");
            } else {
                sb.append("[");
                if (flag) {
                    for (int j=start; j < end - 1; j++) {
                        sb.append(nums[j]).append(",");
                    }
                    if (end - 1 >= 0)
                        sb.append(nums[end-1]);
                } else {
                    for (int j=end - 1; j > start; j--) {
                        sb.append(nums[j]).append(",");
                    }
                    sb.append(nums[start]);
                }
                sb.append("]\n");
            }
        }
        System.out.print(sb.toString());
    }
}
