
/**
 * This class represents a player playing the game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Player {
    private String username; // String that holds the username of a player.
    private int points; // Integer that holds the points of a player.
    private String[] controls; // String Array that holds the controls of a player.
    private boolean hasAnswered; // Boolean containing if the player has answered or not.
    private int bet; // Integer containing the player's current bet.
    private int thermometerCorrectAnswers; //Integer containing the player's wins in THERMOMETER.
    private int multiplayerWins; // Integer containing the player's wins in multiplayer mode.

    /**
     * Constructor.
     */

    public Player() {
        username = "";
        points = 0;
        controls = new String[4];
        hasAnswered = false;
        bet = 0;
        thermometerCorrectAnswers = 0;
        multiplayerWins = 0;
    }

    /**
     * Gets the player's username.
     * @return String containing the player's username.
     */

    public String getUsername(){
        return username;
    }

    /**
     * Sets the player's username.
     * @param username String containing the player's username.
     */

    public void setUsername(String username){this.username = username;}

    /**
     * Gets the player's points.
     * @return Integer containing the player's points.
     */

    public int getPoints(){return points;}

    /**
     * Sets the player's points.
     * @param points Integer containing the player's points.
     */

    public void setPoints(int points){this.points = points;}

    /**
     * Gets one of the player's controls.
     * @param currentControl One of the player's controls.
     * @return String containing one of the player's controls.
     */

    public String getControl(int currentControl){return controls[currentControl];}

    /**
     * Sets the player's controls.
     * @param currentAnswer The answer of which we want to set the control (1 = A, 2 = B, 3 = C or 4 = D)
     * @param chosenControl String containing the control chosen by the player.
     */

    public void setPlayerControls(int currentAnswer, String chosenControl){
        controls[currentAnswer]=chosenControl.toUpperCase();
    }

    /**
     * Gets a boolean containing if the player has answered or not.
     * @return Boolean containing if the player has answered or not.
     */

    public boolean getHasAnswered(){return hasAnswered;}

    /**
     * Sets whether the player has answered or not.
     * @param answered Boolean containing if the player has answered or not.
     */

    public void setHasAnswered(Boolean answered){hasAnswered = answered;}

    /**
     * Gets the player's current bet.
     * @return Integer containing the player's current bet.
     */

    public int getBet(){return bet;}

    /**
     * Sets the player's current bet.
     * @param bet Integer containing the player's current bet.
     */

    public void setBet(int bet){this.bet = bet;}

    /**
     * Gets the player's wins in THERMOMETER.
     * @return Integer containing the player's wins in THERMOMETER.
     */

    public int getThermometerCorrectAnswers(){return thermometerCorrectAnswers;}

    /**
     * Sets the player's wins in THERMOMETER.
     * @param thermometerCorrectAnswers Integer containing the player's wins in THERMOMETER.
     */
    public void setThermometerCorrectAnswers(int thermometerCorrectAnswers){
        this.thermometerCorrectAnswers = thermometerCorrectAnswers;
    }

    /**
     * Gets the player's wins in multiplayer mode.
     * @return Integer containing the player's wins in multiplayer mode.
     */

    public int getMultiplayerWins(){return multiplayerWins;}

    /**
     * Sets the player's wins in multiplayer mode.
     * @param multiplayerWins Integer containing the player's wins in multiplayer mode.
     */

    public void setMultiplayerWins(int multiplayerWins) {this.multiplayerWins = multiplayerWins;}

    /**
     * Clears the player's controls.
     */

    public void clearControls(){
        for (int i = 0; i < 4; i++)
            setPlayerControls(i,"");
    }
}