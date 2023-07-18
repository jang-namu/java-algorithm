package programmers.lv2;

public class Lv2_마법의_엘리베이터 {
}

/*
    dp로 풀기? => 1.아래로 가는 길, 2. 위로 갔다 아래로 가는 길. 두가지 방향이 존재해 어렵다.

    조건문을 사용한다. 1~9까지 생각하자면 1~5와 6~9까지의 범위로 나눌 수 있다.
    6부터는 위로 (10층까지) 올라갔다가 한번에 0층으로 가는것이 빠르다.
    storey 크기의 상관없이 일의 자리와 십의 자리만을 신경쓴다.

    다만, 10의자리 이상일 경우 엣지케이스가 생긴다.
    55, 65, ..., 95와 같은 경우. 모두 위로가는 것이 빠르다. 55 -> 50 -> 0과 55 -> 60 -> 100 -> 0의 횟수는 같다.
    65 -> 60 -> 100 -> 0 보다는, 65 -> 70 -> 100 -> 0 이 빠르다.
*/

class Solutionkwe23 {
    public int solution(int storey) {
        int answer = 0;

        while (storey > 0) {
            if (storey % 10 >= 6) {
                answer += 10 - (storey % 10);
                storey += 10;
            } else if ((storey % 100) / 10 >= 5 && storey % 10 == 5) {
                answer += 10 - (storey % 10);
                storey += 10;
            } else {
                answer += storey % 10;
            }
            storey /= 10;
            System.out.println(answer);
        }

        return answer;
    }
}