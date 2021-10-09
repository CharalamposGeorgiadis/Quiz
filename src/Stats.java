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

public class Stats {

    private ArrayList<String> usernames; // ArrayList of Strings that holds the username of every player.
    private ArrayList<Integer> playerHighScores; // ArrayList of Integers that holds the highscore of every player.
    private ArrayList<Integer> multiplayerWins; // ArrayList of Integers that holds the multiplayer wins of every
    // player.
    private ArrayList<String> teamNames; // ArrayList of Strings that holds the name of every team.
    private ArrayList<Integer> teamHighScores; // ArrayList of Integers that holds the highscore of every team.
    private ArrayList<Integer> teamWins; // ArrayList of Integers that holds the wins of every team.

    /**
     * Constructor.
     */

    public Stats() {
        usernames = new ArrayList<>();
        playerHighScores = new ArrayList<>();
        multiplayerWins = new ArrayList<>();
        teamNames = new ArrayList<>();
        teamHighScores = new ArrayList<>();
        teamWins = new ArrayList<>();
    }

    /**
     * Gets each player's usernames.
     * @return ArrayList of Strings containing each player's usernames.
     */

    public ArrayList<String> getUsernames(){return usernames;}

    /**
     * Gets each player's highscore.
     * @return ArrayList of Integers containing each player's highscore.
     */

    public ArrayList<Integer> getPlayerHighScores(){return playerHighScores;}

    /**
     * Gets each player's multiplayer wins.
     * @return ArrayList of Integers containing each player's multiplayer wins.
     */

    public ArrayList<Integer> getMultiplayerWins(){return multiplayerWins;}

    /**
     * Gets each team's name.
     * @return ArrayList of Strings containing each team's name.
     */

    public ArrayList<String> getTeamNames(){return teamNames;}

    /**
     * Gets each team's highscore.
     * @return ArrayList of Integers containing each team's highscore.
     */

    public ArrayList<Integer> getTeamHighScores(){return teamHighScores;}

    /**
     * Gets each team's wins.
     * @return ArrayList of Integers containing each team's wins.
     */

    public ArrayList<Integer> getTeamWins(){return teamWins;}

    /**
     * Loads the player's stats.
     * @return True if file was found. False if file was not found.
     * @throws FileNotFoundException if file was not found.
     */

    public boolean loadStats() throws FileNotFoundException {
        File playerStats = new File("Player Stats.txt");
        File teamStats = new File("Team Stats.txt");
        if (playerStats.exists() && teamStats.exists()) {
            Scanner scannerPlayer = new Scanner(playerStats);
            while (scannerPlayer.hasNextLine()) {
                usernames.add(scannerPlayer.next());
                playerHighScores.add(scannerPlayer.nextInt());
                multiplayerWins.add(scannerPlayer.nextInt());
                if (scannerPlayer.hasNextLine())
                    scannerPlayer.nextLine();
                else
                    break;
            }
            scannerPlayer.close();
            Scanner scannerTeam = new Scanner(teamStats);
            while (scannerTeam.hasNextLine()) {
                teamNames.add(scannerTeam.next());
                teamHighScores.add(scannerTeam.nextInt());
                teamWins.add(scannerTeam.nextInt());
                if (scannerTeam.hasNextLine())
                    scannerTeam.nextLine();
                else
                    break;
            }
            scannerTeam.close();
            return true;
        }
        else
            return false;
    }

    /**
     * Sorts stats based on the chosen sorting.
     * @param sorting String containing the chosen sorting.
     */

