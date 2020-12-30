


public class FastestFinger extends Round{

    static Player firstPlayer;
    static Player secondPlayer;
    static int totalAnswered;

    public FastestFinger(){
        firstPlayer=new Player();
        secondPlayer=new Player();
    }

    public static void fastestFingerPoints(Player currentPlayer, boolean answered,int totalPlayers) {
        if (answered)
            if (firstPlayer==null) {
                firstPlayer = currentPlayer;
                currentPlayer.addPoints(1000);
            }
            else if (secondPlayer==null) {
                secondPlayer = currentPlayer;
                currentPlayer.addPoints(500);
            }
        totalAnswered++;
        if (totalPlayers==totalAnswered)
            resetPlayers();
    }

    public static void resetPlayers(){
        totalAnswered=0;
        firstPlayer=null;
        secondPlayer=null;
    }
}