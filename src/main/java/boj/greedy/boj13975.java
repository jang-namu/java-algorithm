// 13975 파일 합치기 3
package boj.greedy;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.IOException;

public class boj13975 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i=0; i<t; i++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (int j=0; j<n; j++) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            long sum = 0;
            int len = pq.size();
            for (int k=0; k < len-1; k++) {
                long one = pq.poll();
                long two = pq.poll();
                pq.add(one+two);
                sum += one + two;
            }
            System.out.println(sum);
        }
    }
}