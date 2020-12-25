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
     *
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
                    tempQuestion.setHasMedia(scan.nextLine());
                    categories.add(tempQuestion.getCategory());
                    Collections.shuffle(tempQuestion.getAnswers()); //Shuffles the list that holds the answers of each question, so that they appear at a different order in every game.
                    availableQuestions.add(tempQuestion);
                    //In version 2 add image
                }
                Collections.shuffle(availableQuestions); //Shuffles the list that holds all the questions, so that they appear at a different order in every game.
            }
        }
    }

    public void createPlayer() {
        Player tempPlayer = new Player();
        players.add(tempPlayer);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<String> getRoundTypes() {
        return round.getRounds();
    }

    /**
     * Starts the game.
     */

    public Questions getRandomQuestion(String chosenCategory) {
        for (Questions q : availableQuestions) {
            if (q.getCategory().equals(chosenCategory)) {
                availableQuestions.remove(q);
                return q;
            }
        }
        return null;
    }

    public int enterUsernames(String chosenUsername, int currentPlayer) {
        if (chosenUsername.length() != 0 && chosenUsername.length() < 15 && !chosenUsername.trim().isEmpty())
            for (Player p : players) {
                if (chosenUsername.equals(p.getUsername()))
                    return -1;
            }
        else
            return 0;
        createPlayer();
        getPlayers().get(currentPlayer).setUsername(chosenUsername);
        return 1;
    }

    public int setControls(String currentControl, int currentPlayer, int currentControlNumber) {
        // Loop that checks if the chosen control is already bound, whereupon it asks for a new control and restarts.
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (currentControl.equals(String.valueOf(players.get(i).getControl(j)))) {
                    return -1;
                } else if (currentControl.length() != 1 || currentControl.trim().isEmpty()) {
                    return 0;
                }
            }
        }
        getPlayers().get(currentPlayer).setPlayerControls(currentControlNumber, currentControl);
        return 1;
    }

    public void clearPlayers() {
        players.clear();
    }

    public String[] randomCategories() {
        ArrayList<String> randomCategories = new ArrayList<>();
        boolean flag = false;
        for (String s : categories) {
            for (Questions q : availableQuestions) {
                if (q.getCategory().equals(s) && !randomCategories.contains(s)) {
                    randomCategories.add(s);
                    flag = true;
                }
            }
            if (!flag)
                categories.remove(s);
        }
        Collections.shuffle(randomCategories);
        String[] random4 = new String[4];
        if (randomCategories.size() > 3) {
            for (int i = 0; i < 4; i++)
                random4[i] = randomCategories.get(i);
        } else {
            for (int i = 0; i < randomCategories.size(); i++)
                random4[i] = randomCategories.get(i);
            for (int i = randomCategories.size() - 1; i < 4; i++)
                random4[i] = "";
        }
        return random4;
    }

    public int correctAnswer(char answer, Questions question, String currentRound) {
        for (Player p : getPlayers()) {
            for (int i = 0; i < 4; i++)
                if (Character.toLowerCase(answer) == Character.toLowerCase(p.getControl(i).charAt(0)) && !p.getHasAnswered()) {
                    p.setHasAnswered(true);
                    if (question.getAnswers().get(i).equals(question.correctAnswer))
                        round.calculatePoints(true, currentRound, p);
                    else
                        round.calculatePoints(false, currentRound, p);
                    return 1;
                }
        }
        return 0;
    }

    public int hasBet(char answer) {
        for (Player p : getPlayers()) {
            for (int i = 0; i < 4; i++)
                if (Character.toLowerCase(answer) == Character.toLowerCase(p.getControl(i).charAt(0)) && !p.getHasAnswered()) {
                    p.setHasAnswered(true);
                    p.setBet((i+1) * 250);
                    return 1;
                }
        }
        return 0;
    }

    public void haveAnswered(int playersAnswered) {
        if (playersAnswered == getPlayers().size()) {
            for (Player p : getPlayers())
                p.setHasAnswered(false);
        }
    }
}


