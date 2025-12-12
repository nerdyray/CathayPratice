package mid_term;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomString {

    Random rd = new Random();
    private static int resultSize;
    // private final int num = (int) (Math.random() * 26) + 1; //1-26之間的亂數
    // private final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //前綴字母

    public static List<String> randomList(String letter, int num) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            result.add(letter + i);
        }
        Collections.shuffle(result);
        System.out.println(result);
        resultSize = result.size();
        return result;
    }

    public static Map<Integer, List<String>> divideNum(int num, List<String> resultList) {
        Map<Integer, List<String>> resultMap = new HashMap<>();
        //初始化陣列
        for (int i = 1; i <= num; i++) {
            resultMap.put(i, new ArrayList<>());
        }
        //組裝Map<num,<List.
        int counter = 0;
        //e = 上一個功能中的result
        //resultList 是randomlist(每一個值都是e)
        for (String e : resultList) {
            counter++;
            List<String> ls = resultMap.get(counter);
            ls.add(e);
            if(counter%num==0){
                counter=0;
            }
        }
        System.out.println(resultMap);
        return resultMap;
        // System.out.println(resultMap.get(counter));
        // if (num == 0 || resultSize == 0) {
        //     System.out.println("數字不能為零");
        // } else {
        //     int result = resultSize / num;
        //     int remainder = resultSize % num;
        //     resultList.add(result);
        //     resultMap.put(num, resultList);
        // }
        // return resultMap;
    }
}
