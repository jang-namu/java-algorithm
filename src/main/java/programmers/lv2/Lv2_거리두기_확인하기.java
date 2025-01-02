package programmers.lv2;

public class Lv2_거리두기_확인하기 {
}


/*
아아디어: 체스판 문제와 유사하게 0,0 -> 5,5로 단방향 순회하며 right, down, 대각선 확인

반례 1. P끼리 붙어있는 경우 PPXXX (마지막 행도 검사 필요)
반례 2. (r+1, c+1) 방향 대각선 말고도 (r+1, c-1) 방향 대각선도 확인필요
마지막 틀린이유.

    private boolean direct(char[][] place, int r, int c) {
        if (!validateRange(r, c+1)) return false;
        if (place[r][c+1] == 'P') return true;
        if (!validateRange(r+1, c)) return false;
        if (place[r+1][c] == 'P') return true;
        return false;
    }

문제:
        "if (!validateRange(r, c+1)) return false;"
         오른쪽 범위 확인 중 false로 반환되면, 아래쪽 확인을 하지않았음


아래처럼 변경 후 따로 호출하여 통과
    private boolean downDirect(char[][] place, int r, int c) {
        if (!validateRange(r+1, c)) return false;
        if (place[r+1][c] == 'P') return true;
        return false;
    }

    private boolean rightDirect(char[][] place, int r, int c) {
        if (!validateRange(r, c+1)) return false;
        if (place[r][c+1] == 'P') return true;
        return false;
    }

*/
class Solution {

    public int[] solution(String[][] places) {
        int[] result = new int[5];
        for (int i = 0; i < 5; i++) {
            result[i] = isSafe(places[i]);
        }
        return result;
    }

    private int isSafe(String[] place) {
        char[][] placeArr = new char[5][];
        for (int i = 0; i < 5; i++) {
            placeArr[i] = place[i].toCharArray();
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (placeArr[i][j] != 'P') continue;
                if (unsafe(placeArr, i, j)) return 0;
            }
        }
        return 1;
    }

    private boolean validateRange(int r, int c) {
        return (0 <= r && r < 5 && 0 <= c && c < 5);
    }

    private boolean unsafe(char[][] place, int r, int c) {
        if (downDirect(place, r, c)) return true;
        if (rightDirect(place, r, c)) return true;
        if (right(place, r, c + 2)) return true;
        if (down(place, r + 2, c)) return true;
        if (diagonalRight(place, r + 1, c + 1)) return true;
        if (diagonalLeft(place, r + 1, c - 1)) return true;
        return false;
    }

    private boolean downDirect(char[][] place, int r, int c) {
        if (!validateRange(r+1, c)) return false;
        if (place[r+1][c] == 'P') return true;
        return false;
    }

    private boolean rightDirect(char[][] place, int r, int c) {
        if (!validateRange(r, c+1)) return false;
        if (place[r][c+1] == 'P') return true;
        return false;
    }

    private boolean right(char[][] place, int r, int c) {
        if (!validateRange(r, c)) return false;
        if (place[r][c] != 'P') return false;
        if (place[r][c-1] == 'X') return false;
        return true;
    }

    private boolean down(char[][] place, int r, int c) {
        if (!validateRange(r, c)) return false;
        if (place[r][c] != 'P') return false;
        if (place[r-1][c] == 'X') return false;
        return true;
    }

    private boolean diagonalRight(char[][] place, int r, int c) {
        if (!validateRange(r, c)) return false;
        if (place[r][c] != 'P') return false;
        if ((place[r-1][c] == 'X') && (place[r][c-1] == 'X')) return false;
        return true;
    }

    private boolean diagonalLeft(char[][] place, int r, int c) {
        if (!validateRange(r, c)) return false;
        if (place[r][c] != 'P') return false;
        if ((place[r-1][c] == 'X') && (place[r][c+1] == 'X')) return false;
        return true;
    }

}