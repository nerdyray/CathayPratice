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

    private final DataSource dataSource;
    
    public CardDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public List<Map<String,Object>> getCards() {
        List<Map<String, Object>> cards = new ArrayList<>();
        String SelectSql = "select code, name, value from poker";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(SelectSql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("code", rs.getString("code"));
                map.put("name", rs.getString("name"));
                map.put("value", rs.getInt("value"));
                cards.add(map);
            }
        } catch (SQLException e) {
            System.err.println("連線測試失敗  " + e.getMessage());
        }
        return cards;
    }
}
