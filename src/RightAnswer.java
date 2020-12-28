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
     */

    public void rightAnswerPoints(Player currentPlayer, boolean answered) {
        if (answered)
            currentPlayer.addPoints(1000);
    }
}
