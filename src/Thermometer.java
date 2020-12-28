

public class Thermometer extends Round{

    public static void thermometerPoints(Player currentPlayer, boolean answered) {
        if (answered){
            currentPlayer.addThermometerCorrectAnswer();
        }
        if (currentPlayer.getThermometerCorrectAnswers()==5)
            currentPlayer.addPoints(5000);
    }
}
