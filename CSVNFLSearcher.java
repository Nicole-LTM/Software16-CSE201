import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVNFLSearcher {
    
    private static final String file = "./Documents/nfl_teams_players - Players.csv";
    
    public static void main(String[] args) throws FileNotFoundException {
        searchPlayersByName("Tom Brady");
    }
    
    public static void searchPlayersByName(String name) throws FileNotFoundException {
        try {
            Scanner read = new Scanner(new File(file));
            read.useDelimiter(",");
            while(read.hasNext()) {
                String inName = read.next();
                if(inName.equals(name)) {
                    String playerName = inName;
                    String playerTeam = read.next();
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("No connection to the CSV file.");
        }
    }
}
