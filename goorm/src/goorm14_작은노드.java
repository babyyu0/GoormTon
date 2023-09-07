import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class goorm14_작은노드 {

    private static int N, M, K;  // 노드 개수, 간선 개수, 시작 노드의 번호
    private static List<Integer>[] nodes;
    private static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        int n1, n2;
        // 값 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());

            if (nodes[n1] == null) nodes[n1] = new ArrayList<>();  // 연결 노드 담을 곳 만들기
            if (nodes[n2] == null) nodes[n2] = new ArrayList<>();  // 연결 노드 담을 곳 만들기

            nodes[n1].add(n2);  // 연결 노드 추가
            nodes[n2].add(n1);  // 연결 노드 추가
        }

        findEnd();
    }

    private static void findEnd() {
        int currentK = 0, visit = 0;

        while (K != currentK) {
            // System.out.println("방문 노드 : " + K);
            currentK = K;
            visited[K] = true;
            visit++;
            if (nodes[K] == null) break;

            Collections.sort(nodes[K]);
            // System.out.println("연결 노드 : " + nodes[K] + "\n");

            for (int i = 0; i < nodes[K].size(); i++) {
                if (!visited[nodes[K].get(i)]) {
                    K = nodes[K].get(i);
                    break;
                }
            }
        }

        System.out.println(visit + " " + K);  // 끝 노드 찾기
    }
}
