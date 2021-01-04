


public class Countdown{

    public void countdownPoints(Player currentPlayer, boolean answered, int milliseconds) {
        if (answered)
            currentPlayer.addPoints((int) (milliseconds * 0.2));
    }
}
