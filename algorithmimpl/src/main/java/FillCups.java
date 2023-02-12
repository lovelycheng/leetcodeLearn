import java.util.Arrays;

/**
 * @author chengtong
 * @date 2023/2/11 12:42
 */
public class FillCups {

    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[0] + amount[1] < amount[2]) {
            return amount[2];
        }
        return (amount[1] + amount[0] + amount[2] + 1) / 2;
    }

}
