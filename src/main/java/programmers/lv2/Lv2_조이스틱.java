package programmers.lv2;

public class Lv2_조이스틱 {
    private int change(char ch) {
        if (ch < 'N') return ch - 'A';
        return 'Z' - ch + 1;
    }

    public int solution(String name) {
        int answer = 0;
        int remain = 0;
        char[] chs = name.toCharArray();
        for (char c : chs) {
            if (c != 'A') {
                remain++;
                answer += change(c);
            }
        }

        int idx = 0;
        if (chs[idx] != 'A') {
            remain--;
            chs[idx] = 'A';
        }
        for (int count = 0; count < remain; count++) {
            int i;
            for (i = 1; i < chs.length; i++) {
                
            }
        }
        return answer;
    }
}
