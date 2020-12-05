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
    private ArrayList<Player> players; // List that holds the information of every player.
    private ArrayList<Questions> availableQuestions; // List that holds every available question.
    private HashSet<String> categories; // Set of Strings that holds the name of each category once.
    private Round round; // Grants access to Round methods.

    /**
     * Constructor.
     * @param questions Holds the directory of the questions folder.
     * @param menu Gives access to menu options.
     * @throws FileNotFoundException if a file is not found.
     */

    public Game(File[] questions,Menu menu) throws FileNotFoundException {
        availableQuestions = new ArrayList<>();
        categories = new HashSet<>();
        round = new Round();
        players = new ArrayList<>();
    /*
        //Will be added in version 2
        int numberOfPlayers = currentMenu.chooseNumberOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++) {
            Player tempPlayer = new Player(currentMenu.chooseUsername(i));
            players.add(tempPlayer);
            currentMenu.setControls(i, tempPlayer, players);
        }*/
        Player tempPlayer = new Player(menu.chooseUsername(0)); //Creates a new player.
        players.add(tempPlayer);
        menu.setControls(0, tempPlayer, players);
        for (File question : questions) {
            if (question.isFile()) {
                Scanner scan = new Scanner(question);
                Questions tempQuestion;
                tempQuestion = new Questions();
                while (scan.hasNextLine()) { //Scans each line of the txt files in the Directory and stores them in a appropriate variable.
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
     * @param menu Gives access to menu options.
     */

    public void startGame(Menu menu) {
        for (int i = 0; i < round.getRounds().size(); i++) {
            System.out.println("\n" + round.getRounds().get(i) + "\n");
            String chosenCategory = menu.chooseCategory(categories, players);
            System.out.println(chosenCategory.toUpperCase()+"\n");
            round.startRound(round.getRounds().get(i), availableQuestions, chosenCategory, players,menu, i, categories);
            if (availableQuestions.size()==0) {
                System.out.println("THERE ARE NO MORE QUESTIONS LEFT");
                break;
            }
            if (i < round.getRounds().size()-1) {
                System.out.println("MOVING TO THE NEXT ROUND");
            }
        }
        System.out.println("\nGAME FINISHED");
        System.out.println("    "+players.get(0).getUsername() + ": " + players.get(0).getPoints()); //In version 2, sort players by points and print them all with a loop.
        System.out.println("\nPress any key to return to Main Menu");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }
}
