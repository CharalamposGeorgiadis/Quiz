import java.util.ArrayList;

public class RightAnswer extends Round {




    public void rightAnswerPoints(ArrayList<Questions> availableQuestions, String chosenCategory, ArrayList<Player> playerCount){
        for (int i=0; i<5; i++) {
            if (this.randomQuestion(availableQuestions, chosenCategory))
                 this.addPoints(playerCount, 1000);
            else
                this.addPoints(playerCount, 1000);
            System.out.println("Player's current points: "+playerCount.get(0).getPoints()+"\n");
        }

    }



}