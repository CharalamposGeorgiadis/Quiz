import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the player stats file.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/12/2020
 */

public class PlayerStats {

    private ArrayList<String> usernames; // ArrayList of Strings that holds the username of every player.
    private ArrayList<Integer> highScores; // ArrayList of Integers that holds the highscore of every player.
    private ArrayList<Integer> multiplayerWins; // ArrayList of Integers that holds the multiplayer wins of every player.

    /**
     * Constructor.
     */

    public PlayerStats() {
        usernames=new ArrayList<>();
        highScores =new ArrayList<>();
        multiplayerWins = new ArrayList<>();
    }

    /**
     * Gets the player's usernames.
     * @return ArrayList of Strings containing the player's usernames.
     */

    public ArrayList<String> getUsernames(){return usernames;}

    /**
     * Gets the player's highscores.
     * @return ArrayList of Integers containing the player's highscores.
     */

    public ArrayList<Integer> getHighScores(){return highScores;}

    /**
     * Gets the player's multiplayer wins.
     * @return ArrayList of Integers containing the player's multiplayer wins.
     */

    public ArrayList<Integer> getMultiplayerWins(){return multiplayerWins; }

    /**
     * Loads the player's stats.
     * @return True if file was found. False if file was not found.
     * @throws FileNotFoundException if file was not found.
     */

    public boolean loadPlayerStats() throws FileNotFoundException {
        File stats = new File("Player Stats.txt");
        if (stats.exists()) {
            Scanner scanner = new Scanner(stats);
            while (scanner.hasNextLine()) {
                usernames.add(scanner.next());
                highScores.add(scanner.nextInt());
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

    /**
     * Sorts stats by points.
     */

    public void sortStatsByPoints(){
        for (int i=1; i<=getHighScores().size()-1;i++){
            for (int j=1;j<=getHighScores().size()-i;j++){
                int first=getHighScores().get(j-1);
                int second=getHighScores().get(j)    ;
                if (first<second){
                    getHighScores().set(j-1,second);
                    getHighScores().set(j, first);
                    int tempWins= getMultiplayerWins().get(j-1);
                    getMultiplayerWins().set(j-1, getMultiplayerWins().get(j));
                    getMultiplayerWins().set(j,tempWins);
                    String tempUsername= getUsernames().get(j-1);
                    getUsernames().set(j-1, getUsernames().get(j));
                    getUsernames().set(j,tempUsername);
                }
            }
        }
    }

    /**
     * Sorts stats by multiplayer wins.
     */

    public void sortStatsByMultiplayerWins(){
        for (int i=1; i<=getMultiplayerWins().size()-1;i++){
            for (int j=1;j<=getMultiplayerWins().size()-i;j++){
                int first=getMultiplayerWins().get(j-1);
                int second=getMultiplayerWins().get(j)    ;
                if (first<second){
                    getMultiplayerWins().set(j-1,second);
                    getMultiplayerWins().set(j, first);
                    int tempPoints= getHighScores().get(j-1);
                    getHighScores().set(j-1, getHighScores().get(j));
                    getHighScores().set(j,tempPoints);
                    String tempUsername= getUsernames().get(j-1);
                    getUsernames().set(j-1, getUsernames().get(j));
                    getUsernames().set(j,tempUsername);
                }
            }
        }
    }
}