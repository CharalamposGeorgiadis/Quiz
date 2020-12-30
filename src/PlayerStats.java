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

    public ArrayList<String> getUsernames(){
        return usernames;
    }

    public ArrayList<Integer> getHighScore(){
        return highScore;
    }

    public ArrayList<Integer> getMultiplayerWins(){
        return multiplayerWins;
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

    public void sortStatsByPoints(){
        for (int i=1; i<=getHighScore().size()-1;i++){
            for (int j=1;j<=getHighScore().size()-i;j++){
                int first=getHighScore().get(j-1);
                int second=getHighScore().get(j)    ;
                if (first<second){
                    int tempPoints=first;
                    getHighScore().set(j-1,second);
                    getHighScore().set(j,tempPoints);
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

    public void sortStatsByMultiplayerWins(){
        for (int i=1; i<=getMultiplayerWins().size()-1;i++){
            for (int j=1;j<=getMultiplayerWins().size()-i;j++){
                int first=getMultiplayerWins().get(j-1);
                int second=getMultiplayerWins().get(j)    ;
                if (first<second){
                    int tempWins=first;
                    getMultiplayerWins().set(j-1,second);
                    getMultiplayerWins().set(j,tempWins);
                    int tempPoints= getHighScore().get(j-1);
                    getHighScore().set(j-1, getHighScore().get(j));
                    getHighScore().set(j,tempPoints);
                    String tempUsername= getUsernames().get(j-1);
                    getUsernames().set(j-1, getUsernames().get(j));
                    getUsernames().set(j,tempUsername);
                }
            }
        }
    }
}