    public void sortStats(String sorting){
        switch(sorting){
            case "PLAYER HIGHSCORES":
                for (int i = 1; i <= getPlayerHighScores().size() - 1; i++){
                    for (int j = 1; j <= getPlayerHighScores().size() - i; j++){
                        int first = getPlayerHighScores().get(j - 1);
                        int second = getPlayerHighScores().get(j);
                        if (first < second){
                            getPlayerHighScores().set(j - 1, second);
                            getPlayerHighScores().set(j, first);
                            int tempWins = getMultiplayerWins().get(j - 1);
                            getMultiplayerWins().set(j - 1, getMultiplayerWins().get(j));
                            getMultiplayerWins().set(j, tempWins);
                            String tempUsername = getUsernames().get(j - 1);
                            getUsernames().set(j - 1, getUsernames().get(j));
                            getUsernames().set(j, tempUsername);
                        }
                    }
                }
                break;
            case "MULTIPLAYER":
                for (int i = 1; i <= getMultiplayerWins().size() - 1; i++){
                    for (int j = 1; j <= getMultiplayerWins().size() - i; j++){
                        int first = getMultiplayerWins().get(j - 1);
                        int second = getMultiplayerWins().get(j);
                        if (first < second){
                            getMultiplayerWins().set(j - 1,second);
                            getMultiplayerWins().set(j, first);
                            int tempPoints = getPlayerHighScores().get(j - 1);
                            getPlayerHighScores().set(j - 1, getPlayerHighScores().get(j));
                            getPlayerHighScores().set(j, tempPoints);
                            String tempUsername = getUsernames().get(j - 1);
                            getUsernames().set(j - 1, getUsernames().get(j));
                            getUsernames().set(j, tempUsername);
                        }
                    }
                }
                break;
            case "TEAM HIGHSCORES":
                for (int i = 1; i <= getTeamHighScores().size() - 1; i++){
                    for (int j = 1; j <= getTeamHighScores().size() - i; j++){
                        int first = getTeamHighScores().get(j - 1);
                        int second = getTeamHighScores().get(j);
                        if (first < second){
                            getTeamHighScores().set(j - 1, second);
                            getTeamHighScores().set(j, first);
                            int tempWins = getTeamWins().get(j - 1);
                            getTeamWins().set(j - 1, getTeamWins().get(j));
                            getTeamWins().set(j, tempWins);
                            String tempName = getTeamNames().get(j - 1);
                            getTeamNames().set(j - 1, getTeamNames().get(j));
                            getTeamNames().set(j, tempName);
                        }
                    }
                }
                break;
            case "TEAM WINS":
                for (int i = 1; i <= getTeamWins().size() - 1; i++){
                    for (int j = 1; j <= getTeamWins().size() - i; j++){
                        int first = getTeamWins().get(j - 1);
                        int second = getTeamWins().get(j);
                        if (first < second){
                            getTeamWins().set(j - 1, second);
                            getTeamWins().set(j, first);
                            int tempPoints = getTeamHighScores().get(j - 1);
                            getTeamHighScores().set(j - 1, getTeamHighScores().get(j));
                            getTeamHighScores().set(j, tempPoints);
                            String tempName = getTeamNames().get(j - 1);
                            getTeamNames().set(j - 1, getTeamNames().get(j));
                            getTeamNames().set(j, tempName);
                        }
                    }
                }
                break;
        }
    }

    /**
     * Adds/updates the current game's player stats to the Player Stats.txt.
     * @param players ArrayList of Player Objects containing the players of the current game.
     * @throws IOException if stream to file cannot be written to or closed.
     */

    public void addPlayerStats(ArrayList<Player> players) throws IOException {
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

    /**
     * Adds/updates the current game's team stats to the Team Stats.txt.
     * @param teams ArrayList of Team Objects containing the teams of the current game.
     * @throws IOException if stream to file cannot be written to or closed.
     */

    public void addTeamStats(ArrayList<Team> teams) throws IOException {
        File oldFile = new File("Team Stats.txt");
        Writer writer = new BufferedWriter(new FileWriter(oldFile, true));
        Scanner scanner = new Scanner(oldFile);
        if (!scanner.hasNextLine()) {
            for (Team t : teams) {
                writer.append(t.getName()).append(" ").append(String.valueOf(t.getPoints())).append(" ").
                        append(String.valueOf(t.getWins())).append("\n");
            }
            scanner.close();
            writer.close();
        } else {
            // For every team saved in the txt file, checks if they have played on the current game.
            // If their score on the current game is higher than their score on the txt, their wins on the
            // txt file are added to their current wins. Otherwise, the team's current game stats are swapped with
            // their stats from the txt file, after their wins have been updated.
            ArrayList<Team> toRemove = new ArrayList<>(); // Contains the Teams Objects that will be removed from the
            // teams ArrayList.
            ArrayList<Team> toAdd = new ArrayList<>(); // Contains the Team Objects that will be added to the teams
            // ArrayList.
            while (scanner.hasNextLine()){
                boolean flag = false;
                Team tempTeam = new Team(); // Temporary Team Object created from the current line of the txt file
                tempTeam.setName(scanner.next());
                tempTeam.setPoints(scanner.nextInt());
                tempTeam.setWins(scanner.nextInt());
                for (Team t : teams) {
                    if (tempTeam.getName().equals(t.getName())) {
                        if (tempTeam.getPoints() > t.getPoints()) {
                            tempTeam.setWins(tempTeam.getWins() + t.getWins());
                            toRemove.add(t);
                            toAdd.add(tempTeam);
                        } else
                            t.setWins(t.getWins() + tempTeam.getWins());
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    teams.add(tempTeam);
                if (scanner.hasNextLine())
                    scanner.nextLine();
                else
                    break;
            }
            teams.removeAll(toRemove);
            teams.addAll(toAdd);
            scanner.close();
            writer.close();
            Files.deleteIfExists(Paths.get("Team Stats.txt"));
            Writer newWriter = new BufferedWriter(new FileWriter("Team Stats.txt", true));
            for (Team t : teams) {
                if (!t.getName().equals(teams.get(teams.size() - 1).getName()))
                    newWriter.append(t.getName()).append(" ").append(String.valueOf(t.getPoints())).append(" ").
                            append(String.valueOf(t.getWins())).append("\n");
                else
                    newWriter.append(t.getName()).append(" ").append(String.valueOf(t.getPoints())).append(" ").
                            append(String.valueOf(t.getWins()));
            }
            newWriter.close();
        }
    }
}