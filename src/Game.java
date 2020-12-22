import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
     * @throws FileNotFoundException if a file is not found.
     */

    public Game(File[] questions) throws IOException {
        availableQuestions = new ArrayList<>();
        categories = new HashSet<>();
        round = new Round();
        players = new ArrayList<>();
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

    public void createPlayer(){
        Player tempPlayer=new Player();
        players.add(tempPlayer);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Starts the game.
     */

    public void startGame(Menu menu) throws IOException {
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
        players.get(0).addStats(players);
        System.out.println("\nPress any key to return to Main Menu");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

    public int enterUsernames(String chosenUsername, int currentPlayer){
        if (chosenUsername.length()!=0 && chosenUsername.length()<15 && !chosenUsername.trim().isEmpty())
            for (Player p:players){
                if (chosenUsername.equals(p.getUsername()))
                    return -1;
            }
        else
            return 0;
        createPlayer();
        getPlayers().get(currentPlayer).setUsername(chosenUsername);
        return 1;
    }

    public int setControls(String currentControl, int currentPlayer, int currentControlNumber){
        // Loop that checks if the chosen control is already bound, whereupon it asks for a new control and restarts.
        for (int i=0; i<players.size(); i++) {
            for (int j=0; j<4; j++) {
                if (currentControl.equals(String.valueOf(players.get(i).getControl(j)))) {
                    return -1;
                }
                else if (currentControl.length()!= 1 || currentControl.trim().isEmpty()) {
                    return 0;
                }
            }
        }
        getPlayers().get(currentPlayer).setPlayerControls(currentControlNumber,currentControl);
        return 1;
    }

    public void clearPlayers(){
        players.clear();
    }

    public String[] randomCategories(){
        ArrayList<String> randomCategories=new ArrayList<>();
        for (String s:categories){
            randomCategories.add(s);
        }
        Collections.shuffle(randomCategories);
        String [] random4 = new String[4];
        for (int i=0; i<4; i++)
            random4[i]=randomCategories.get(i);
        return random4;
    }
}
