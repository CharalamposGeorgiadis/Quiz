import java.util.ArrayList;
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




    public boolean randomQuestion(ArrayList<Questions> availableQuestions,String chosenCategory, ArrayList<Player> playerCount) {
        while (true) {
            for (Questions q: availableQuestions){
                if (chosenCategory.equals(q.getCategory())) {
                    System.out.println(q.getQuestion());
                    System.out.println("A. " + q.getAnswers().get(0));
                    System.out.println("B. " + q.getAnswers().get(1));
                    System.out.println("C. " + q.getAnswers().get(2));
                    System.out.println("D. " + q.getAnswers().get(3));
                    Scanner console = new Scanner(System.in);
                    while (true) {
                        String chosenAnswer = console.nextLine();
                        if (chosenAnswer.equals(String.valueOf(playerCount.get(0).getControl(0)))) {
                            if (q.getAnswers().get(0).equals(q.correctAnswer)){
                                availableQuestions.remove(q);
                                return true;
                          }
                            else{
                                availableQuestions.remove(q);
                                return false;
                            }
                        } else if (chosenAnswer.equals(String.valueOf(playerCount.get(0).getControl(1)))) {
                            if (q.getAnswers().get(1).equals(q.correctAnswer)) {
                                availableQuestions.remove(q);
                                return true;
                            }
                            else{
                                availableQuestions.remove(q);
                                return false;
                            }
                        } else if (chosenAnswer.equals(String.valueOf(playerCount.get(0).getControl(2)))) {
                            if (q.getAnswers().get(2).equals(q.correctAnswer)) {
                                availableQuestions.remove(q);
                                return true;
                            }
                            else{
                                availableQuestions.remove(q);
                                return false;
                            }
                        } else if (chosenAnswer.equals(String.valueOf(playerCount.get(0).getControl(3)))) {
                            if (q.getAnswers().get(3).equals(q.correctAnswer)) {
                                availableQuestions.remove(q);
                                return true;
                            }
                            else {
                                availableQuestions.remove(q);
                                return false;
                            }
                        }
                        else
                            System.out.println("Please choose a suitable answer!");
                    }
                }
            }
        }
    }

        public void startRound ( int numberOfRound, ArrayList<Questions > availableQuestions, String
        chosenCategory, ArrayList < Player > playerCount, Menu menu ){
            if (numberOfRound == 1) {
                RightAnswer r = new RightAnswer();
                r.rightAnswerPoints(availableQuestions, chosenCategory, playerCount);

            }
            if (numberOfRound == 2) {
                Betting b =new Betting();
                b.bettingPoints(availableQuestions, chosenCategory, playerCount, menu);
            }
         }
    }