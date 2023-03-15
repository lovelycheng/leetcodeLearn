import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author chengtong
 * @date 2023/3/15 19:44
 * 题目描述
 * 若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，它们能应用于通信加密。现在密码学会请你设计一个程序，从已有的 N （ N 为偶数）个正整数中挑选出若干对组成“素数伴侣”，挑选方案多种多样，例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，而将2和5、6和13编组将得到两组“素数伴侣”，能组成“素数伴侣”最多的方案称为“最佳方案”，当然密码学会希望你寻找出“最佳方案”。
 *
 * 输入:
 *
 * 有一个正偶数 n ，表示待挑选的自然数的个数。后面给出 n 个具体的数字。
 *
 * 输出:
 *
 * 输出一个整数 K ，表示你求得的“最佳方案”组成“素数伴侣”的对数。
 *
 */
public class PrimaryNumber {
    // 5,13 2,6
    //  2
    //  5 --13
    //         6
    //         --5  13
    //
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        List<Integer> list = new ArrayList<>();

        List<Integer> os = new ArrayList<>();
        List<Integer> js = new ArrayList<>();
        int jkjk=0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            if(jkjk == 0){
                jkjk =1;
                continue;
            }
            if ((a & 1 ) == 1) {
                js.add(a);
            } else {
                os.add(a);
            }
        }
        // System.out.println(js);
        // System.out.println(os);
        int max = 0;
        Map<Pair,Boolean> gu = new HashMap<>();
        for (int i = 0; i < os.size(); i++) {
            Map<Integer, Integer> hash = new HashMap<>();
            for (int j = 0; j < os.size(); j++) {
                for (int k = 0; k < js.size(); k++) {
                    int indexes = os.get(j) + js.get(k);
                    if (isPrime(indexes) && !gu.getOrDefault(new Pair(j,k),false)) {
                        // System.out.println("pp:"+indexes);
                        hash.put(os.get(j), js.get(k));
                        gu.put(new Pair(j,k),true);
                        System.err.println("==="+i+"===");
                        System.err.println("==="+j+","+k+"===");
                    }
                }
            }
            max = Math.max(max,hash.size());
            hash.clear();
        }
        System.out.println(max);
    }
    public static boolean isPrime(int n) {
        if (n <= 3) {
            return n > 1;
        }
        // 只有6x-1和6x+1的数才有可能是质数
        if (n % 6 != 1 && n % 6 != 5) {
            return false;
        }
        // 判断这些数能否被小于sqrt(n)的奇数整除
        int sqrt = (int) Math.sqrt(n);
        for (int i = 5; i <= sqrt; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    static class Pair{
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Pair))
                return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}
