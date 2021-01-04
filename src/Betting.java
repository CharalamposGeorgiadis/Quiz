/**
 * This class extends the Round Class and implements the Betting round of the Game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Betting{

    /**
     * Represents the Betting portion of the game.
     * If the player answers correctly he gains points equal to the ones he bet.
     * Otherwise, they are deducted from his score.
     * (For example: currentRoundNumber=1 if the game is on the first round type and 2 if the game has advanced to the next round type)
     */

    public void bettingPoints(Player currentPlayer, boolean answered) {
        if (answered)
            currentPlayer.addPoints(currentPlayer.getBet());
        else
            currentPlayer.addPoints(-currentPlayer.getBet());
    }
}