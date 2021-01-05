
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
    private boolean hasAnswered;
    private int bet;
    private int thermometerCorrectAnswers;
    private int multiplayerWins;

    /**
     * Constructor
     */

    public Player() {
        username="";
        points=0;
        controls= new String[4];
        hasAnswered=false;
        bet=0;
        thermometerCorrectAnswers=0;
        multiplayerWins=0;
    }

    /**
     * Gets the player's username.
     * @return A String containing the player's username.
     */

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    /**
     * Gets the player's points.
     * @return An Integer containing the player's points.
     */

    public int getPoints(){
        return points;
    }

    /**
     * Sets the player's points.
     * @param points  A String containing the player's points.
     */

    public void setPoints(int points){
        this.points=points;
    }

    /**
     * Gets one of the player's controls.
     * @param currentControl One of the player's controls.
     * @return A char containing one of the player's controls.
     */

    public String getControl(int currentControl){
        return controls[currentControl];
    }

    /**
     * Sets the player's controls.
     * @param currentAnswer The answer of which we want to set the control (A, B, C or D)
     * @param chosenControl A String containing the control chosen by the player.
     */

    public void setPlayerControls(int currentAnswer, String chosenControl){
        controls[currentAnswer]=chosenControl.toUpperCase();
    }

    public boolean getHasAnswered(){
        return hasAnswered;
    }

    public void setHasAnswered(Boolean answered){
        hasAnswered=answered;
    }

    public int getBet(){
        return bet;
    }

    public void setBet(int bet){
        this.bet=bet;
    }

    public int getThermometerCorrectAnswers(){
        return thermometerCorrectAnswers;
    }

    public void setThermometerCorrectAnswers(int thermometerCorrectAnswers){this.thermometerCorrectAnswers=thermometerCorrectAnswers;}


    public int getMultiplayerWins(){
        return multiplayerWins;
    }

    public void setMultiplayerWins(int multiplayerWins) {
        this.multiplayerWins = multiplayerWins;
    }

    public void clearControls(){
        for (int i=0; i<4; i++)
            setPlayerControls(i,"");
    }

    /**
     * Adds points to the player.
     * @param points An Integer containing the points that will be added.
     */

//    public void addPoints(int points) {
//        this.points+=points;
//    }
}