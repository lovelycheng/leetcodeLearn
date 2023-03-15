import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author chengtong
 * @date 2023/3/15 16:04
 * 定义一个二维数组 N*M ，如 5 × 5 数组下所示：
 *
 *
 * int maze[5][5] = {
 * 0, 1, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 0, 0,
 * 0, 1, 1, 1, 0,
 * 0, 0, 0, 1, 0,
 * };
 *
 *
 * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的路线。入口点为[0,0],既第一格是可以走的路。
 * 输入描述：
 * 输入两个整数，分别表示二维数组的行数，列数。再输入相应的数组，其中的1表示墙壁，0表示可以走的路。数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。
 *
 * 输出描述：
 * 左上角到右下角的最短路径，格式如样例所示。
 */
public class Palace {

    private static int XL;
    private static int YL;
    private static int[][] elem;
    private static int[][] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        elem = new int[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                elem[i][j] = scanner.nextInt();
            }
        }
        XL = elem.length;
        YL = elem[0].length;
        List<int[]> list = new ArrayList<>();
        dfs(0, 0, list);
        for (int[] ints : list) {
            System.out.println(ints[0] + " " + ints[1]);
        }

    }

    private static boolean dfs(int x, int y, List<int[]> list) {
        visited[x][y] = 1;
        if ((x == XL - 1 && y == YL - 1)) {
            list.add(new int[] {x, y});
            return true;
        }
        if (elem[x][y] == 1) {
            return false;
        }
        int[] dot = new int[] {x, y};
        list.add(dot);
        if (x - 1 >= 0 && visited[x - 1][y] == 0) {
            if (dfs(x - 1, y, list)) {
                return true;
            }
        }
        if (x < XL - 1 && visited[x + 1][y] == 0) {
            if (dfs(x + 1, y, list)) {
                return true;
            }
        }
        if (y < YL - 1 && visited[x][y + 1] == 0) {
            if (dfs(x, y + 1, list)) {
                return true;
            }
        }
        if (y >= 1 && visited[x][y - 1] == 0) {
            return dfs(x, y - 1, list);
        }
        list.remove(dot);
        return false;

    }

}
