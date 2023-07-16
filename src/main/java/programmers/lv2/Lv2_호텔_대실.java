package programmers.lv2;

import java.util.*;

public class Lv2_호텔_대실 {
}


class Book implements Comparable {
    int start;
    int end;
    public Book(int s, int e) {
        start = s;
        end = e;
    }

    public int compareTo(Object obj) {
        Book tmp = (Book)obj;
        if (start < tmp.start) {
            return -1;
        } else if (start > tmp.start) {
            return 1;
        } else {
            if (end >= tmp.end) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public String toString() {
        return "시작 : " + start + ", 끝 : " + end;
    }
}

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;

        List<Book> list = new ArrayList<Book>();
        for (int i=0; i < book_time.length; i++) {
            String[] times = book_time[i][0].split(":");
            int s = Integer.parseInt(times[0]) * 60
                    + Integer.parseInt(times[1]);

            times = book_time[i][1].split(":");
            int e = Integer.parseInt(times[0]) * 60
                    + Integer.parseInt(times[1]);
            list.add(new Book(s, e));
        }

        Collections.sort(list);
        // System.out.println(list);

        int[] endTimes = new int[1000];
        Arrays.fill(endTimes, 0);
        int pos = 0;
        for (Book now : list) {
            boolean sign = true;
            for (int i=0; i < pos; i++) {
                if (endTimes[i] + 10 <= now.start) {
                    sign = false;
                    endTimes[i] = now.end;
                    break;
                }
            }
            if (sign) {
                endTimes[pos] = now.end;
                pos++;
            }
        }

        return pos;
    }
}