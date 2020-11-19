import java.util.ArrayList;

public class RightAnswer extends Round {




    public void rightAnswerPoints(ArrayList<Questions> availableQuestions, String chosenCategory, ArrayList<Player> playerCount){
        for (int i=0; i<5; i++) {
            this.addPoints(playerCount, this.randomQuestion(availableQuestions, chosenCategory));
            System.out.println("Player's current points: "+playerCount.get(0).getPoints()+"\n");
        }

    }



}