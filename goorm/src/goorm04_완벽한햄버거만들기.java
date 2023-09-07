import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class goorm04_완벽한햄버거만들기 {

    private static int N, SUM;
    private static int[] taste;
    private static int[] maxTaste = new int[2];  // [index, 맛의 크기]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());  // 재료 개수
        taste = new int[N];  // 재료 개수 만큼의 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            taste[i] = Integer.parseInt(st.nextToken());
            if(maxTaste[1] < taste[i]) maxTaste = new int[] {i, taste[i]};  // 맛의 점수가 높은 재료의 index
        }

        SUM = 0;
        // 왼쪽
        for (int i = maxTaste[0] - 1; 0 <= i; i--) {
            if(taste[i + 1] < taste[i]) {
                System.out.println(0); return;
            } else {
                SUM += taste[i];
            }
        }


        // 오른쪽
        for (int i = maxTaste[0] + 1; i < N; i++) {
            if(taste[i - 1] < taste[i]) {
                System.out.println(0); return;
            } else {
                SUM += taste[i];
            }
        }

        System.out.println(SUM + maxTaste[1]);

    }
}
