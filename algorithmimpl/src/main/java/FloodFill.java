/**
 * @author chengtong
 * @date 2023/2/17 17:50
 */
public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        search(image, sr + 1, sc, color, image[sr][sc]);
        search(image, sr - 1, sc, color, image[sr][sc]);
        search(image, sr, sc + 1, color, image[sr][sc]);
        search(image, sr, sc - 1, color, image[sr][sc]);
        image[sr][sc] = color;
        return image;
    }

    //up sr-1 right sc+1 left sc-1 down sr+1
    private int[][] search(int[][] image, int sr, int sc, int color, int oldColor) {
        if (sc < 0 || sr < 0 || sc == image[0].length || sr == image.length || image[sr][sc] == color
            || image[sr][sc] != oldColor) {
            return image;
        } else {
            image[sr][sc] = color;
        }
        if (sc <= image[0].length-1 ) {
            image = search(image, sr, sc + 1, color, oldColor);
        }
        if (sc > 0) {
            image = search(image, sr, sc - 1, color, oldColor);
        }
        if (sr < image.length) {
            image = search(image, sr + 1, sc, color, oldColor);
        }
        if (sr > 0) {
            image = search(image, sr - 1, sc, color, oldColor);
        }
        return image;
    }

    //image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
    public static void main(String[] args) {
        FloodFill floodFill = new FloodFill();
        int[][] image = {{0, 0, 0,}, {0, 0, 0,}};
        image = floodFill.floodFill(image, 0, 0, 2);
        System.out.println(image);
    }

}
