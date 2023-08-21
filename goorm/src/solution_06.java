import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class solution_06 {

    private static int N;
    private static String S;
    private static String[] varArr;
    private static Map<String, Integer> varMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  // 문자열 길이 받기
        S = br.readLine();  // 문자열 받기

        varMap = new HashMap<>();  // 점수를 담을 HashMap

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if(S.substring(i, j).length() <= N - 2) {  // 3개의 문자열로 나누기 위해 긴 문자열 제거
                    varMap.put(S.substring(i, j), 0);  // 문자열 중복을 제거하기 위한 Map 이ㅛㅇㅇ
                }
            }
        }

        varArr = varMap.keySet().toArray(new String[0]);  // sort를 위한 Array 변경
        Arrays.sort(varArr);  // 문자열 오름차순 정렬

        for (int i = 0; i < varArr.length; i++) {
            varMap.put(varArr[i], i + 1);  // 점수의 빠른 측정을 위한 Map 세팅
        }

        int sum = 0, tmp;
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // 문자열 점수 매기기
                tmp = varMap.get(S.substring(0, i));
                tmp += varMap.get(S.substring(i, j));
                tmp += varMap.get(S.substring(j, N));

                sum = Math.max(sum, tmp);  // 문자열 최대 점수 확인
            }
        }

        System.out.println(sum);  // 최대 점수 출력
    }
}
