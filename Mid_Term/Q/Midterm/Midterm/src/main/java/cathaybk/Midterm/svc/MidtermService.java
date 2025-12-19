package cathaybk.Midterm.svc;

import java.util.Map;

public interface MidtermService {

    /**
     * 加分題:手動選擇送出人數
     * @param map
     * @return
     */
    Map<String, Object> demoCode(Map<String, String> map);

    /**
     * 固定玩家4人送出
     * @param map
     * @return
     */
    Map<String, Object> submit(Map<String, String> demoMap);
}
