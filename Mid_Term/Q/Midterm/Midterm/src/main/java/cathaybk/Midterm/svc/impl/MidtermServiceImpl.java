package cathaybk.Midterm.svc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import cathaybk.Midterm.DAO.CardDAO;
import cathaybk.Midterm.svc.MidtermService;

@Service
public class MidtermServiceImpl implements MidtermService {

    private final CardDAO cardDAO;

    public MidtermServiceImpl(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    private Map<Character, Integer> loadSuitScoreMap() {
        List<Map<String, Object>> suitList = cardDAO.getCards();
        Map<Character, Integer> map = new HashMap<>();
        for (Map<String, Object> row : suitList) {
            map.put(row.get("code").toString().charAt(0),
                    (int) row.get("value"));
        }
        return map;
    }

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
        Map<String, Object> result = new HashMap<>();

        Map<Character, Integer> suitScoreMap = loadSuitScoreMap();
        List<String> pokerSet = MidtermServiceImpl.pokerCard();
        Map<Integer, List<String>> card = MidtermServiceImpl.dealCard1(4, pokerSet);
        List<Map.Entry<Integer, Integer>> scores = ranking(card, suitScoreMap);
        List<Map<String, Object>> players = new ArrayList<>();

        //拿出在dealCard功能中分好的牌組。
        for (Map.Entry<Integer, Integer> entry : scores) {
            Integer player = entry.getKey();
            Integer score = entry.getValue();
            Map<String, Object> row = new HashMap<>();
            row.put("player", player);
            row.put("cards", card.get(player));
            row.put("score", score);
            players.add(row);
            result.put("players", players);

        }
        System.out.println(result);
        return result;
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
        String[] suits = {"S", "H", "D", "C"};
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
    public static List<Map.Entry<Integer, Integer>> ranking(Map<Integer, List<String>> resultMap, Map<Character, Integer> loadSuitScoreMap) {
        //計算分數，Map(玩家,總分)
        Map<Integer, Integer> playerMap = new HashMap<>();
        for (Map.Entry<Integer, List<String>> entry : resultMap.entrySet()) {
            int score = 0;
            List<String> cards = entry.getValue();
            // 第二層迴圈拆解(分數S/H/D/C分別對應四種花色)
            for (String letter : cards) {
                char suit = letter.charAt(0);
                int rank = Integer.parseInt(letter.substring(1));

                int multiplier = loadSuitScoreMap.get(suit);
                score += rank * multiplier;
                // //計算加權後的總分
                // switch (suit) {
                //     case 'A':
                //         score += rank * 6;
                //         break;
                //     case 'B':
                //         score += rank * 5;
                //         break;
                //     case 'C':
                //         score += rank * 3;
                //         break;
                //     default:
                //         score += rank * 2;
                //         break;
                // }
            }
            playerMap.put(entry.getKey(), score);
        }

        //按總分排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(playerMap.entrySet());
        list.sort((e1, e2) -> e2.getValue() - e1.getValue());
        //遍歷list
        for (Map.Entry<Integer, Integer> entry : list) {
            System.out.println(entry.getKey() + entry.getValue());//測試用
        }

        System.out.println(list);//測試用
        return list;

    }
}
