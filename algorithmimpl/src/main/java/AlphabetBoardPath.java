import java.util.HashMap;

/**
 * @author chengtong
 * @date 2023/2/12 16:53
 */
public class AlphabetBoardPath {
    private static class Node {
        int right;
        int left;

        public Node(int right, int left) {
            this.right = right;
            this.left = left;
        }
    }

    public static String alphabetBoardPath(String target) {
        int temp = 'a';
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            int pos = target.charAt(i);
            int delta = pos - 'a';
            int up = delta / 5;
            int right = delta % 5;
            System.out.println("char:"+pos+" "+up+" "+right);

            int delta2 = temp - 'a';
            int up2 = delta2 / 5;
            int right2 = delta2 % 5;

            int upDown = up -up2;
            int rightLeft = right -right2;
            if(temp == 'z'){
                for (int j = 0; j < Math.abs(upDown); j++) {
                    stringBuilder.append(upDown < 0 ? "U" : "D");
                }
                for (int j = 0; j < Math.abs(rightLeft); j++) {
                    stringBuilder.append(rightLeft < 0 ? "L" : "R");
                }
            }else if(pos == 'z'){
                for (int j = 0; j < Math.abs(rightLeft); j++) {
                    stringBuilder.append(rightLeft < 0 ? "L" : "R");
                }
                for (int j = 0; j < Math.abs(upDown); j++) {
                    stringBuilder.append(upDown < 0 ? "U" : "D");
                }
            }else {
                for (int j = 0; j < Math.abs(rightLeft); j++) {
                    stringBuilder.append(rightLeft < 0 ? "L" : "R");
                }
                for (int j = 0; j < Math.abs(upDown); j++) {
                    stringBuilder.append(upDown < 0 ? "U" : "D");
                }
            }


            stringBuilder.append("!");
            temp = pos;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.err.println(alphabetBoardPath("leet"));
    }
}
