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
        testQuestion.setAnswer("a");
        testQuestion.setAnswer("b");
        testQuestion.setAnswer("c");
        testQuestion.setAnswer("d");
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
        Questions question1=new Questions();
        question1.setCategory("1");
        Questions question2=new Questions();
        question2.setCategory("2");
        Questions question3=new Questions();
        question3.setCategory("3");
        Questions question4=new Questions();
        question4.setCategory("4");
        for (int i=0;i<5;i++){
            testGame.getAvailableQuestions().add(question1);
            testGame.getAvailableQuestions().add(question2);
            testGame.getAvailableQuestions().add(question3);
            testGame.getAvailableQuestions().add(question4);
        }
        testGame.getCategories().add("1");
        testGame.getCategories().add("2");
        testGame.getCategories().add("3");
        testGame.getCategories().add("4");

        ArrayList<String> correctCategories=new ArrayList<>();
        correctCategories.add("1");
        correctCategories.add("2");
        correctCategories.add("3");
        correctCategories.add("4");

        // Tests randomCategories when there are 4 categories with enough questions.
        ArrayList<String> testCategories = testGame.randomCategories();
        Collections.sort(testCategories);
        assertEquals(correctCategories.get(0),testCategories.get(0));
        assertEquals(correctCategories.get(1),testCategories.get(1));
        assertEquals(correctCategories.get(2),testCategories.get(2));
        assertEquals(correctCategories.get(3),testCategories.get(3));
        // Tests randomCategories when there are 3 categories with enough questions and one category with not enough questions.
        correctCategories.set(0,"");
        correctCategories.set(1,"1");
        correctCategories.set(2,"2");
        correctCategories.set(3,"3");
        for (int i=0;i<5;i++)
            testGame.getAvailableQuestions().remove(question4);
        testCategories.clear();
        testCategories = testGame.randomCategories();
        Collections.sort(testCategories);
        assertEquals(correctCategories.get(0),testCategories.get(0));
        assertEquals(correctCategories.get(1),testCategories.get(1));
        assertEquals(correctCategories.get(2),testCategories.get(2));
        assertEquals(correctCategories.get(3),testCategories.get(3));
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