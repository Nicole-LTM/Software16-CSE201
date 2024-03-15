import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NFLSearcher {

    private static final String URL = "jdbc:mysql://localhost:3306/nfl_db";
    private static final String USER = "yourUsername";
    private static final String PASSWORD = "yourPassword";

    public static void main(String[] args) {
        NFLSearcher searcher = new NFLSearcher();
        searcher.searchPlayersByName("Tom Brady");
    }

    public void searchPlayersByName(String playerName) {
        String query = "SELECT * FROM players WHERE name LIKE ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, "%" + playerName + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                // Assuming your players table has columns: id, name, team_id
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int teamId = rs.getInt("team_id");
                
                System.out.println("Player ID: " + id + ", Name: " + name + ", Team ID: " + teamId);
                // Optionally, join with the teams table to get the team name
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
