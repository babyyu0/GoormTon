import java.io.*;
import java.util.*;

public class solution_13 {

    private static class Pos {
        private Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        int r, c;
    }

    private static int N, K;  // 마을 한 변의 크기, 단지의 기준
    private static int[][] M;  // 마을 구성 배열
    private static int[] danjiCnt, maxNum;  // 단지의 개수 출력, {건물 유형, 단지 수}
    private static final int MAX_CATEGORY = 31;  // 단지 유형 최대 숫자

    private static Queue<Pos> queue;  // BFS에 사용할 queue
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 마을 한 변의 크기 입력
        K = Integer.parseInt(st.nextToken());  // 단지의 기준 입력
        M = new int[N][N];  // 마을 구성 초기화
        danjiCnt = new int[MAX_CATEGORY];  // 단지 개수 초기화
        queue = new ArrayDeque<>();  // BFS에 사용할 queue 초기화

        // 입력 시작
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                M[i][j] = Integer.parseInt(st.nextToken());  // 마을의 건물 유형 수 입력
            }
        }

        System.out.println(findHouse());  // 최대 단지 개수를 가진 건물 유형 찾기
    }

    private static int findHouse() {  // 방문 탐색을 이용한 건물 탐색
        maxNum = new int[2];  // 최대 단지 개수 초기화
        int category = 0;  // 건물 유형 초기화

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (0 < M[i][j]) {  // 방문한 적이 없을 경우
                    category = M[i][j];  // 건물 유형 저장
                    danjiCnt[M[i][j]] += findDanji(i, j, 0, M[i][j], null);  // 단지일 시  + 1 BFS 함수 호출

                    // 이전 최대 단지 보유 건물 유형과 비교
                    if (maxNum[1] < danjiCnt[category] || (maxNum[0] < category && maxNum[1] == danjiCnt[category])) {
                        maxNum[0] = category;  // 현재 최대 단지 보유 건물 유형 삽입
                        maxNum[1] = danjiCnt[category];  // 현재 최대 단지 개수 삽입
                    }
                }
            }
        }

        return maxNum[0];  // 현재 최대 단지 보유 건물 유형 전달
    }

    private static int findDanji(int r, int c, int cnt, int category, Pos currentPos) {
        queue.add(new Pos(r, c));  // 최초 시작점 queue에 삽입
        M[r][c] = 0;  // 최초 시작점 건물 방문 check

        while (!queue.isEmpty()) {
            currentPos = queue.poll();  // 큐 가장 앞에 있는 위치 빼기
            r = currentPos.r;
            c = currentPos.c;

            cnt++;  // 건물 유형 + 1
            for (int i = 0; i < 4; i++) {
                try {
                    if (M[r + dx[i]][c + dy[i]] == category) {  // 건물 유형이 일치할 경우
                        M[r + dx[i]][c + dy[i]] = 0;  // 방문 check
                        queue.add(new Pos(r + dx[i], c + dy[i]));  // queue에 삽입
                    }
                } catch (Exception ignored) {  // 범위를 넘을 시 진행 X
                }
            }
        }

        if (K <= cnt) return 1;  // 단지 존재 시 + 1
        else return 0;  // 단지가 아닐 시 + 0
    }
}
