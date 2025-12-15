package cathaybk.Midterm.svc;

import java.util.Map;

public interface MidtermService {

    /**
     * 範例程式
     * @param map
     * @return
     */
    Map<String, Object> demoCode(Map<String, String> map);

    /**
     * 送出
     * @param map
     * @return
     */
    Map<String, Object> submit(Map<String, String> map);
}
