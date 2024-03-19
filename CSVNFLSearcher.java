import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVNFLSearcher {
    
    private static final String file = "nfl_teams_players - Players.csv";
    
    public static void main(String[] args) throws FileNotFoundException {
        searchPlayersByName("Zay Flowers");
        searchPlayersByName("Alex Highsmith");
        searchPlayersByName("Patrick Mahomes");
    }
    
    public static void searchPlayersByName(String name) throws FileNotFoundException {
        try {
            Scanner read = new Scanner(new File(file));
            read.useDelimiter(",");
            while(read.hasNext()) {
                String inName = read.next();
                if(inName.contains(name)) {
                    String playerName = inName.substring(inName.indexOf(name.charAt(0)));
                    String playerTeam = inName.substring(0, inName.indexOf(name.charAt(0)));
                    System.out.println("Player: " + playerName + " Team: " + playerTeam);
                    return;
                }
            }
            System.out.println("Player not found in file.");
            
        } catch(FileNotFoundException e) {
            System.out.println("No connection to the CSV file.");
        }
    }
}
