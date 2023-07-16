package programmers.lv2;

import java.util.Arrays;

public class Lv2_숫자_변환하기 {
}


class Solution {
    public int solution(int x, int y, int n) {

        int[] nums = new int[y+1];
        Arrays.fill(nums, 1024);
        nums[x] = 0;

        for (int i=x+1; i < y + 1; i++) {
            if (i - n >= x && nums[i-n] != 1024) {
                nums[i] = Math.min(nums[i], nums[i-n] + 1);
            }
            if (i % 2 == 0 && nums[i/2] != 1024) {
                nums[i] = Math.min(nums[i], nums[i/2] + 1);
            }
            if (i % 3 == 0 && nums[i/3] != 1024) {
                nums[i] = Math.min(nums[i], nums[i/3] + 1);
            }
        }

        if (nums[y] != 1024) return nums[y];
        return -1;

    }
}