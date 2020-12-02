import java.util.ArrayList;

/**
 * This class extends the Round Class and implements the Right Answer round of the Game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class RightAnswer extends Round {

    /**
     * Represents the Right Answer portion of the game.
     * If the player answers correctly he gains 1000 points. Otherwise, he gains 0 points.
     * @param availableQuestions List containing all the available questions.
     * @param chosenCategory The category chosen by one of the players.
     * @param players List containing every player.
     * @param currentRoundNumber Integer that holds the number of the current round type.
     * (For example: currentRoundNumber=1 if the game is on the first round type and 2 if the game has advanced to the next round type)
     */

    public void rightAnswerPoints(ArrayList<Questions> availableQuestions, String chosenCategory, ArrayList<Player> players, int currentRoundNumber){
        for (int i=0; i<5; i++) {
            //for (Player p : players) {  //Will be added in version 2
            if (availableQuestions.size()==0)
                break;
            if (randomQuestion(availableQuestions, chosenCategory, players))
                 players.get(0).addPoints(1000);
            else
                players.get(0).addPoints(0);
            if ((currentRoundNumber + 1) != roundTypes.size() || i != 4) {
                System.out.println("\nCURRENT POINTS");
                for (Player p : players)
                    System.out.println("    "+p.getUsername() + ": " + p.getPoints() + "\n");
            }
        }
    }
}