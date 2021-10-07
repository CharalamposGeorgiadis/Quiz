import java.util.*;

/**
 * This class represents a round in the game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Round {

    protected ArrayList<String> roundTypes; // List of Strings that holds the type of each round type.
    final String rightAnswerDescription = ("Correct answers grant 1000 points, incorrect answers grant 0.");
    final String bettingDescription = ("Bet points for each question. Correct answers grant points equal to the ones "
            + "you bet. Incorrect answers subtract the same amount.");
    final String countdownDescription = ("You have 6 seconds for each question. Correct answers grant points equal to "
            + "the remaining time (in milliseconds)x0.2");
    private String fastestFingerDescription = ("First player to answer correctly gains 1000 points. " +
            "The second one gains 500.");
    private String thermometerDescription = ("First player to answer 5 questions correctly gains 5000 points. Then, or "
            + "if there are no more questions left, the game ends.");
    private String votingDescription = ("Each team member votes for the correct answer. Every correct vote grants " +
            "1000 points, while every wrong vote subtract 500 points. Votes are shown to every player.");
    private int totalAnswered; // Integer containing the total amount of players who have answered in "FASTEST FINGER".
    private int totalCorrect; // Integer containing the total amount of players who have answered correctly in
    // "FASTEST FINGER".


    /**
     * Constructor.
     */

    public Round() {
        roundTypes = new ArrayList<>();
        addEssentialRoundTypes();
        totalAnswered = 0;
        totalCorrect = 0;
    }

    /**
     * Gets the name of every round type.
     * @return ArrayList of Strings containing the name of every round type.
     */

    public ArrayList<String> getRoundTypes() {
        return roundTypes;
    }

    /**
     * Gets the description of the current round type.
     * @param currentRound String containing the name of the current round type.
     * @return String containing the description of the current round type.
     */

    public String getRoundDescription(String currentRound){
        switch (currentRound){
            case "RIGHT ANSWER":
                return rightAnswerDescription;
            case "BETTING":
                return bettingDescription;
            case "COUNTDOWN":
                return countdownDescription;
            case "FASTEST FINGER":
                return fastestFingerDescription;
            case "THERMOMETER":
                return thermometerDescription;
            case "VOTING":
                return votingDescription;
        }
        return "";
    }

    /**
     * Adds the essential rounds to the roundTypes ArrayList.
     */
    // If a new round type that can be played in single and multiplayer is to be added, add its name in Capital letters.
    public void addEssentialRoundTypes(){
     //   roundTypes.add("RIGHT ANSWER");
       // roundTypes.add("BETTING");
      //  roundTypes.add("COUNTDOWN");
        Collections.shuffle(roundTypes);
    }

    /**
     * Adds the multiplayer rounds to the roundTypes ArrayList.
     * @param numberOfPlayers Integer containing the number of players for the current game.
     */
    // If a new Multiplayer-only round is to be added, add its name in Capital letters.
    public void addMultiplayerRounds(int numberOfPlayers){
     //  roundTypes.add("FASTEST FINGER");
       if (numberOfPlayers == 3)
           fastestFingerDescription = ("First player to answer correctly gains 1000 points. The second one gains 750, "
                   + "while the third one gains 500.");
       else if (numberOfPlayers == 4)
           fastestFingerDescription = ("First player to answer correctly gains 1000 points. The second one gains 750, "
                   + "the third one gains 500 and the fourth one gains 250.");
       Collections.shuffle(roundTypes);
       roundTypes.add("THERMOMETER");
    }

    /**
     * Adds the team-mode rounds to the roundTypes ArrayList.
     */
    // If a new team-only round is to be added, add its name in Capital letters.
    public void addTeamRounds(){
        roundTypes.add("VOTING");
        roundTypes.remove("THERMOMETER");
        Collections.shuffle(roundTypes);
        roundTypes.add("THERMOMETER");
        thermometerDescription = ("First team to answer 5 questions correctly gains 5000 points. Then, or " +
                "if there are no more questions left, the game ends.");
    }

    /**
     * Calculates the current player's points based on the current round type.
     * @param answered Boolean containing whether the player has answered correctly or wrongly.
     * @param currentRound String containing the name of the current round type.
     * @param currentPlayer Player Object containing the player of which the points will be calculated.
     * @param currentRoundParameter Integer containing  a parameter that may be required by a certain round type.
     * @param totalPlayers Integer containing the total amount of players.
     */

    public void calculatePoints(Boolean answered, String currentRound, Player currentPlayer, int currentRoundParameter,
                                int totalPlayers){
        switch (currentRound){
            case "RIGHT ANSWER":
                if (answered)
                    currentPlayer.setPoints(currentPlayer.getPoints() + 1000);
                break;
            case "BETTING":
                if (answered)
                    currentPlayer.setPoints(currentPlayer.getPoints() + currentPlayer.getBet());
                else
                    currentPlayer.setPoints(currentPlayer.getPoints() - currentPlayer.getBet());
                break;
            case "COUNTDOWN":
                if (answered)
                    currentPlayer.setPoints(currentPlayer.getPoints() + (int)(currentRoundParameter * 0.2));
                break;
            case "FASTEST FINGER":
                totalAnswered++;
                if (answered) {
                    totalCorrect++;
                    if (totalPlayers == 2)
                        currentPlayer.setPoints(currentPlayer.getPoints() + 1000 - 500 * (totalCorrect - 1));
                    else
                        currentPlayer.setPoints(currentPlayer.getPoints() + 1000 - 250 * (totalCorrect - 1));
                }
                if (totalAnswered == totalPlayers) {
                    totalAnswered = 0;
                    totalCorrect = 0;
                }
                break;
            case "THERMOMETER":
                if (answered)
                    currentPlayer.setThermometerCorrectAnswers(currentPlayer.getThermometerCorrectAnswers() + 1);
                if (currentPlayer.getThermometerCorrectAnswers() == 5)
                    currentPlayer.setPoints(currentPlayer.getPoints() + 5000);
                break;
            case "VOTING":
                if (answered)
                    currentPlayer.setPoints(currentPlayer.getPoints() + 1000);
                else
                    currentPlayer.setPoints(currentPlayer.getPoints() - 500);
                break;
        }
    }
}