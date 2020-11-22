import java.util.ArrayList;

public class Betting extends Round{


    public void bettingPoints(ArrayList<Questions> availableQuestions, String chosenCategory, ArrayList<Player> playerCount, Menu menu){
        for (int i=0; i<5; i++) {
            int bet=menu.betPoints();
            if (this.randomQuestion(availableQuestions, chosenCategory))
                    this.addPoints(playerCount, bet);
            else
                this.addPoints(playerCount, -bet);
            System.out.println("Player's current points: "+playerCount.get(0).getPoints()+"\n");
        }

    }

}
