import java.io.*;
import java.util.*;

public class solution_08 {

    private static int[] painkiller = new int[] {14, 7, 1};
    private static long N;
    private static int SUM;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int i = 0;
        for (int j = 0; j < painkiller.length; j++) {
            SUM += N / painkiller[j];
            N %= painkiller[j];
        }

        System.out.println(SUM);
    }
}
