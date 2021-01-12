import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Testers for Round class.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 11/01/2021
 */

public class GameTest {

    @Rule
    public TemporaryFolder tempFolder=new TemporaryFolder();
    private File testFolder;
    private File file1;
    private File file2;
    private File[] testQuestions;
    private Game testGame;

    @Before
    public void init() throws IOException {
        testFolder = tempFolder.newFolder("testFolder");
        file1 = new File(testFolder,"file1.txt");
        Writer writer= new BufferedWriter(new FileWriter(file1, true));
        writer.write("Sports\nabcd\na\nb\nc\nd\na\nNULL");
        writer.close();
        file2 = new File(testFolder,"file2.txt");
        Writer writer2 = new BufferedWriter(new FileWriter(file2, true));
        writer2.write("Video Games\nabcd\na\nb\nc\nd\na\nNULL");
        writer2.close();
        testQuestions = testFolder.listFiles();
        testGame = new Game(testQuestions);
    }

    @Test
    public void getRandomQuestion() {
        Questions testQuestion = new Questions();
        testQuestion.setCategory("Sports");
        testQuestion.setQuestion("abcd");
        testQuestion.getAnswers().add("a");
        testQuestion.getAnswers().add("b");
        testQuestion.getAnswers().add("c");
        testQuestion.getAnswers().add("d");
        testQuestion.setCorrectAnswer("a");
        testQuestion.setMedia("NULL");

        Questions returnedQuestion = testGame.getRandomQuestion("Sports");
        assertEquals(testQuestion.getCategory(),returnedQuestion.getCategory());
        assertEquals(testQuestion.getQuestion(),returnedQuestion.getQuestion());
        assertEquals(testQuestion.getCorrectAnswer(),returnedQuestion.getCorrectAnswer());
        assertEquals(testQuestion.getMedia(),returnedQuestion.getMedia());
    }


    @Test
    public void enterUsernames() {
        Player testPlayer = new Player();
        testPlayer.setUsername("HARRIS");
        testGame.getPlayers().add(testPlayer);
        // Tests enterUsername when the chosen username already exists.
        int testResult = testGame.enterUsernames("Harris", 0);
        assertEquals(-1,testResult);
        // Tests enterUsername when the chosen username's length is 0.
        testResult = testGame.enterUsernames("", 0);
        assertEquals(0,testResult);
        // Tests enterUsername when the chosen username consists only of empty space.
        testResult = testGame.enterUsernames("    ", 0);
        assertEquals(0,testResult);
        // Tests enterUsername when the chosen username's length is over 14.
        testResult = testGame.enterUsernames("qwertyuiopasdfg", 0);
        assertEquals(0,testResult);
        // Tests enterUsername when the chosen username is valid and does not already exist.
        testResult = testGame.enterUsernames("Fanis", 1);
        assertEquals(1,testResult);
    }

    @Test
    public void setCurrentControl() {
        Player testPlayer = new Player();
        testPlayer.setPlayerControls(0,"A");
        testPlayer.setPlayerControls(1,"B");
        testPlayer.setPlayerControls(2,"C");
        testPlayer.setPlayerControls(3,"");
        testGame.getPlayers().add(testPlayer);
        int testResult = testGame.setCurrentControl("A", 0,3);
        assertEquals(-1,testResult);

        testResult = testGame.setCurrentControl("AAAAAAAAAA", 0,3);
        assertEquals(0,testResult);

        testResult = testGame.setCurrentControl("D", 0,3);
        assertEquals(1,testResult);

    }

    @Test
    public void randomCategories() {





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
                        break;
                    }
                }
            }
            if (!flag)
                categories.remove(s);
        }
        Collections.shuffle(randomCategories);
        if (randomCategories.size()<4){
            for (int i=randomCategories.size(); i<4;i++)
                randomCategories.add("");
        }
        return randomCategories;
    }

    @Test
    public void correctAnswer() {
    }

    @Test
    public void hasBet() {
    }

    @Test
    public void sortPlayersByPoints() {
    }

    @Test
    public void addStats() {
    }
}