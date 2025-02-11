package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15686 치킨 배달
public class boj15686 {

    private static int numberOfHouses;
    private static int numberOfChickens;
    private static List<Point> houses = new ArrayList<>();
    private static List<Point> chickens = new ArrayList<>();
    private static List<List<Distance>> distances = new ArrayList<>();

    private static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static class Distance {
        int chickenIdx;
        int distance;

        private Distance(int chickenIdx, int distance) {
            this.chickenIdx = chickenIdx;
            this.distance = distance;
        }

        public static Distance of(int idx, Point house, Point chicken) {
            return new Distance(idx, calcDistance(house, chicken));
        }

        private static int calcDistance(Point house, Point chicken) {
            return Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N, br);

        for (int i = 0; i < numberOfHouses; i++) {
            Point house = houses.get(i);
            distances.add(new ArrayList<>());
            List<Distance> distancesFromHouse = distances.get(i);
            for (int j = 0; j < numberOfChickens; j++) {
                Point chicken = chickens.get(j);
                distancesFromHouse.add(Distance.of(j, house, chicken));
            }
            distancesFromHouse.sort(Comparator.comparingInt(d -> d.distance));
        }

        int result = combination(distances, new boolean[numberOfChickens], M, 0, 0);
        System.out.println(result);
    }

    private static int combination(List<List<Distance>> distances, boolean[] visit, int M, int depth, int start) {
        if (depth == M) {
            return sumChickenDistance(distances, visit);
        }
        int result = Integer.MAX_VALUE;
        for (int i = start; i < numberOfChickens; i++) {
            visit[i] = true;
            result = Math.min(result, combination(distances, visit, M, depth + 1, i+1));
            visit[i] = false;
        }
        return result;
    }

    private static int sumChickenDistance(List<List<Distance>> distances, boolean[] visit) {
        int result = 0;
        for (List<Distance> distancesFromHouse : distances) {
            for (Distance distance : distancesFromHouse) {
                if (!visit[distance.chickenIdx]) { // true인 경우 폐업안함
                    continue;
                }
                result += distance.distance;
                break;
            }
        }
        return result;
    }

    private static void init(int N, BufferedReader br) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int token = Integer.parseInt(st.nextToken());
                if (token == 1) {
                    houses.add(new Point(i, j));
                    numberOfHouses++;
                }
                if (token == 2) {
                    chickens.add(new Point(i, j));
                    numberOfChickens++;
                }
            }
        }
    }

}
