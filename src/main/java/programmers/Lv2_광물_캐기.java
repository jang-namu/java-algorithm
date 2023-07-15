package programmers;

import java.util.Arrays;

public class Lv2_광물_캐기 {
}

/*
    곡괭이 종류별로 0~5개, 곡괭이는 5개 캐면 부서짐
    선택한 곡괭이는 부서질때까지 사용
    최소 피로도
    광물을 5개씩 짝지어서 피로도의 합 순대로?
    다이아 = 철 5개 = 돌 25개
    즉, 다이아 = 25, 철 = 5, 돌 = 1로 계산.

    광물을 5개씩, 하나의 작업으로 짝짓고, 작업의 피로도 대로 정렬.
    피로도가 높은 작업부터 다이아 -> 철 -> 돌 곡괭이 순으로 사용.

    탐욕법이 통하는 이유? => 곡괭이의 피로도가 모두 배수이기 때문!
 */



//import java.util.*;
class boj2529 {
    public int solution(int[] picks, String[] minerals) {
        //우선 미네랄을 picks * 5 개 까지만 끊는다.
        int num_axes = Arrays.stream(picks).sum();
        if (num_axes * 5 <= minerals.length) {
            minerals = Arrays.copyOfRange(minerals, 0, num_axes * 5);
        }

        int answer = 0;
        // fatigue 배열은 fatigue[i][0]에 해당 작업의 총 피로도, 1, 2, 3, 4, 5에 각각의 광물을 저장
        // 곡괭이가 많을 경우, 미네랄이 많을 경우. 생각해서 Math.min()으로 배열 크기 지정.
        int[][] fatigue = new int[Math.min(num_axes, minerals.length/5+1)][];

        // 각 작업의 피로도 0으로 초기화
        for (int i=0; i < fatigue.length; i++) {
            fatigue[i] = new int[6];
            fatigue[i][0] = 0;
        }

        // idx는 1~5 까지를 돌며, 각 광물을 저장하는 용도의 인덱스
        int idx = 1;
        for (int i=0; i < minerals.length; i++) {
            if (minerals[i].equals("diamond")) {
                fatigue[i / 5][0] += 25;    // 곡괭이 하나, 작업 피로도 총합
                fatigue[i / 5][idx++] = 25;     // 광물 저장
            } else if (minerals[i].equals("iron")) {
                fatigue[i / 5][0] += 5;
                fatigue[i / 5][idx++] = 5;
            } else {
                fatigue[i / 5][0] += 1;
                fatigue[i / 5][idx++] = 1;
            }

            if (idx == 6) idx = 1;  // 다음 곡괭이 작업 시, 1로 되돌아감.
        }

        // 람다식 이용. 첫버째 원소(작업 피로도) 기준 내림차순 정렬.
        Arrays.sort(fatigue, (o1, o2) -> {return o2[0] - o1[0];});
        for (int[] job : fatigue) {
            /*
            for (int x : job) {
                System.out.print(x + " ");
            }
            System.out.println();
            */
            int pick = -1;  // 곡괭이
            if (picks[0] > 0) {     // 다이아 곡괭이가 남았을 경우
                pick = 25;
                picks[0]--;
            } else if (picks[1] > 0) {  // 철괭이 남았을 경우
                pick = 5;
                picks[1]--;
            } else if (picks[2] > 0) {  // 돌괭이라도 남았을 경우.
                pick = 1;
                picks[2]--;
            } else {    // 괭이 전부 소진 시 종료.
                break;
            }

            for (int i=1; i < job.length; i ++) {
                if (job[i] == 0) {  // 더 이상 광물 없으면 종료.
                    break;
                }
                else if (job[i] / pick == 0) {      // 광물이 1(돌) 이고 괭이가 25 (다이아)이면? 1만큼 피로도
                    answer++;
                } else {    // 광물이 25(다이아), 괭이가 5(철)이면? ..
                    answer += job[i] / pick;
                }
            }
        }

        return answer;
    }
}