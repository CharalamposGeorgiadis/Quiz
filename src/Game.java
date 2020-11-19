import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.util.*;


public class Game {
    ArrayList<Player> playerCount;
    ArrayList<Questions> availableQuestions;
    Menu currentMenu;
    Set<String> categories;
    Round currentRound;


    public Game(File[] questions, String username) throws FileNotFoundException {
        availableQuestions = new ArrayList();
        categories = new HashSet<>();
        currentMenu = new Menu();
        currentRound = new Round();
        playerCount = new ArrayList();
        Player tempPlayer = new Player(username);
        playerCount.add(tempPlayer);
        Random rand = new Random();
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].isFile()) {
                Scanner scan = new Scanner(questions[i]);
                Questions tempQuestion;
                tempQuestion = new Questions();

                while (scan.hasNextLine()) {
                    tempQuestion.setCategory(scan.nextLine());
                    tempQuestion.setQuestion(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setCorrectAnswer(scan.nextLine());
                    categories.add(tempQuestion.getCategory());
                    Collections.shuffle(tempQuestion.getAnswers());
                    availableQuestions.add(tempQuestion);
                    //In version 2 add image
                }
                Collections.shuffle(availableQuestions);
            }
        }
    }

    public void startGame() {
        for (int i = 0; i < currentRound.getRounds().size(); i++) {
            System.out.println(currentRound.getRounds().get(i) + "\n");
            String chosenCategory = currentMenu.chooseCategory(categories);
            currentRound.startRound(i + 1, availableQuestions, chosenCategory, playerCount);
            if (i < currentRound.getRounds().size()-1) {
                System.out.println("MOVING TO THE NEXT ROUND\n");
            }
        }
            System.out.println("GAME FINISHED\n" + "TOTAL POINTS: " + playerCount.get(0).getPoints()+"\n");
            System.out.println("Press any key to return to Main Menu\n") ; //In GUI, Press Enter
        // How to change anyKey to not require Enter confirmation
            Scanner console = new Scanner(System.in);
            String anyKey=console.nextLine();

    }
}


