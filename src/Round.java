import java.util.*;

/**
 * This class represents a round in the game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Round {

    protected ArrayList<String> roundTypes; //List of Strings that holds the type of each round type.
    final String rightAnswerDescription=("Correct answers grant 1000 points, incorrect answers grant 0.");
    final String bettingDescription=("Bet points for each question. Correct answers grant points equal to the ones you bet. Incorrect answers subtract the same amount.");
    final String countdownDescription=("You have 5 seconds for each question. Correct answers grant points equal to the time remaining(in milliseconds)x0.2");
    final String fasterFingerDescription=("First player to answer correctly gains 1000 points. The second one gains 500.");
    final String thermometerDescription=("First player to answer 5 questions correctly gains 5000 points. Then, or if there are no more questions left, the game finishes.");

    /**
     * Constructor
     * Creates a list that holds the names of each round type.
     */

    public Round() {
        roundTypes = new ArrayList<>(); //If a new round type that can be played single and multiplayer is to be added, add its name in Capital letters.
        refillRoundTypes();
    }

    /**
     * Gets the name of the current round type.
     * @return The name of the current round type.
     */

    public ArrayList<String> getRoundTypes() {
        return roundTypes;
    }

    public String getRightAnswerDescription(){
        return rightAnswerDescription;
    }

    public String getBettingDescription(){
        return bettingDescription;
    }

    public String getCountdownDescription(){
        return countdownDescription;
    }

    public String getFasterFingerDescription(){
        return fasterFingerDescription;
    }

    public String getThermometerDescription(){
        return thermometerDescription;
    }

    public void addMultiplayerRounds(){ //If a new Multiplayer-only round is to be added, add its name in Capital letters.
        roundTypes.add("FASTEST FINGER");
        Collections.shuffle(roundTypes);
        roundTypes.add("THERMOMETER");
    }

    public void refillRoundTypes(){
        roundTypes.add("RIGHT ANSWER");
        roundTypes.add("BETTING");
        roundTypes.add("COUNTDOWN");
        Collections.shuffle(roundTypes);
    }

    public void calculatePoints(Boolean answered, String currentRound, Player currentPlayer, int currentRoundParameter,int totalPlayers){
        switch (currentRound){
            case "RIGHT ANSWER":
                RightAnswer r=new RightAnswer();
                r.rightAnswerPoints(currentPlayer, answered);
                break;
            case "BETTING":
                Betting b=new Betting();
                b.bettingPoints(currentPlayer,answered);
                break;
            case "COUNTDOWN":
                Countdown c=new Countdown();
                c.countdownPoints(currentPlayer,answered,currentRoundParameter);
                break;
            case "FASTEST FINGER":
                FastestFinger.fastestFingerPoints(currentPlayer,answered,totalPlayers);
                break;
            case "THERMOMETER":
                Thermometer.thermometerPoints(currentPlayer,answered);
                break;
        }
    }
}