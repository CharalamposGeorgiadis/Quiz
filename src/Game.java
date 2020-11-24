import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Game {
    private ArrayList<Player> playerCount;
    private ArrayList<Questions> availableQuestions;
    private Menu currentMenu;
    private Set<String> categories;
    private Round currentRound;

    public Game(File[] questions) throws FileNotFoundException {
        availableQuestions = new ArrayList<>();
        categories = new HashSet<>();
        currentMenu = new Menu();
        currentRound = new Round();
        playerCount = new ArrayList<>();
        int numberOfPlayers=currentMenu.chooseNumberOfPlayers();
        for (int i=0; i<numberOfPlayers; i++) {
            Player tempPlayer = new Player(currentMenu.chooseUsername(i));
            playerCount.add(tempPlayer);
            currentMenu.setControls(i,tempPlayer, playerCount);
        }
        for (File question : questions) {
            if (question.isFile()) {
                Scanner scan = new Scanner(question);
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
            System.out.println("\n" +currentRound.getRounds().get(i) + "\n");
            String chosenCategory = currentMenu.chooseCategory(categories, playerCount);
            System.out.println(chosenCategory.toUpperCase()+"\n");
            currentRound.startRound(i + 1, availableQuestions, chosenCategory, playerCount,currentMenu);
            if (i < currentRound.getRounds().size()-1) {
                System.out.println("MOVING TO THE NEXT ROUND");
            }
        }
            System.out.println("GAME FINISHED\n" + "TOTAL POINTS: " + playerCount.get(0).getPoints()+"\n");
            System.out.println("Press any key to return to Main Menu") ; //In GUI, Press Enter
        // How to change anyKey to not require Enter confirmation
            Scanner console = new Scanner(System.in);
            console.nextLine();
    }
}


