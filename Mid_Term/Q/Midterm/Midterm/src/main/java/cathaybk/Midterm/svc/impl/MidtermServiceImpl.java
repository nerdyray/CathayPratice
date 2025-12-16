package cathaybk.Midterm.svc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cathaybk.Midterm.svc.MidtermService;

@Service
public class MidtermServiceImpl implements MidtermService {

    @Override
    public Map<String, Object> demoCode(Map<String, String> demoMap) {
        String id = demoMap.get("id");
        String keyword = demoMap.get("keyword");
        System.err.println("id: " + id);
        System.err.println("keyword: " + keyword);

        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("success", true);
        rtnMap.put("returnMessage", "驗證成功");
        rtnMap.put("metro_fee", 100);
        rtnMap.put("pokerA", new ArrayList<>());
        return rtnMap;
    }

    @Override
    public Map<String, Object> submit(Map<String, String> map) {
        return null;
    }

    /**
     * 使用for迴圈得出0-51作為總數 Suits為四種花色，使用第二個迴圈把第一個迴圈的數字加上花色 回傳值為List
     *
     * @param map
     * @return
     */
    public static List<String> pokerCard() {
        List<String> deck = new ArrayList<>();
        int[] num = new int[52];
        String[] suits = {"A", "B", "C", "D"};
        for (int i = 0; i < 52; i++) {
            num[i] = i;
        }
        for (int n : num) {
            String suit = suits[n / 13];
            int rank = (n % 13) + 1;
            deck.add(suit + rank);
        }
        Collections.shuffle(deck);//先將排序打亂
        // System.out.println(deck);
        // System.out.println();
        // System.out.println(deck);

        return deck;
    }

    /**
     * 發牌功能1
     * 生成撲克牌的功能中就已經使用Collection.shuffle打亂排序，dealCard1功能中就使用SubList把已打亂的牌均分成四等份。
     *
     * @param manCount
     * @param deck
     */
    public static Map<Integer, List<String>> dealCard1(int manCount, List<String> deck) {
        int resultSize = deck.size();
        Map<Integer, List<String>> resultMap = new HashMap<>();
        if (manCount == 0) {
            System.out.println("數字不能為零");
        }
        int counter = 0;
        int result = resultSize / manCount;
        int limit = result;
        // int remainder = resultSize % manCount;
        for (int i = 1; i <= manCount; i++) {
            if (i == manCount) {
                resultMap.put(i, deck.subList(counter, resultSize));
                break;
            }
            resultMap.put(i, deck.subList(counter, limit));
            counter += result;
            limit = result + counter;
        }

        for (Map.Entry<Integer, List<String>> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        return resultMap;
    }

    //計算每張牌的分數並排名
    public static Map<Integer, Integer> ranking(Map<Integer, List<String>> resultMap) {
        //計算分數，Map(玩家,總分)
        Map<Integer, Integer> playerMap = new HashMap<>();
        for (Map.Entry<Integer, List<String>> entry : resultMap.entrySet()) {
            int score = 0;
            List<String> cards = entry.getValue();
            // 第二層迴圈拆解(分數A/B/C/D分別對應四種花色)
            for (String letter : cards) {
                char suit = letter.charAt(0);
                String numberPart = letter.substring(1);
                int rank = Integer.parseInt(numberPart);
                //計算加權後的總分
                switch (suit) {
                    case 'A':
                        score += rank * 6;
                        break;
                    case 'B':
                        score += rank * 5;
                        break;
                    case 'C':
                        score += rank * 3;
                        break;
                    default:
                        score += rank * 2;
                        break;
                }
            }
            playerMap.put(entry.getKey(), score);
        }

        //按玩家排序總分排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(playerMap.entrySet());
        list.sort((e1, e2) -> e2.getValue() - e1.getValue());
        for (Map.Entry<Integer, Integer> entry : list) {
            System.out.println( entry.getKey() + entry.getValue());//測試用
        }

        System.out.println(playerMap);//測試用
        return playerMap;

    }
}
