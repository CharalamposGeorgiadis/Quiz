import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Testers for Game class.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 11/01/2021
 */

public class GameTest {

    @Rule
    public TemporaryFolder tempFolder=new TemporaryFolder(); // Temporary folder where test question files will be stored.
    private Game testGame; // Test Game Object.

    /**
     * @throws IOException if stream to file cannot be written to or closed.
     */
    @Before
    public void init() throws IOException {
        // Folder path.
        File testPath = tempFolder.newFolder("testFolder");
        // Test question 1.
        File file1 = new File(testPath, "file1.txt");
        Writer writer= new BufferedWriter(new FileWriter(file1, true));
        writer.write("Sports\nHello World\na\nb\nc\nd\na\nNULL");
        writer.close();
        //Test question 2.
        File file2 = new File(testPath, "file2.txt");
        Writer writer2 = new BufferedWriter(new FileWriter(file2, true));
        writer2.write("Video Games\nHello World\na\nb\nc\nd\na\nNULL");
        writer2.close();
        // Holds the directory of the test questions folder
        File[] testQuestions = testPath.listFiles();
        testGame = new Game(testQuestions);
    }

    /**
     * Tests getRandomQuestion.
     */

    @Test
    public void getRandomQuestion() {
        Questions testQuestion = new Questions();
        testQuestion.setCategory("Sports");
        testQuestion.setQuestion("Hello World");
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

    /**
     * Tests enterUsernames.
     */

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
        testResult = testGame.enterUsernames("123456789012345", 0);
        assertEquals(0,testResult);
        // Tests enterUsername when the chosen username is valid and does not already exist.
        testResult = testGame.enterUsernames("Fanis", 1);
        assertEquals(1,testResult);
    }

    /**
     * Tests setCurrentControl.
     */

    @Test
    public void setCurrentControl() {
        Player testPlayer = new Player();
        testPlayer.setPlayerControls(0,"A");
        testPlayer.setPlayerControls(1,"B");
        testPlayer.setPlayerControls(2,"C");
        testPlayer.setPlayerControls(3,"");
        testGame.getPlayers().add(testPlayer);
        // Tests setCurrentControl when the chosen control is already bound.
        int testResult = testGame.setCurrentControl("A", 0,3);
        assertEquals(-1,testResult);
        // Tests setCurrentControl when the chosen control's length is more that 1.
        testResult = testGame.setCurrentControl("AA", 0,3);
        assertEquals(0,testResult);
        // Tests setCurrentControl when the chosen control is valid.
        testResult = testGame.setCurrentControl("D", 0,3);
        assertEquals(1,testResult);
    }

    /**
     * Tests randomCategories.
     */

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
        // Tests randomCategories when there are less than 4 categories with enough questions and one category with not enough questions.
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

    /**
     * Tests correctAnswer.
     */

    @Test
    public void correctAnswer() {
        Player testPlayer = new Player();
        testPlayer.setPlayerControls(0,"A");
        testPlayer.setPlayerControls(1,"B");
        testPlayer.setPlayerControls(2,"C");
        testPlayer.setPlayerControls(3,"D");
        testPlayer.setPoints(1000);
        testGame.getPlayers().add(testPlayer);
        Questions question1=new Questions();
        question1.setAnswer("A");
        question1.setAnswer("B");
        question1.setAnswer("C");
        question1.setAnswer("D");
        question1.setCorrectAnswer("C");
        // Tests correctAnswer when the player chose the correct answer.
        int testResult = testGame.correctAnswer('C',question1,"RIGHT ANSWER",0);
        assertEquals(1,testResult);
        assertEquals(2000,testPlayer.getPoints());
        testPlayer.setHasAnswered(false);
        // Tests correctAnswer when the player chose the wrong answer.
        testResult = testGame.correctAnswer('B',question1,"RIGHT ANSWER",0);
        assertEquals(1,testResult);
        assertEquals(2000,testPlayer.getPoints());
        // Tests correctAnswer when the player has already answered this question, but pressed a valid control again before the second player has answered.
        testResult = testGame.correctAnswer('C',question1,"RIGHT ANSWER",0);
        assertEquals(0,testResult);
        assertEquals(2000,testPlayer.getPoints());
        testPlayer.setHasAnswered(false);
        // Tests correctAnswer when the player pressed a control that is not among their 4 chosen controls.
        testResult = testGame.correctAnswer('T',question1,"RIGHT ANSWER",0);
        assertEquals(0,testResult);
        assertEquals(2000,testPlayer.getPoints());
    }

    /**
     * Tests hasBet.
     */

    @Test
    public void hasBet() {

        Player testPlayer = new Player();
        testPlayer.setPlayerControls(0,"A");
        testPlayer.setPlayerControls(1,"B");
        testPlayer.setPlayerControls(2,"C");
        testPlayer.setPlayerControls(3,"D");
        testGame.getPlayers().add(testPlayer);
        // Tests hasBet when the player has chosen the 3rd betting option.
        int testResult = testGame.hasBet('C');
        assertEquals(1,testResult);
        assertEquals(750,testPlayer.getBet());
        // Tests hasBet when the player has chosen the 1st betting option, while he has already chosen another option before the second player has chosen.
        testResult = testGame.hasBet('A');
        assertEquals(0,testResult);
        assertEquals(750,testPlayer.getBet());
        testPlayer.setHasAnswered(false);
        testPlayer.setBet(0);
        // Tests hasBet when the player pressed a control that is not among their 4 chosen controls.
        testResult = testGame.hasBet('T');
        assertEquals(0,testResult);
        assertEquals(0,testPlayer.getBet());
    }

    /**
     * Tests sortPlayersByPoints.
     */

    @Test
    public void sortPlayersByPoints() {
        Player testPlayer1 = new Player();
        testPlayer1.setUsername("winters");
        testPlayer1.setPoints(2000);
        testGame.getPlayers().add(testPlayer1);

        Player testPlayer2 = new Player();
        testPlayer2.setUsername("Sally");
        testPlayer2.setPoints(6500);
        testGame.getPlayers().add(testPlayer2);

        Player testPlayer3 = new Player();
        testPlayer3.setUsername("Darth Maul");
        testPlayer3.setPoints(4000);
        testGame.getPlayers().add(testPlayer3);

        ArrayList<String> correctUsernames = new ArrayList<>();
        correctUsernames.add("Sally");
        correctUsernames.add("Darth Maul");
        correctUsernames.add("winters");
        ArrayList<Integer> correctPoints = new ArrayList<>();
        correctPoints.add(6500);
        correctPoints.add(4000);
        correctPoints.add(2000);
        testGame.sortPlayersByPoints();
        for (int i=0; i<3; i++) {
            assertEquals(correctUsernames.get(i), testGame.getPlayers().get(i).getUsername());
            assertEquals((int)correctPoints.get(i),testGame.getPlayers().get(i).getPoints());
        }
    }
}