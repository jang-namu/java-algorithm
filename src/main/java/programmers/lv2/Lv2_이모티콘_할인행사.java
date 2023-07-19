package programmers.lv2;

public class Lv2_이모티콘_할인행사 {
}
/*
    재귀로 해결할 경우, 콜 스택 깊이가 엄청나게 깊어진다. 이모티콘 4^7 번 호출. 2^14승이면 작긴한가? 아무튼 1만 6천번이상

    좀 더 효율적이도록 반복문으로 작성.
    문제는 각 이모티콘에 대한 할인율을 10~40까지 어떻게 바꿀것이냐.

    이모티콘의 종류가 7가지밖에 안되므로 정수형 사용.
    1111111 ~ 4444444까지 각 자리 숫자는 각 이모티콘의 할인율. 1 = 10%, 2 = 20% ...
    각 자리 숫자가 5 이상이되면 1로 되돌리고, 다음 자리 수를 1만큼 증가시킨다.
    숫자를 조정한 경우에는 다음 iter로 넘어가 계산한다.

    각 할인율마다 유저들을 순회하며 이모티콘 플러스, 구입 금액의 합을 계산.

    보완.
    1111111로 두고, discount = {10, 20, 30, 40}으로 둔 후,
    for (int discount = 1111111; discount <= 1111111 * discount.length;)
    if문에서 discount[i의 각 자리수 - 1]로 인덱싱을 사용하는 것이 좋아보인다.
    증가시키는 것은 if (((discount % (j * 10)) / j) > discount.length)로 좀 더 유연하게 처리한다.
*/
class Solution312598qwe {
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {0, 0};

        var sb = new StringBuilder();

        for (int discount = 1111111; discount <= 4444444;) {
            boolean sign = false;
            for (int j=1; j <= 1000000; j*=10) {
                if (((discount % (j * 10)) / j) >= 5) {
                    discount -= 4*j;
                    discount += 10*j;
                    sign = true;
                }
            }
            if (sign) continue;

            int emoziPlus = 0;
            int total = 0;
            for (int[] user : users) {
                int userTotal = 0;
                for (int k=emoticons.length-1; k >= 0; k--) {
                    int discountRatio = ((discount % (int)Math.pow(10, k+1)) / (int)Math.pow(10, k));
                    if (discountRatio * 10 >= user[0]) {
                        userTotal += emoticons[k] * (100 - discountRatio * 10) / 100;
                    }
                }
                if (userTotal >= user[1]) {
                    emoziPlus++;
                } else {
                    total += userTotal;
                }
            }
            if (answer[0] < emoziPlus) {
                answer[0] = emoziPlus;
                answer[1] = total;
            } else if ((answer[0] == emoziPlus) && (answer[1] < total)) {
                answer[1] = total;
            }
            discount++;
        }
        return answer;
    }
}