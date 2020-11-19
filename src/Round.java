import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Round {

    private ArrayList<String> rounds;


    public Round() {
        rounds = new ArrayList<>();
        rounds.add("RIGHT ANSWER");
        rounds.add("BETTING");
    }

    public ArrayList<String> getRounds() {
        return rounds;
    }


    public void addPoints(ArrayList<Player> player, int points) {
        int tempPoints = player.get(0).getPoints() + points;
        player.get(0).setPoints(tempPoints);
    }


    public int randomQuestion(ArrayList<Questions> availableQuestions,String chosenCategory) {
        while (true) {
            Questions randomQuestion = availableQuestions.get(new Random().nextInt(availableQuestions.size()));
            ArrayList<String> tempAnswers;
            if (chosenCategory.equals(randomQuestion.getCategory())){
                System.out.println(randomQuestion.getQuestion());
                tempAnswers=randomQuestion.getAnswers();
                System.out.println("A. "+ tempAnswers.get(0));
                System.out.println("B. "+ tempAnswers.get(1));
                System.out.println("C. "+ tempAnswers.get(2));
                System.out.println("D. "+ tempAnswers.get(3));
                Scanner console = new Scanner(System.in);
                while (true){
                    int chosenAnswer=console.nextInt();
                    if(chosenAnswer>0 && chosenAnswer<5){
                        if (tempAnswers.get(chosenAnswer-1).equals(randomQuestion.correctAnswer)){
                            System.out.println("The correct answer was: "+randomQuestion.getCorrectAnswer());
                            availableQuestions.remove(randomQuestion);
                            return 1000;
                        }
                        else {
                            System.out.println("The correct answer was: "+randomQuestion.getCorrectAnswer());
                            availableQuestions.remove(randomQuestion);
                            return 0;
                        }
                    }
                    else
                        System.out.println("Please choose a suitable answer!");
                }
            }
        }
    }




    public void startRound(int numberOfRound,ArrayList<Questions> availableQuestions,String chosenCategory,ArrayList<Player> playerCount ) {
        if (numberOfRound==1){
            RightAnswer r=new RightAnswer();
            r.rightAnswerPoints(availableQuestions,chosenCategory,playerCount);
            //this.randomQuestion(availableQuestions,chosenCategory);

        }
        if (numberOfRound==2){

        }

    }
}