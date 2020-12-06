
/**
 * This class represents a player playing the game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Player {
    private String username; // String that holds the username of a player.
    private int points; // Integer that holds the points of a player.
    private char[] controls; // Char Array that holds the controls of a player.

    /**
     * Constructor
     * @param username The player's username.
     */

    Player(String username){
        this.username=username;
        points=0;
        controls= new char[4];
    }

    /**
     * Sets the player's controls.
     * @param currentAnswer The answer of which we want to set the control (A, B, C or D)
     * @param chosenControl A String containing the control chosen by the player.
     */

    public void setPlayerControls(int currentAnswer, char chosenControl){
        controls[currentAnswer]=chosenControl;
    }

    /**
     * Gets the player's username.
     * @return A String containing the player's username.
     */

    public String getUsername(){
        return username;
    }

    /**
     * Gets the player's points.
     * @return An Integer containing the player's points.
     */

    public int getPoints(){
        return points;
    }

    /**
     * Gets one of the player's controls.
     * @param currentControl One of the player's controls.
     * @return A char containing one of the player's controls.
     */

    public char getControl(int currentControl){
        return controls[currentControl];
    }

    /**
     * Sets the player's points.
     * @param points  A String containing the player's points.
     */

    public void setPoints(int points){
        this.points=points;
    }

    /**
     * Adds points to the player.
     * @param points An Integer containing the points that will be added.
     */

    public void addPoints(int points) {
        this.points+=points;
    }
}