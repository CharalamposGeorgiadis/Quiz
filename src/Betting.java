import java.util.ArrayList;


/**
 * This class extends the Round Class and implements the Betting round of the Game.
 * If the player answers correctly he gains points equal to the ones he bet. Otherwise, they are deducted from his score.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */



public class Betting extends Round{


    public void bettingPoints(ArrayList<Questions> availableQuestions, String chosenCategory, ArrayList<Player> players, Menu menu) {
        for (int i = 0; i < 5; i++) {
            for (Player p : players) {
                int bet = menu.betPoints(p);
                if (randomQuestion(availableQuestions, chosenCategory, players))
                    p.addPoints(bet);
                else
                    p.addPoints(-bet);
                System.out.println("Player's current points: " + p.getPoints() + "\n");
            }
        }
    }
}
