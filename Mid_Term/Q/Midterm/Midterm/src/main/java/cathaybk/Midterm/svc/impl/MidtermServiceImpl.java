package cathaybk.Midterm.svc.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    public static int[] dealCards() {
        int[] num = new int[52];
        for (int i = 0; i < 52; i++) {
            num[i] = (i % 13) + 1;
        }
        System.out.println(Arrays.toString(num));
        return num;
    }
}
