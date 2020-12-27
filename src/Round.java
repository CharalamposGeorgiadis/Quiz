import java.util.*;

/**
 * This class represents a round in the game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Round {

    protected ArrayList<String> roundTypes; //List of Strings that holds the type of each round type.
    final String rightAnswerDescription=("Correct answers grant 1000 points,\n       incorrect answers grant 0.");
    final String bettingDescription=("      Bet points for each question.\n      Correct answers grant points\n      equal to the ones you bet.\n      Incorrect answers subtract\n      the same amount.");
    final String countdownDescription=("You have 5 seconds for each question.\nCorrect answers grant points equal to\nthe time remaining(in milliseconds)x0.2");
    final String fasterFingerDescription=("");
    final String thermometerDescription=("");

    /**
     * Constructor
     * Creates a list that holds the names of each round type.
     */

    public Round() {
        roundTypes = new ArrayList<>(); //If a new round type that can be played single and multiplayer is to be added, add its name in Capital letters.
        roundTypes.add("RIGHT ANSWER");
        roundTypes.add("BETTING");
        roundTypes.add("COUNTDOWN");
        //Collections.shuffle(roundTypes);
    }


    /**
     * Gets the name of the current round type.
     * @return The name of the current round type.
     */

    public ArrayList<String> getRounds() {
        return roundTypes;
    }

    public void addMultiplayerRounds(){ //If a new Multiplayer-only round is to be added, add its name in Capital letters.
        roundTypes.add("FASTEST FINGER");
        roundTypes.add("THERMOMETER");
       // Collections.shuffle(roundTypes);
        }

    public void calculatePoints(Boolean answered, String currentRound, Player currentPlayer,int currentRoundParameter){
        switch (currentRound){
            case "RIGHT ANSWER":
                RightAnswer r = new RightAnswer();
                r.rightAnswerPoints(currentPlayer, answered);
                break;
            case "BETTING":
                Betting b =new Betting();
                b.bettingPoints(currentPlayer,answered);
                break;
            case "COUNTDOWN":
                Countdown c =new Countdown();
                c.countdownPoints(currentPlayer,answered,currentRoundParameter);
                break;
            case "FASTEST FINGER":
                FastestFinger f= new FastestFinger();
                break;
            case "THERMOMETER":
                Thermometer t= new Thermometer();
                break;
            }
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
}
