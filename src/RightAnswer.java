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
     */

    public void rightAnswerPoints(ArrayList<Questions> availableQuestions, String chosenCategory, ArrayList<Player> players){
        for (int i=0; i<5; i++) {
            //for (Player p : players) {  //Will be added in version 2
            if (availableQuestions.size()==0)
                break;
            if (randomQuestion(availableQuestions, chosenCategory, players))
                 players.get(0).addPoints(1000);
            else
                players.get(0).addPoints(0);
            System.out.println("Player's current points: "+players.get(0).getPoints()+"\n");
        }
    }
}
