import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class represents the whole game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Questions> availableQuestions;
    private Menu currentMenu;
    private HashSet<String> categories;
    private Round currentRound;

    /**
     * Constructor.
     * @param questions Holds the directory of the questions folder.
     * @throws FileNotFoundException if a file is not found.
     */

    public Game(File[] questions) throws FileNotFoundException {
        availableQuestions = new ArrayList<>();
        categories = new HashSet<>(); //Holds the name of each category once.
        currentMenu = new Menu();
        currentRound = new Round();
        players = new ArrayList<>();
        int numberOfPlayers = currentMenu.chooseNumberOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++) {
            Player tempPlayer = new Player(currentMenu.chooseUsername(i));
            players.add(tempPlayer);
            currentMenu.setControls(i, tempPlayer, players);
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
                    Collections.shuffle(tempQuestion.getAnswers()); //Shuffles the list that holds the answers of each question, so that they appear at a different order in every game.
                    availableQuestions.add(tempQuestion);
                    //In version 2 add image
                }
                Collections.shuffle(availableQuestions); //Shuffles the list that holds all the questions, so that they appear at a different order in every game.
            }
        }
    }

    /**
     * Starts the game.
     */

    public void startGame() {
        for (int i = 0; i < currentRound.getRounds().size(); i++) {
            System.out.println("\n" +currentRound.getRounds().get(i) + "\n");
            String chosenCategory = currentMenu.chooseCategory(categories, players);
            System.out.println(chosenCategory.toUpperCase()+"\n");
            currentRound.startRound(i + 1, availableQuestions, chosenCategory, players,currentMenu);
            if (i < currentRound.getRounds().size()-1) {
                System.out.println("MOVING TO THE NEXT ROUND");
            }
        }
            System.out.println("GAME FINISHED\n" + "TOTAL POINTS: " + players.get(0).getPoints()+"\n");
            System.out.println("Press any key to return to Main Menu") ; //In GUI, Press Enter
            Scanner console = new Scanner(System.in);
            console.nextLine();
    }
}


