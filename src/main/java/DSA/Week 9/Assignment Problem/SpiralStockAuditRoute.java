
import java.util.*;

public class Problem5SpiralStockAuditRoute {

    public static List<Integer> auditRoute(int[][] grid) {
        List<Integer> result = new ArrayList<>();

        int top = 0;
        int bottom = grid.length - 1;
        int left = 0;
        int right = grid[0].length - 1;

        while (top <= bottom && left <= right) {

            for (int i = left; i <= right; i++) {
                result.add(grid[top][i]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                result.add(grid[i][right]);
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(grid[bottom][i]);
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(grid[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        System.out.println(auditRoute(grid));
    }
}
