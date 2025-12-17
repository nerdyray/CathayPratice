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

    //宣告CardDAO
    private final CardDAO cardDAO;

    //資料庫連接建立物件
    public MidtermServiceImpl(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    //載入資料庫方法
    private Map<Character, Integer> loadSuitScoreMap() {
        //建立花色和分數加權
        List<Map<String, Object>> suitList = cardDAO.getCards();
        Map<Character, Integer> map = new HashMap<>();
        for (Map<String, Object> row : suitList) {
            //code代表花色，value代表加權
            map.put(row.get("code").toString().charAt(0),
                    (int) row.get("value"));
        }
        return map;
    }

    /**
     * 第二種發牌方法，用在加分題
     *
     * @param demoMap
     * @return
     */
    @Override
    public Map<String, Object> demoCode(Map<String, String> demoMap) {
        Map<String, Object> result = new HashMap<>();
        //宣告遊戲人數
        int manCount;
        //驗證輸入
        try {
            manCount = Integer.parseInt(demoMap.get("manCount"));

            //人數必須是數字
        } catch (Exception e) {
            throw new IllegalArgumentException("manCount must be a number");
        }
        //人數必須大於2
        if (manCount < 2) {
            throw new IllegalArgumentException("manCount must be greater than 2");
        }
        //人數必須小於總牌數52
        if (manCount > 52) {
            throw new IllegalArgumentException("manCount cannot exceed 52");
        }
        //載入資料庫
        Map<Character, Integer> suitScoreMap = loadSuitScoreMap();
        //調用牌組
        List<String> pokerSet = MidtermServiceImpl.pokerCard();
        //使用方法2發牌
        Map<Integer, List<String>> card = MidtermServiceImpl.dealCard2(manCount, pokerSet);
        //ranking總分並排名
        List<Map.Entry<Integer, Integer>> scores = ranking(card, suitScoreMap);
        //建立玩家List
        List<Map<String, Object>> players = new ArrayList<>();

        //把Map裡的資料拿出來組裝成JSON
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
        return result;
    }

    /**
     * 人數固定的情況下使用方法1
     */
    @Override
    public Map<String, Object> submit(Map<String, String> map) {
        Map<String, Object> result = new HashMap<>();
        //載入資料庫方法
        Map<Character, Integer> suitScoreMap = loadSuitScoreMap();
        //調用牌組
        List<String> pokerSet = MidtermServiceImpl.pokerCard();
        //使用方法2發牌，參加遊戲的人數寫死
        Map<Integer, List<String>> card = MidtermServiceImpl.dealCard1(4, pokerSet);
        //ranking總分並排名
        List<Map.Entry<Integer, Integer>> scores = ranking(card, suitScoreMap);
        //建立玩家List
        List<Map<String, Object>> players = new ArrayList<>();
        //把Map裡的資料拿出來組裝成JSON
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
        return result;
    }

    /**
     * 使用for迴圈得出0-51作為撲克牌總數
     * Suits為四種花色(Spades,Hearts,Diamonds,Clubs)，使用第二個迴圈把第一個迴圈的數字加上花色 回傳值為List
     *
     * @param map
     * @return
     */
    public static List<String> pokerCard() {
        List<String> deck = new ArrayList<>();
        //一組牌52張
        int[] num = new int[52];
        //分別代表(黑桃，紅心，方塊，梅花)
        String[] suits = {"S", "H", "D", "C"};
        for (int i = 0; i < 52; i++) {
            num[i] = i;
        }
        for (int n : num) {
            //一副牌四種花色
            String suit = suits[n / 13];
            //把牌分成1-13
            int rank = (n % 13) + 1;
            //組合起來eg.(黑桃Q = "S12")
            deck.add(suit + rank);
        }
        //將排序打亂後續在固定四人的情況下就不用重新發牌
        Collections.shuffle(deck);
        return deck;
    }

    /**
     * 發牌功能1
     * 生成撲克牌的功能中就已經使用Collection.shuffle打亂排序，dealCard1功能中只需要使用SubList把已打亂的牌均分成四等份。
     *
     * @param manCount
     * @param deck
     */
    public static Map<Integer, List<String>> dealCard1(int manCount, List<String> deck) {
        //取得陣列長度
        int resultSize = deck.size();
        int counter = 0;
        //計數器
        Map<Integer, List<String>> resultMap = new HashMap<>();
        //選擇分成幾等份,在這個方法中會將多餘的直接丟掉
        int result = resultSize / manCount;
        //subList index尾端限制
        int limit = result;
        //使用For迴圈把整個陣列分4等分
        for (int i = 1; i <= manCount; i++) {
            if (i == manCount) {
                resultMap.put(i, deck.subList(counter, resultSize));
                break;
            }
            resultMap.put(i, deck.subList(counter, limit));
            counter += result;
            limit = result + counter;
        }
        return resultMap;
    }

    /**
     * 第二種發牌邏輯，當人數不能被整除時就循環發放
     *
     * @param manCount
     * @param deck
     * @return
     */
    public static Map<Integer, List<String>> dealCard2(int manCount, List<String> deck) {
        Map<Integer, List<String>> resultMap = new HashMap<>();
        //初始化Key，(Map(i,List<String>))
        for (int i = 1; i <= manCount; i++) {

            resultMap.put(i, new ArrayList<>());
        }
        //把撲克牌循環發給玩家
        int count = 1;//計數器
        for (String c : deck) {
            resultMap.get(count).add(c);
            count++;
            if (count > manCount) {
                //超過人數時就歸零計數
                count = 1;
            }
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
            }
            playerMap.put(entry.getKey(), score);
        }

        //按總分排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(playerMap.entrySet());
        list.sort((e1, e2) -> e2.getValue() - e1.getValue());
        return list;

    }
}
