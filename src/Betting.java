import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class extends the Round Class and implements the Betting round of the Game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Betting extends Round{

    /**
     * Represents the Betting portion of the game.
     * If the player answers correctly he gains points equal to the ones he bet.
     * Otherwise, they are deducted from his score.
     * @param availableQuestions List containing all the available questions.
     * @param chosenCategory The category chosen by one of the players.
     * @param players List containing every player.
     * @param menu Gives access to menu options.
     * @param currentRoundNumber Integer that holds the number of the current round type.
     * (For example: currentRoundNumber=1 if the game is on the first round type and 2 if the game has advanced to the next round type)
     * @param categories Set of Strings that contains the name of each category.
     */

    public void bettingPoints(ArrayList<Questions> availableQuestions, String chosenCategory, ArrayList<Player> players, Menu menu, int currentRoundNumber, HashSet<String> categories) {
        for (int i = 0; i < 5; i++) {
            boolean flag = true;
            //for (Player p : players) {  //Will be added in version 2
            if (availableQuestions.size() == 0)
                break;
            int bet = menu.betPoints(players.get(0));
            if (randomQuestion(availableQuestions, chosenCategory, players))
                players.get(0).addPoints(bet);
            else
                players.get(0).addPoints(-bet);
            if ((currentRoundNumber + 1) != roundTypes.size() || i != 4) {
                System.out.println("\nCURRENT POINTS");
                for (Player p : players)
                    System.out.println("    " + p.getUsername() + ": " + p.getPoints() + "\n");
            }
            for (Questions q : availableQuestions) {
                if (q.getCategory().equals(chosenCategory)) {
                    flag = false;
                    break;
                }
            }
            if (flag){
                System.out.println("No more questions left in category: "+chosenCategory);
                categories.remove(chosenCategory);
            }
        }
    }
}
