import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a round in the game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Round {

    private ArrayList<String> rounds;

    /**
     * Constructor
     * Creates a list that holds the names of each round type.
     */

    public Round() {
        rounds = new ArrayList<>();
        rounds.add("RIGHT ANSWER");
        rounds.add("BETTING");
    }

    /**
     * Gets the name of the current round type.
     * @return The name of the current round type.
     */

    public ArrayList<String> getRounds() {
        return rounds;
    }

    /**
     * Asks the player(s) a random question based on the chosen category. After each player has answered it removes that question from the list of available questions.
     * @param availableQuestions A list holding all the available questions.
     * @param chosenCategory A String that holds the chosen category for this round.
     * @param players A list holding the information of each player.
     * @return If the player has answered correctly it returns true, otherwise it returns false.
     */


    public boolean randomQuestion(ArrayList<Questions> availableQuestions,String chosenCategory, ArrayList<Player> players) {
      //  while (true) {

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
                        if (chosenAnswer.equals(String.valueOf(players.get(0).getControl(0)))) {
                            if (q.getAnswers().get(0).equals(q.correctAnswer)){
                                System.out.println("THE CORRECT ANSWER WAS: "+ q.correctAnswer);
                                availableQuestions.remove(q);
                                return true;
                          }
                            else{
                                System.out.println("THE CORRECT ANSWER WAS: "+ q.correctAnswer);
                                availableQuestions.remove(q);
                                return false;
                            }
                        } else if (chosenAnswer.equals(String.valueOf(players.get(0).getControl(1)))) {
                            if (q.getAnswers().get(1).equals(q.correctAnswer)) {
                                System.out.println("THE CORRECT ANSWER WAS: "+ q.correctAnswer);
                                availableQuestions.remove(q);
                                return true;
                            }
                            else{
                                System.out.println("THE CORRECT ANSWER WAS: "+ q.correctAnswer);
                                availableQuestions.remove(q);
                                return false;
                            }
                        } else if (chosenAnswer.equals(String.valueOf(players.get(0).getControl(2)))) {
                            if (q.getAnswers().get(2).equals(q.correctAnswer)) {
                                System.out.println("THE CORRECT ANSWER WAS: "+ q.correctAnswer);
                                availableQuestions.remove(q);
                                return true;
                            }
                            else{
                                System.out.println("THE CORRECT ANSWER WAS: "+ q.correctAnswer);
                                availableQuestions.remove(q);
                                return false;
                            }
                        } else if (chosenAnswer.equals(String.valueOf(players.get(0).getControl(3)))) {
                            if (q.getAnswers().get(3).equals(q.correctAnswer)) {
                                System.out.println("THE CORRECT ANSWER WAS: "+ q.correctAnswer);
                                availableQuestions.remove(q);
                                return true;
                            }
                            else {
                                System.out.println("THE CORRECT ANSWER WAS: "+ q.correctAnswer);
                                availableQuestions.remove(q);
                                return false;
                            }
                        }
                        else
                            System.out.println("Please choose a suitable answer!");
                    }
                }
            }
        //}
        return true;
    }

    /**
     * Initiates the start of each round type.
     * @param numberOfRound The number of the current round type.
     * @param availableQuestions A list holding all the available questions.
     * @param chosenCategory A String that holds the chosen category for this round.
     * @param players A list holding the information of each player.
     * @param menu Gives access to menu options.
     */
        public void startRound ( int numberOfRound, ArrayList<Questions > availableQuestions, String
        chosenCategory, ArrayList <Player> players, Menu menu ){
            if (numberOfRound == 1) {
                RightAnswer r = new RightAnswer();
                r.rightAnswerPoints(availableQuestions, chosenCategory, players);

            }
            if (numberOfRound == 2) {
                Betting b =new Betting();
                b.bettingPoints(availableQuestions, chosenCategory, players, menu);
            }
         }
    }