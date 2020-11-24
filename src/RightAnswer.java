import java.util.ArrayList;

/**
 * This class extends the Round Class and implements the Right Answer round of the Game.
 * If the player answers correctly he gains 1000 points. Otherwise, he gains 0 points.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class RightAnswer extends Round {


    public void rightAnswerPoints(ArrayList<Questions> availableQuestions, String chosenCategory, ArrayList<Player> playerCount){
        for (int i=0; i<5; i++) {
            if (randomQuestion(availableQuestions, chosenCategory, playerCount))
                 playerCount.get(0).addPoints(1000);
            else
                playerCount.get(0).addPoints(0);
            System.out.println("Player's current points: "+playerCount.get(0).getPoints()+"\n");
        }
    }
}
