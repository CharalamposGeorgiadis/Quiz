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
            for (int i = 0; i < availableQuestions.size(); i++) {
                if (chosenCategory.equals(availableQuestions.get(i).getCategory())) {
                    System.out.println(availableQuestions.get(i).getQuestion());
                    System.out.println("A. " + availableQuestions.get(i).getAnswers().get(0));
                    System.out.println("B. " + availableQuestions.get(i).getAnswers().get(1));
                    System.out.println("C. " + availableQuestions.get(i).getAnswers().get(2));
                    System.out.println("D. " + availableQuestions.get(i).getAnswers().get(3));
                    Scanner console = new Scanner(System.in);
                    while (true) {
                        String chosenAnswer = console.nextLine();
                        if (chosenAnswer.equals(String.valueOf(playerCount.get(0).getControl(0)))) {
                            if (availableQuestions.get(i).getAnswers().get(0).equals(availableQuestions.get(i).correctAnswer)){
                                availableQuestions.remove(availableQuestions.get(i));
                                return true;
                          }
                            else{
                                availableQuestions.remove(availableQuestions.get(i));
                                return false;
                            }
                        } else if (chosenAnswer.equals(String.valueOf(playerCount.get(0).getControl(1)))) {
                            if (availableQuestions.get(i).getAnswers().get(1).equals(availableQuestions.get(i).correctAnswer)) {
                                availableQuestions.remove(availableQuestions.get(i));
                                return true;
                            }
                            else{
                                availableQuestions.remove(availableQuestions.get(i));
                                return false;
                            }
                        } else if (chosenAnswer.equals(String.valueOf(playerCount.get(0).getControl(2)))) {
                            if (availableQuestions.get(i).getAnswers().get(2).equals(availableQuestions.get(i).correctAnswer)) {
                                availableQuestions.remove(availableQuestions.get(i));
                                return true;
                            }
                            else{
                                availableQuestions.remove(availableQuestions.get(i));
                                return false;
                            }
                        } else if (chosenAnswer.equals(String.valueOf(playerCount.get(0).getControl(3)))) {
                            if (availableQuestions.get(i).getAnswers().get(3).equals(availableQuestions.get(i).correctAnswer)) {
                                availableQuestions.remove(availableQuestions.get(i));
                                return true;
                            }
                            else {
                                availableQuestions.remove(availableQuestions.get(i));
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