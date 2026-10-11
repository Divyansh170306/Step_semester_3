
import java.util.*;

public class Problem1MallFootfallRangeReport {

    public static List<Integer> footfallReport(int[] visitors, int[][] queries) {
        int n = visitors.length;
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + visitors[i];
        }

        List<Integer> result = new ArrayList<>();

        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];

            result.add(prefix[end + 1] - prefix[start]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};

        int[][] queries = {
            {0, 2},
            {2, 5},
            {4, 6},
            {3, 3}
        };

        System.out.println(footfallReport(visitors, queries));
    }
}
