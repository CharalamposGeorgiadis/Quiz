import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private ArrayList<Integer> multiplayerWins; // ArrayList of Integers that holds the multiplayer wins of every
    // player.

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
    /**
     * Adds/updates the current game's player stats to the Player Stats.txt.
     * @param players ArrayList of Player containing the player objects of the current game.
     * @throws IOException if stream to file cannot be written to or closed.
     */

    public void addStats(ArrayList<Player> players) throws IOException {
        File oldFile = new File("Player Stats.txt");
        Writer writer = new BufferedWriter(new FileWriter(oldFile, true));
        Scanner scanner = new Scanner(oldFile);
        if (!scanner.hasNextLine()) {
            for (Player p : players) {
                writer.append(p.getUsername()).append(" ").append(String.valueOf(p.getPoints())).append(" ").
                        append(String.valueOf(p.getMultiplayerWins())).append("\n");
            }
            scanner.close();
            writer.close();
        } else {
            // For every player saved in the txt file, checks if they have played on the current game.
            // If their score on the current game is higher than their score on the txt, their multiplayer wins on the
            // txt file are added to their current wins. Otherwise, the player's current game stats are swapped with
            // their stats from the txt file, after their multiplayer wins have been updated.
            ArrayList<Player> toRemove = new ArrayList<>(); //Contains the Player Objects that will be removed from the
            // players ArrayList.
            ArrayList<Player> toAdd = new ArrayList<>(); //Contains the Player Objects that will be added to the players
            // ArrayList.
            while (scanner.hasNextLine()) {
                boolean flag = false;
                Player tempPlayer = new Player(); //Temporary Player Object created from the current line of the txt
                // file.
                tempPlayer.setUsername(scanner.next());
                tempPlayer.setPoints(scanner.nextInt());
                tempPlayer.setMultiplayerWins(scanner.nextInt());
                for (Player p : players) {
                    if (tempPlayer.getUsername().equals(p.getUsername())) {
                        if (tempPlayer.getPoints() > p.getPoints()) {
                            tempPlayer.setMultiplayerWins(tempPlayer.getMultiplayerWins() + p.getMultiplayerWins());
                            toRemove.add(p);
                            toAdd.add(tempPlayer);
                        } else
                            p.setMultiplayerWins(p.getMultiplayerWins() + tempPlayer.getMultiplayerWins());
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    players.add(tempPlayer);
                if (scanner.hasNextLine())
                    scanner.nextLine();
                else
                    break;
            }
            players.removeAll(toRemove);
            players.addAll(toAdd);
            scanner.close();
            writer.close();
            Files.deleteIfExists(Paths.get("Player Stats.txt"));
            Writer newWriter = new BufferedWriter(new FileWriter("Player Stats.txt", true));
            for (Player p : players) {
                if (!p.getUsername().equals(players.get(players.size() - 1).getUsername()))
                    newWriter.append(p.getUsername()).append(" ").append(String.valueOf(p.getPoints())).append(" ").
                            append(String.valueOf(p.getMultiplayerWins())).append("\n");
                else
                    newWriter.append(p.getUsername()).append(" ").append(String.valueOf(p.getPoints())).append(" ").
                            append(String.valueOf(p.getMultiplayerWins()));
            }
            newWriter.close();
        }
    }
}