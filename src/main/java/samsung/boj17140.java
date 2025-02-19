package samsung;

// https://www.acmicpc.net/problem/17140 이차원 배열과 연산

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/*
2 1 1 2 [0 0]
1 1 2 1 3 1
3 3 0 0 0 0

리스트에 숫자를 추가하다가 0을 만나도 break 하면안됨.
continue로 이어가야함. -> R연산과 C연산이 뒤섞여 수행되므로 0이 중간에 생길 수 있음

1 3 1 1 3 1
1 1 1 1 1 1
2 1 2 2 0 0
1 2 1 1 0 0
3 0 0 0 0 0
1 0 0 0 0 0
 */

public class boj17140 {
    private static int r;
    private static int c;
    private static int k;
    private static List<List<Integer>> arr;

    private static int round;
    private static int maxLength;

    public static void main(String[] args) throws IOException {
        init();

        for (; round <= 100; round++) {
//            print();
            if (isSuccess()) {
                System.out.println(round);
                return;
            }
            if (arr.size() >= arr.get(0).size()) {
                operationRows();
            } else {
                operationCols();
            }
        }
        System.out.println(-1);
    }

    private static void print() {
        System.out.println("------ round: " + round + " -------");
        for (List<Integer> ar : arr) {
            for (int a : ar) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isSuccess() {
        return arr.size() >= r && arr.get(r - 1).size() >= c && arr.get(r - 1).get(c - 1) == k;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            arr.add(Arrays.asList(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
    }

    private static class Number implements Comparable<Number> {
        int num;
        int count;

        public Number(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Number n1) {
            if (count == n1.count) {
                return num - n1.num;
            }
            return count - n1.count;
        }
    }

    private static void operationRows() {
        List<List<Integer>> newArr = new ArrayList<>();
        for (List<Integer> row : arr) {
            newArr.add(opRow(row));
        }

        maxLength = Math.min(maxLength, 100);
        for (int i = 0; i < newArr.size(); i++) {
            List<Integer> row = newArr.get(i);
            int length = row.size();
            for (int j = 0; j < maxLength - length; j++) {
                row.add(0);
            }
            if (100 < length) {
                newArr.set(i, row.subList(0, 100));
            }
        }
        maxLength = 0;
        arr = newArr;
    }

    private static List<Integer> opRow(List<Integer> row) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer e : row) {
            if (e.equals(0)) {
                continue;
            }
            map.put(e, map.getOrDefault(e, 0) + 1);
        }

        List<Number> numbers = new ArrayList<>();
        for (Entry<Integer, Integer> e : map.entrySet()) {
            numbers.add((new Number(e.getKey(), e.getValue())));
        }
        numbers.sort(Number::compareTo);

        List<Integer> newRow = new ArrayList<>();
        for (Number num : numbers) {
            newRow.add(num.num);
            newRow.add(num.count);
        }
        maxLength = Math.max(maxLength, newRow.size());
        return newRow;
    }

    private static void operationCols() {
        List<List<Integer>> cols = new ArrayList<>();

        for (int i = 0; i < arr.get(0).size(); i++) {
            List<Integer> col = new ArrayList<>();
            for (int j = 0; j < arr.size(); j++) {
                Integer e = arr.get(j).get(i);
                if (e.equals(0)) {
                    continue;
                }
                col.add(e);
            }
            cols.add(opRow(col));
        }

        maxLength = Math.min(maxLength, 100);
        for (int i = 0; i < cols.size(); i++) {
            List<Integer> col = cols.get(i);
            int length = col.size();
            for (int j = 0; j < maxLength - length; j++) {
                col.add(0);
            }
            if (100 < length) {
                cols.set(i, col.subList(0, 100));
            }
        }

        List<List<Integer>> newArr = new ArrayList<>();
        for (int i = 0; i < maxLength; i++) {
            newArr.add(new ArrayList<>());
        }

        for (int i = 0; i < cols.size(); i++) {
            for (int j = 0; j < maxLength; j++) {
                newArr.get(j).add(cols.get(i).get(j));
            }
        }
        maxLength = 0;
        arr = newArr;
    }
}
