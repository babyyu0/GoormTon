import java.io.*;
import java.util.*;

public class goorm19_대체경로 {
    private static int N, M;  // 도시의 수 N, 도로의 수 M
    private static int S, E;  // 출발 도시 S, 도착 도시 E
    private static List<Integer>[] nodes;
    private static int[] nodeDepth;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        nodes = new ArrayList[N + 1];

        int n1, n2;
        // 값 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());

            if (nodes[n1] == null) nodes[n1] = new ArrayList<>();  // 도시 담을 곳 만들기
            if (nodes[n2] == null) nodes[n2] = new ArrayList<>();  // 도시 담을 곳 만들기

            nodes[n1].add(n2);  // 연결도시 추가
            nodes[n2].add(n1);  // 연결 도시 추가
        }

        for (int i = 1; i <= N; i++) {
            if(S == i || E == i) {
                System.out.println(-1);
                continue;
            }
            nodeDepth = new int[N + 1];
            DP(i);
        }
    }

    private static void DP(int date) {
        Queue<Integer> queue = new ArrayDeque<>();
        nodeDepth[S] = 1;
        queue.add(S);

        int curNode;
        while (!queue.isEmpty()) {
            curNode = queue.poll();  // 현재 선택 노드 인덱스
            for (int i = 0; i < nodes[curNode].size(); i++) {
                if(nodes[curNode].get(i) == E) {
                    System.out.println(nodeDepth[curNode] + 1);
                    return;
                } else if (nodeDepth[nodes[curNode].get(i)] == 0 && nodes[curNode].get(i) != date) {  // 방문한 적 없을 시 혹은 공사 안 할 시
                    nodeDepth[nodes[curNode].get(i)] = nodeDepth[curNode] + 1;  // 깊이 추가
                    queue.add(nodes[curNode].get(i));  // queue에 추가
                }
            }
        }
        System.out.println(-1);
    }
}