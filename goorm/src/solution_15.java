import java.io.*;
import java.util.*;

public class solution_15 {
    private static class Fruit implements Comparable<Fruit> {
        private Fruit(int price, int hunger) {
            this.price = price;
            this.hunger = hunger;
            this.pieceHunger = (hunger / price);  // 한 조각 당 포만감 계산
        }

        long price, hunger, pieceHunger;

        @Override
        public int compareTo(Fruit o) {
            return (int) ((this.hunger / this.price) - (o.hunger / o.price));
        }
    }

    private static int N;  // 과일의 종류
    private static long K;  // 플레이어 재산
    private static Fruit[] fruitArray;  // {과일의 가격, 포만감}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 과일의 종류
        K = Long.parseLong(st.nextToken());  // 플레이어 재산

        fruitArray = new Fruit[N];  // 과일 배열 초기화

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 과일 종류 삽입
            fruitArray[i] = new Fruit(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(fruitArray);  // 포만감 오름차순 정렬

        System.out.println(findMaxHunger(0L));  // 포만감 계산하는 함수 호출 및 출력
    }

    private static long findMaxHunger(long currentHunger) {
        for (int i = (N - 1); 0 <= i; i--) {  // 포만감이 높은 것부터 탐색
            if (0 <= K - fruitArray[i].price) {  // 한 과일 다 살 수 있을 경우
                K -= fruitArray[i].price;  // 가격 수 만큼 돈 빼기
                currentHunger += fruitArray[i].hunger;  // 포만감 입력
            } else {  // 한 과일을 다 사지 못할 경우
                currentHunger += (fruitArray[i].hunger / fruitArray[i].price) * K;  // K조각 당 포만감 계산
                break;
            }
        }

        return currentHunger;  // 포만감 반환
    }
}
