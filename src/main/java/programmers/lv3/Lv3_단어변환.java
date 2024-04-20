package programmers.lv3;

import java.util.*;

public class Lv3_단어변환 {
    static class Word {
        String word;
        int n;
        public Word(String word, int n) {
            this.word = word;
            this.n = n;
        }
    }
    private boolean isAlmostSame(String s1, String s2) {
        int count = 0;

        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        for (int i = 0; i < chs1.length; i++)
            if (chs1[i] != chs2[i])
                count++;

        return count < 2;
    }

    public int solution(String begin, String target, String[] words) {
        List<List<String>> a = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            a.add(new ArrayList<String>());
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isAlmostSame(words[i], words[j])) {
                    a.get(i).add(words[j]);
                    a.get(j).add(words[i]);
                }
            }
        }

        Map<String, Integer> idxs = new HashMap<>();
        for (int i = 0 ; i < words.length; i++) {
            idxs.put(words[i], i);
        }
        boolean[] visited = new boolean[words.length];

        Queue<Word> q = new LinkedList<>();

        for (int i = 0; i < words.length; i++) {
            if (isAlmostSame(words[i], begin)) {
                q.add(new Word(words[i], 1));
            }
        }

        while (q.size() > 0) {
            Word current = q.poll();
            if (current.word.equals(target))
                return current.n;
            for (String next : a.get(idxs.get(current.word))) {
                int idx = idxs.get(next);
                if (visited[idx]) continue;
                visited[idx] = true;
                q.add(new Word(next, current.n+1));
            }
        }

        return 0;
    }
}