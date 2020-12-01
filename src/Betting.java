import java.util.ArrayList;

/**
 * This class extends the Round Class and implements the Betting round of the Game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Betting extends Round{

    /**
     * Represents the Betting portion of the game.
     * If the player answers correctly he gains points equal to the ones he bet. Otherwise, they are deducted from his score.
     * @param availableQuestions List containing all the available questions.
     * @param chosenCategory The category chosen by one of the players.
     * @param players List containing every player.
     * @param menu Gives access to menu options.
     */

    public void bettingPoints(ArrayList<Questions> availableQuestions, String chosenCategory, ArrayList<Player> players, Menu menu) {
        for (int i = 0; i < 5; i++) {
            //for (Player p : players) {  //Will be added in version 2
            if (availableQuestions.size()==0)
                break;
            int bet = menu.betPoints(players.get(0));
            if (randomQuestion(availableQuestions, chosenCategory, players))
                players.get(0).addPoints(bet);
            else
                players.get(0).addPoints(-bet);
            System.out.println("Player's current points: " + players.get(0).getPoints() + "\n");
        }
    }
}
