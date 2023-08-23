import java.io.*;
import java.util.*;

public class solution_07 {

    private static int N, K, SUM;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());  // 배열 크기, 찾고싶은 깃발 값

        N = Integer.parseInt(st.nextToken());  // 배열 크기
        K = Integer.parseInt(st.nextToken());  // 찾고싶은 깃발의 값
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()); // 한 행의 값
            for (int j = 0; j < N; j++) {
                if(st.nextToken().equals("1")) {
                    if(arr[i][j] == K) SUM--;
                    arr[i][j] = -100;
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            try {
                                arr[k][l]++;
                                if(arr[k][l] == K) SUM++;
                                else if(arr[k][l] == K + 1) SUM--;
                            } catch(ArrayIndexOutOfBoundsException e) {
                                continue;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(SUM);
    }
}
