import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class solution_10 {
    private static class Person {
        Person(int r, int c) {
            this.r = r;
            this.c = c;
            this.score = 0;
        }

        private void setScore(int score) {
            this.score = score;
        }

        int r, c, score;
    }

    private static int N;
    private static Person p1, p2;
    private static String[][] map;
    private static boolean[][] visited;
    static HashMap<Character, int[]> directions = new HashMap<>() {
        {
            put('U', new int[]{-1, 0});
            put('D', new int[]{1, 0});
            put('L', new int[]{0, -1});
            put('R', new int[]{0, 1});
        }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  // 보드의 크기
        map = new String[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());  // 구름이 정보
        p1 = new Person(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        st = new StringTokenizer(br.readLine());  // 본인 정보
        p2 = new Person(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken();
            }
        }

        visited = new boolean[N][N];
        p1.setScore(move(p1.r, p1.c, 1, map[p1.r][p1.c]));

        visited = new boolean[N][N];
        p2.setScore(move(p2.r, p2.c, 1, map[p2.r][p2.c]));

        if (p1.score > p2.score) {
            System.out.println("goorm " + p1.score);
        } else {
            System.out.println("player " + p2.score);
        }
    }

    private static int moveCnt;

    private static int move(int r, int c, int score, String move) {
        if (visited[r][c]) return score - 1;
        visited[r][c] = true;

        if (Integer.parseInt(move.substring(0, move.length() - 1)) == 0) move = map[r][c];  // 움직일 위치 변경
        moveCnt = Integer.parseInt(move.substring(0, move.length() - 1)) - 1;
        move = Integer.toString(moveCnt) + move.charAt(move.length() - 1);

        r += directions.get(move.charAt(move.length() - 1))[0];
        c += directions.get(move.charAt(move.length() - 1))[1];

        return move(setPos(r), setPos(c), score + 1, move);
    }

    private static int setPos(int x) {
        if(x == N) return 0;
        else if (x == -1) return N - 1;
        return x;
    }
}
