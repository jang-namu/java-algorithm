package programmers.lv2;

import java.util.*;


class Task implements Comparable {
    String name;
    int hour;
    int minutes;
    int playTime;

    public Task(String name, int hour, int minutes, int playTime) {
        this.name = name;
        this.hour = hour;
        this.minutes = minutes;
        this.playTime = playTime;
    }

    public int compareTo(Object obj) {
        Task temp = (Task)obj;
        int org = this.hour * 60 + this.minutes;
        int com = temp.hour * 60 + temp.minutes;
        if (org > com) {
            return -1;
        } else if (org == com) {
            return 0;
        } else {
            return 1;
        }
    }

    public String toString() {
        return name + ", 시작시간: " + hour + ":" + minutes + ", 소요시간: " + playTime;
    }
}

public class Lv2_과제_진행하기 {
    public void main(String[] args) {
        solution();
    }
    public String[] solution(String[][] plans) {
        String[] answer = {};

        List<Task> list = new ArrayList<>();
        for (int i=0; i < plans.length; i++) {
            String name = plans[i][0];
            String[] times = plans[i][0].split(":");
            int hour = Integer.parseInt(times[0]);
            int minutes = Integer.parseInt(times[1]);
            int playTime = Integer.parseInt(plans[i][2]);
            list.add(new Task(name, hour, minutes, playTime));
        }

        Collections.sort(list);
        for (var item : list) {
            System.out.println(item);
        }

        return answer;
    }
}

*/