package programmers.lv2;

// https://school.programmers.co.kr/learn/courses/30/lessons/42888

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lv2_오픈채팅방 {
    static Map<String, User> history;
    static List<Command> commands;

    private static class User {
        public String uid;
        public String nickname;

        public User(String uid, String nickname) {
            this.uid = uid;
            this.nickname = nickname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

    private static class Command {
        public User user;
        public String msg;

        public Command(User user, String msg) {
            this.user = user;
            this.msg = msg;
        }

        public String toString() {
            return user.getNickname() + msg;
        }
    }

    public String[] solution(String[] record) {
        init();

        for (String line : record) {
            String[] command = line.split(" ");
            if (line.startsWith("Enter")) {
                User user = history.getOrDefault(command[1], new User(command[1], command[2]));
                user.setNickname(command[2]);
                history.put(command[1], user);
                commands.add(new Command(user, "님이 들어왔습니다."));
            } else if (line.startsWith("Leave")) {
                User user = history.get(command[1]);
                commands.add(new Command(user, "님이 나갔습니다."));
            } else {
                User user = history.get(command[1]);
                user.setNickname(command[2]);
            } // Change
        }
        return makeResult();
    }

    private static void init() {
        history = new HashMap<>();
        commands = new ArrayList<>();
    }

    private static String[] makeResult() {
        String[] result = new String[commands.size()];
        int i = 0;
        for (Command command : commands) {
            result[i++] = command.toString();
        }
        return result;
    }
}