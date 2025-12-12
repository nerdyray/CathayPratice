// package mid_term;

// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Random;

// public class RandomNum {

//     public class RandomString {

//         Random rd = new Random();
//         private static int resultSize;
//         // private final int num = (int) (Math.random() * 26) + 1; //1-26之間的亂數
//         // private final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //前綴字母

//         public static Map<Integer, List<String>> randomList(int manCount, List<String> resultList) {
//             Map<Integer, List<String>> resultMap = new HashMap<>();
//             List<String> result = new ArrayList<>();
//             for (int i = 1; i <= totalAmount; i++) {
//                 result.add(letter + i);
//             }
//             Collections.shuffle(result);
//             // System.out.println(result);
//             resultSize = result.size();
//             return result;
//         }

//         public static Map<Integer, List<String>> divideNumByForEach(int manCount, List<String> resultList) {
//             /*
//         使用For-each方法遍歷每一個randomList中的值，再以餘數的方式確認Num可以分幾輪。
//              */
//             Map<Integer, List<String>> resultMap = new HashMap<>();
//             //初始化陣列在Map中放入(key(num),value(new ArrayList<>() ))
//             for (int i = 1; i <= manCount; i++) {
//                 resultMap.put(i, new ArrayList<>());
//             }
//             //組裝Map中的List( <num,ArrayList<>() );
//             int counter = 0;
//             //e = 上一個功能中的result
//             //resultList 是randomlist(每一個值都是一個value)
//             for (String e : resultList) {
//                 counter++;
//                 List<String> ls = resultMap.get(counter);
//                 ls.add(e);
//                 if (counter % manCount == 0) {
//                     counter = 0;
//                 }
//             }
//             System.out.println();
//             System.out.println();

//             System.out.println(resultMap);
//             return resultMap;
//         }
//     }
// }
