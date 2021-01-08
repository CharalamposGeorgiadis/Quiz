import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private PlayerStats playerStats; // Grants access to Player Stats.

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
        playerStats=new PlayerStats();
        loadQuestions(questions);
    }

    /**
     * Loads questions from the directory folder.
     * @param questions Holds the directory of the questions folder.
     * @throws FileNotFoundException if a file is not found.
     */

    public void loadQuestions(File[] questions) throws FileNotFoundException {
        for (File question : questions) {
            if (question.isFile()) {
                Scanner scan = new Scanner(question);
                Questions tempQuestion;
                tempQuestion = new Questions();
                //Scans each line of the txt files in the Directory and stores them in a appropriate variable.
                while (scan.hasNextLine()) {
                    tempQuestion.setCategory(scan.nextLine());
                    tempQuestion.setQuestion(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setCorrectAnswer(scan.nextLine());
                    tempQuestion.setMedia(scan.nextLine());
                    categories.add(tempQuestion.getCategory());
                    //Shuffles the list that holds the answers of each question, so that they appear at a different order in every game.
                    Collections.shuffle(tempQuestion.getAnswers());
                    availableQuestions.add(tempQuestion);
                }
                //Shuffles the list that holds all the questions, so that they appear at a different order in every game.
                Collections.shuffle(availableQuestions);
            }
        }
    }

    /**
     * Gets the available questions.
     * @return ArrayList of Questions that holds the available questions.
     */

    public ArrayList<Questions> getAvailableQuestions() {return availableQuestions;}

    /**
     * Gets the available categories.
     * @return HashSet of Strings that holds the available categories.
     */

    public HashSet<String> getCategories(){return categories;}

    /**
     * Gets the players of the current game.
     * @return ArrayList of Player that holds the players of the current game.
     */

    public ArrayList<Player> getPlayers() {return players;}

    /**
     * Gets the round Object of the current game.
     * @return Round Object of the current game.
     */

    public Round getRound(){return round;}

    /**
     * Gets a PlayerStats Object.
     * @return PlayerStats Object.
     */

    public PlayerStats getPlayerStats(){return playerStats;}

    /**
     *
     */

    public void createPlayer() {
        players.add(new Player());
    }


    public void resetHaveAnswered() {
        for (Player p : getPlayers())
            p.setHasAnswered(false);
    }

    public Questions getRandomQuestion(String chosenCategory) {
        if(chosenCategory!=null)
            for (Questions q : availableQuestions) {
                if (q.getCategory().equals(chosenCategory)) {
                    availableQuestions.remove(q);
                    return q;
                }
            }
        else {
            Questions q=getAvailableQuestions().get(0);
            availableQuestions.remove(q);
            return q;
        }
        return null;
    }

    public int enterUsernames(String chosenUsername, int currentPlayer) {
        if (chosenUsername.length() != 0 && chosenUsername.length() < 15 && !chosenUsername.trim().isEmpty())
            for (Player p : players) {
                if (chosenUsername.toUpperCase().equals(p.getUsername()))
                    return -1;
            }
        else
            return 0;
        createPlayer();
        getPlayers().get(currentPlayer).setUsername(chosenUsername.toUpperCase());
        return 1;
    }

    public int setControls(String currentControl, int currentPlayer, int currentControlNumber) {
        for (Player player : players) {
            for (int i = 0; i < 4; i++) {
                if (currentControl.equals(player.getControl(i))) {
                    return -1;
                }
            }
            if (currentControl.length() != 1 || currentControl.trim().isEmpty()) {
                    return 0;
            }
        }
        getPlayers().get(currentPlayer).setPlayerControls(currentControlNumber, currentControl);
        return 1;
    }

    public String[] randomCategories() {
        ArrayList<String> randomCategories = new ArrayList<>();
        boolean flag = false;
        for (String s : categories) {
            int questionCount=0;
            for (Questions q : availableQuestions) {
                if (q.getCategory().equals(s) && !randomCategories.contains(s)) {
                    questionCount++;
                    if(questionCount>4) {
                        randomCategories.add(s);
                        flag = true;
                    }
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
            for (int i = randomCategories.size(); i < 4; i++)
                random4[i] = "";
        }
        return random4;
    }

    public int correctAnswer(char answer, Questions question, String currentRound, int currentRoundTypeParameter) {
        for (Player p : getPlayers()) {
            for (int i = 0; i < 4; i++)
                if (Character.toUpperCase(answer) == p.getControl(i).charAt(0) && !p.getHasAnswered()) {
                    p.setHasAnswered(true);
                    if (question.getAnswers().get(i).equals(question.correctAnswer))
                        round.calculatePoints(true, currentRound, p, currentRoundTypeParameter,getPlayers().size());
                    else
                        round.calculatePoints(false, currentRound, p, 0,getPlayers().size());
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

    public void sortPlayersByPoints(){
        for (int i=1; i<=getPlayers().size()-1;i++){
            for (int j=1;j<=getPlayers().size()-i;j++){
                Player first=getPlayers().get(j-1);
                Player second=getPlayers().get(j);
                if (first.getPoints()<second.getPoints()){
                    Player temp=getPlayers().get(j-1);
                    getPlayers().set(j-1,getPlayers().get(j));
                    getPlayers().set(j,temp);
                }
            }
        }
    }

    public void addStats() throws IOException {
        File oldFile=new File("Player Stats.txt");
        if (!oldFile.exists()) {
            oldFile.createNewFile();
        }
        Writer writer= new BufferedWriter(new FileWriter(oldFile, true));
        Scanner scannerMain=new Scanner(oldFile);
        if (!scannerMain.hasNextLine()) {
            for (Player p : getPlayers()) {
                writer.append(p.getUsername() + " " + p.getPoints() + " " + p.getMultiplayerWins() + "\n");
            }
            writer.close();
        }
        else {
            while (scannerMain.hasNextLine()) {
                boolean flag=false;
                ArrayList<Player> toRemove=new ArrayList<>();
                ArrayList<Player> toAdd=new ArrayList<>();
                Player tempPlayer=new Player();
                tempPlayer.setUsername(scannerMain.next());
                tempPlayer.setPoints(scannerMain.nextInt());
                tempPlayer.setMultiplayerWins(scannerMain.nextInt());
                for (Player p: getPlayers()){
                    if (tempPlayer.getUsername().equals(p.getUsername())) {
                        if (tempPlayer.getPoints() > p.getPoints()) {
                            tempPlayer.setMultiplayerWins(tempPlayer.getMultiplayerWins() + p.getMultiplayerWins());
                            toRemove.add(p);
                            toAdd.add(tempPlayer);
                        } else
                            p.setMultiplayerWins(p.getMultiplayerWins() + tempPlayer.getMultiplayerWins());
                        flag = true;
                        break;
                    }
                }
                getPlayers().removeAll(toRemove);
                getPlayers().addAll(toAdd);
                if (!flag)
                    getPlayers().add(tempPlayer);
                if (scannerMain.hasNextLine())
                    scannerMain.nextLine();
                else
                    break;
            }
            sortPlayersByPoints();
            scannerMain.close();
            writer.close();
            Files.deleteIfExists(Paths.get("Player Stats.txt"));
            File newFile=new File("Player Stats.txt");
            newFile.createNewFile();
            Writer newWriter= new BufferedWriter(new FileWriter("Player Stats.txt", true));
            for (Player p:getPlayers()){
                newWriter.append(p.getUsername() + " " + p.getPoints()+ " "+p.getMultiplayerWins()+ "\n");
            }
            newWriter.close();
        }
    }
}