import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerStats {

    private ArrayList<String> usernames;
    private ArrayList<Integer> highScore;
    private ArrayList<Integer> multiplayerWins;

    public PlayerStats() throws FileNotFoundException {
        usernames=new ArrayList<>();
        highScore =new ArrayList<>();
        multiplayerWins = new ArrayList<>();
    }

    public boolean loadPlayerStats() throws FileNotFoundException {
        File stats = new File("Player Stats.txt");
        if (stats.exists()) {
            Scanner scanner = new Scanner(stats);
            while (scanner.hasNextLine()) {
                usernames.add(scanner.next());
                highScore.add(scanner.nextInt());
                multiplayerWins.add(scanner.nextInt());
                if (scanner.hasNextLine())
                    scanner.nextLine();
                else
                    break;
            }
            scanner.close();
            return true;
        }
        else
            return false;

    }

    public ArrayList<String> getUsernames(){
        return usernames;
    }

    public ArrayList<Integer> getHighScore(){
        return highScore;
    }

    public ArrayList<Integer> getMultiplayerWins(){
        return multiplayerWins;
    }
}
