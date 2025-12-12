package mid_term;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomString {

    // Random rd = new Random();
    // private final int num = (int) (Math.random() * 26) + 1; //1-26之間的亂數
    // private final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //前綴字母
    public static List<String> randomList(String letter, int totalAmount) {
        List<String> result = new ArrayList<>();

        for (int i = 1; i <= totalAmount; i++) {
            result.add(letter + i);
        }
        Collections.shuffle(result);
        // System.out.println(result);
        return result;
    }

    public static Map<Integer, List<String>> divideNumByForEach(int manCount, List<String> resultList) {
        /*
        使用For-each方法遍歷每一個randomList中的值，再以餘數的方式確認Num可以分幾輪。
         */
        Map<Integer, List<String>> resultMap = new HashMap<>();
        //初始化陣列在Map中放入(key(num),value(new ArrayList<>() ))
        for (int i = 1; i <= manCount; i++) {
            resultMap.put(i, new ArrayList<>());
        }
        //組裝Map中的List( <num,ArrayList<>() );
        int counter = 0;
        //e = 上一個功能中的result
        //resultList 是randomlist(每一個值都是一個value)
        for (String e : resultList) {
            counter++;
            List<String> ls = resultMap.get(counter);
            ls.add(e);
            if (counter % manCount == 0) {
                counter = 0;
            }
        }
        System.out.println();
        System.out.println();

        System.out.println(resultMap);
        return resultMap;
    }

    // public static Map<Integer, List<String>> divideNumByResult(int manCount, List<String> resultList) {
    // int resultSize = resultList.size();
    //     Map<Integer, List<String>> resultMap = new HashMap<>();
    //     if (manCount == 0) {
    //         System.out.println("數字不能為零");
    //         return null;
    //     }
    //     int counter = 0;
    //     int result = resultSize / manCount;
    //     int limit = result;
    //     // int remainder = resultSize % manCount;
    //     for (int i = 1; i <= manCount; i++) {
    //         if (i == manCount) {
    //             resultMap.put(i, resultList.subList(counter, resultSize));
    //             break;
    //         } 
    //             resultMap.put(i, resultList.subList(counter, limit));
    //             counter += result;
    //             limit = result + counter;
    //     }
    //     for (Map.Entry<Integer, List<String>> entry : resultMap.entrySet()) {
    //         System.out.println(entry.getKey() + " = " + entry.getValue());
    //     }
    //     return resultMap;
    // }
}
