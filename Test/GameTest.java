import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

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
    public void getRandomQuestion() throws IOException {
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
    public void enterUsernames() throws IOException {
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
    }

    @Test
    public void randomCategories() {
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