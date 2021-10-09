import java.util.ArrayList;

/**
 * This class represents a team.
 * @author Charalampos Georgiadis
 * @version 06/10/2020
 */

public class Team {
    private int points; // Integer containing the combined points of a team.
    private int wins; // Integer containing the team's wins.
    private String name; // String containing the name of the team.
    private ArrayList<Player> players; // Array of Player objects containing the players of the team.


    /**
     * Constructor
     */
    public Team(){
        players = new ArrayList<>();
    }

    /**
     * Sets the name of a team.
     * @param name String containing the name of a team.
     */

    public void setName(String name){this.name = name;}

    /**
     * Gets the name of a team.
     * @return String containing the name of a team.
     */

    public String getName(){return name;}

    /**
     * Sets the points of a team.
     * @param points Integer containing the points of a team.
     */

    public void setPoints(int points){this.points = points;}

    /**
     * Gets the points of a team.
     * @return Integer containing the points of a team.
     */

    public int getPoints(){return points;}

    /**
     * Sets the wins of the team.
     * @param wins Integer containing the wins of a team.
     */

    public void setWins(int wins){this.wins = wins;}

    /**
     * Gets the wins of the team.
     * @return Integer containing the wins of a team.
     */

    public int getWins(){return wins;}

    /**
     * Gets the players that belong to a team.
     * @return ArrayList of Player Objects containing the players that belong to a team.
     */

    public ArrayList<Player> getPlayers(){return players;}

    /**
     * Checks whether a team has already been filled or not
     * @param numberOfPlayers Integer containing the number of players that are in each team.
     * @return True if a team has been filled and false if it has not been filled.
     */

    public boolean checkIfTeamIsFilled(int numberOfPlayers){return players.size() == numberOfPlayers;}
}
