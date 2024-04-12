package programmers.lv2;

public class Lv2_큰_수_만들기 {
    public String solution(String number, int k) {
        StringBuilder sb= new StringBuilder();
        char[] nums = number.toCharArray();

        int start = 0;
        int end = k + 1;
        while (end <= nums.length) {
            int max = nums[start];
            int maxIdx = start;
            for (int i  = start + 1; i < end; i++) {
                if (max < nums[i]) {
                    max = nums[i];
                    maxIdx = i;
                }
            }
            sb.append(nums[maxIdx]);
            start = maxIdx + 1;
            end++;
        }

        return sb.toString();
    }
}
