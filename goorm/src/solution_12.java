import java.io.*;
import java.util.*;

public class solution_12 {

    private static class Pos {
        private Pos(int r, int c) { this.r = r; this.c = c; }
        int r, c;
    }

    private static int N, cnt;
    private static int[][] M;
    private static Queue<Pos> queue;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        queue = new ArrayDeque<>();

        N = Integer.parseInt(br.readLine());
        M = new int[N][N];

        // 값 입력 받기
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                M[i][j] = Integer.parseInt(st.nextToken());  // 맵 채우기
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(M[i][j] == 1) {  // 방문하지 않은 집이 존재할 경우
                    cnt++;  // 발전기 설치
                    countMin(i, j);  // BFS 호출
                }
            }
        }

        System.out.println(cnt);
    }

    private static void countMin(int r, int c) {
        Pos currentPos;
        queue.add(new Pos(r, c));  // BFS 초기 값 추가
        while(!queue.isEmpty()) {  // 근접한 모든 곳을 방문할 때까지
            currentPos = queue.poll();

            if(0 <= currentPos.r - 1 && M[currentPos.r - 1][currentPos.c] == 1) {  // 근접한 집을 미방문했을 경우
                M[currentPos.r - 1][currentPos.c] = 0;  // 방문 처리
                queue.add(new Pos(currentPos.r - 1, currentPos.c));  // queue에 삽입
            }
            if(currentPos.r + 1 < N && M[currentPos.r + 1][currentPos.c] == 1) {
                M[currentPos.r + 1][currentPos.c] = 0;
                queue.add(new Pos(currentPos.r + 1, currentPos.c));
            }
            if(0 <= currentPos.c - 1 && M[currentPos.r][currentPos.c - 1] == 1){
                M[currentPos.r][currentPos.c - 1] = 0;
                queue.add(new Pos(currentPos.r, currentPos.c - 1));
            }
            if(currentPos.c + 1 < N && M[currentPos.r][currentPos.c + 1] == 1) {
                M[currentPos.r][currentPos.c + 1] = 0;
                queue.add(new Pos(currentPos.r, currentPos.c + 1));
            }
        }
    }
}
