import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class solution_11 {
    
    private static int A, B, N;  // 통증완화제
    private static int[] ache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());  // 더 작은 치료제
        B = Integer.parseInt(st.nextToken());  // 더 큰 치료제

        ache = new int[((int) Math.floor(N / B)) + 1];  // 필요한 최대 큰 치료제 개수
        Arrays.fill(ache, N);
        DP(0, (int) Math.floor(N / B));

        System.out.println(-1);
    }

    private static void DP(int aCnt, int bCnt) {
        ache[bCnt] -= (bCnt * B);

        if(ache[bCnt] % A == 0) {
            System.out.println(bCnt + (ache[bCnt] / A));
            System.exit(0);
        }

        if(0 <= bCnt - 1) DP(aCnt, bCnt - 1);
    }
}
