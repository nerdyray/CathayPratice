package mid_term;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomString {

    Random rd = new Random();
    private final int num = (int) (Math.random() * 26) + 1;//1-26之間的亂數
    // private final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//前綴字母

    public static List<String> randomList(String letter, int num) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            StringBuilder sb = new StringBuilder(letter);
            sb.append(i);
            result.add(sb.toString());
        }
        System.out.println(result);
        return result;

    }
}
