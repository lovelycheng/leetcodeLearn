import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chengtong
 * @date 2023/2/17 19:38
 */
public class FloodFillBFS {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int oldVal = image[sr][sc];
        LinkedList<int[]> stack = new LinkedList<>();

        stack.addFirst(new int[] {sr, sc});
        while (!stack.isEmpty()) {
            int[] source = stack.removeFirst();
            int mx = source[0];
            int my = source[1];

            //right
            if (my < image[0].length - 1) {
                int rightVal = image[mx][my + 1];
                if (rightVal == oldVal && rightVal != color) {
                    stack.addFirst(new int[] {mx, my + 1});
                }
            }
            //left
            if (my > 0) {
                int leftVal = image[mx][my - 1];
                if (leftVal == oldVal && leftVal != color) {
                    stack.addFirst(new int[] {mx, my - 1});
                }
            }
            //up
            if (mx > 0) {
                int leftVal = image[mx - 1][my];
                if (leftVal == oldVal && leftVal != color) {
                    stack.addFirst(new int[] {mx - 1, my});
                }
            }
            //down
            if (mx < image.length - 1) {
                int leftVal = image[mx + 1][my];
                if (leftVal == oldVal && leftVal != color) {
                    stack.addFirst(new int[] {mx + 1, my});
                }
            }
            image[mx][my] = color;
        }

        return image;
    }

    public static void main(String[] args) {
        FloodFillBFS floodFill = new FloodFillBFS();
        int[][] image = {{0, 0, 0,}, {0, 0, 0,}};
        image = floodFill.floodFill(image, 0, 0, 2);
        System.out.println(Arrays.deepToString(image));
    }

}
