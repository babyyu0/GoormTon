import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class goorm05_이진수정렬 {

    static class Binary implements Comparable<Binary> {  // 2진수 정보를 담을 class
        public Binary(long num, int oneNum) {
            this.num = num;
            this.oneNum = oneNum;
        }
        long num;
        int oneNum;  // 1의 개수

        @Override
        public int compareTo(Binary o) {
            if(this.oneNum != o.oneNum) {
                return this.oneNum - o.oneNum;
            } else {
                return (int) (this.num - o.num);
            }
        }

        @Override
        public String toString() {
            return "Binary{" +
                    "num=" + num +
                    ", oneNum=" + oneNum +
                    '}';
        }
    }
    private static PriorityQueue<Binary> pq = new PriorityQueue<>();
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 받아올 정수 개수
        M = Integer.parseInt(st.nextToken());  // 받아올 정수 위치

        st = new StringTokenizer(br.readLine());  // 값 받아오기
        long num;  // 숫자 값을 받을 long 변수
        String binaryString;  // 이진수를 담을 string
        int cntOne;  // 1값을 세는 변수
        for (int i = 0; i < N; i++) {
            cntOne = 0; // 초기화
            num = Long.parseLong(st.nextToken());  // 숫자 값
            binaryString = Long.toBinaryString(num);  // 이진수 변환

            for (int j = 0; j < binaryString.length(); j++) {
                if (binaryString.charAt(j) - '0' == 1) cntOne++;
            }

            pq.add(new Binary(num, cntOne));
        }

        for (int i = 0; i < N - M; i++) {
            pq.poll();
        }

        System.out.println(pq.poll().num);
    }
}
