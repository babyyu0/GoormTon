import java.util.*;
import java.io.*;

public class goorm18_중첩점 {

    private static int N, M;  // 정사각형 크기 N, 반직선의 개수 M
    private static int[][] rowArr, colArr;  // 선을 그을 2차원 배열
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static final Map<Character, Integer> direciton = new HashMap() {{
        put('U', 0);
        put('D', 1);
        put('L', 2);
        put('R', 3);
    }};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 정사각형 크기
        M = Integer.parseInt(st.nextToken());  // 반직선의 개수
        rowArr = new int[N][N];
        colArr = new int[N][N];


        // 입력 받기
        int r, c, d;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());  // {r, c, 방향}
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            d = direciton.get(st.nextToken().charAt(0));  // 방향을 int로 변환
            if(d < 2) {  // 수직 방향
                try {
                    while (true) {
                        colArr[r][c]++;
                        r += dr[d]; c += dc[d];
                    }
                } catch(IndexOutOfBoundsException e) { continue; }
            } else {  // 수평 방향
                try {
                    while (true) {
                        rowArr[r][c]++;
                        r += dr[d]; c += dc[d];
                    }
                } catch(IndexOutOfBoundsException e) { continue; }
            }
        }

        System.out.println(countNesting(0L));
    }

    private static long countNesting(long nesting) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nesting += ((long)rowArr[i][j] * (long)colArr[i][j]);
            }
        }

        return nesting;
    }
}
