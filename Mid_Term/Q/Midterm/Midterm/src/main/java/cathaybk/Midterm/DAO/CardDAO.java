package cathaybk.Midterm.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository

public class CardDAO {
    //宣告資料庫
    private final DataSource dataSource;
    //SQL指令
    private static final String SELECT_SQL = "SELECT code, name, value FROM poker";
    //建立連線
    public CardDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    //取得資料
    public List<Map<String,Object>> getCards() {
        
        List<Map<String, Object>> cards = new ArrayList<>(); 
        //Preparestatement 建立連線
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_SQL);
         ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> map = new LinkedHashMap<>();
                //取得花色
                map.put("code", rs.getString("code"));
                //花色中文
                map.put("name", rs.getString("name"));
                //加權
                map.put("value", rs.getInt("value"));
                cards.add(map);
            }
        } catch (SQLException e) {
            System.err.println("連線測試失敗  " + e.getMessage());
            return null;
        }
        return cards;
    }
}
