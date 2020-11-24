import java.util.ArrayList;


/**
 * This class extends the Round Class and implements the Betting round of the Game.
 * If the player answers correctly he gains points equal to the ones he bet. Otherwise, they are deducted from his score.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */



public class Betting extends Round{


    public void bettingPoints(ArrayList<Questions> availableQuestions, String chosenCategory, ArrayList<Player> playerCount, Menu menu){
        for (int i=0; i<5; i++) {
            int bet=menu.betPoints();
            if (randomQuestion(availableQuestions, chosenCategory, playerCount))
                playerCount.get(0).addPoints(bet);
            else
                playerCount.get(0).addPoints(-bet);
            System.out.println("Player's current points: "+playerCount.get(0).getPoints()+"\n");
        }
    }
}
