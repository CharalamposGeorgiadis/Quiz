import  org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testers for Round class.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 11/01/2021
 */

public class RoundTest {

    /**
     * Tests getRoundDescription for every possible valid String input.
     */

    @Test
    public void getRoundDescription() {
        Round testRound = new Round();
        //Testing for RIGHT ANSWER.
        String rightAnswerDescription=("Correct answers grant 1000 points, incorrect answers grant 0.");
        assertEquals(rightAnswerDescription ,testRound.getRoundDescription("RIGHT ANSWER"));
        //Testing for BETTING.
        String bettingDescription=("Bet points for each question. Correct answers grant points equal to the ones you bet. Incorrect answers subtract the same amount.");
        assertEquals(bettingDescription ,testRound.getRoundDescription("BETTING"));
        //Testing for COUNTDOWN.
        String countdownDescription=("You have 5 seconds for each question. Correct answers grant points equal to the time remaining(in milliseconds)x0.2");
        assertEquals(countdownDescription ,testRound.getRoundDescription("COUNTDOWN"));
        //Testing for FASTEST FINGER.
        String fasterFingerDescription=("First player to answer correctly gains 1000 points. The second one gains 500.");
        assertEquals(fasterFingerDescription ,testRound.getRoundDescription("FASTEST FINGER"));
        //Testing for THERMOMETER.
        String thermometerDescription=("First player to answer 5 questions correctly gains 5000 points. Then, or if there are no more questions left, the game finishes.");
        assertEquals(thermometerDescription ,testRound.getRoundDescription("THERMOMETER"));
    }

    /**
     * Tests calculatePoints for every possible scenario.
     */

    @Test
    public void calculatePoints() {
        Round testRound = new Round();
        Player testPlayer = new Player();
        Player testPlayer2 = new Player();
        int testPoints;
        int testThermometer;
        testPlayer.setPoints(5000);
        // Testing for correct answer in RIGHT ANSWER.
        testRound.calculatePoints(true,"RIGHT ANSWER",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(6000 ,testPoints);
        // Testing for wrong answer in RIGHT ANSWER.
        testRound.calculatePoints(false,"RIGHT ANSWER",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(6000 ,testPoints);

        // Testing for correct answer in BETTING.
        testPlayer.setBet(1000);
        testRound.calculatePoints(true,"BETTING",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(7000 ,testPoints);
        // Testing for wrong answer in BETTING.
        testRound.calculatePoints(false,"BETTING",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(6000 ,testPoints);

        // Testing for correct answer in COUNTDOWN.
        testRound.calculatePoints(true,"COUNTDOWN",testPlayer,4000,0);
        testPoints = testPlayer.getPoints();
        assertEquals(6800 ,testPoints);
        // Testing for wrong answer in COUNTDOWN.
        testRound.calculatePoints(false,"COUNTDOWN",testPlayer,4000,0);
        testPoints = testPlayer.getPoints();
        assertEquals(6800 ,testPoints);

        // Testing for correct answer for both players. Player 2 answered first, while player 1 answered second.
        testPlayer2.setPoints(2000);
        testRound.calculatePoints(true,"FASTEST FINGER",testPlayer2,0,2);
        testPoints = testPlayer2.getPoints();
        assertEquals(3000 ,testPoints);
        testRound.calculatePoints(true,"FASTEST FINGER",testPlayer,0,2);
        testPoints = testPlayer.getPoints();
        assertEquals(7300 ,testPoints);
        // Testing for wrong answer for player 1 who answered first and correct answer for player 2 who answered second.
        testRound.calculatePoints(false,"FASTEST FINGER",testPlayer,0,2);
        testPoints = testPlayer.getPoints();
        assertEquals(7300 ,testPoints);
        testRound.calculatePoints(true,"FASTEST FINGER",testPlayer2,0,2);
        testPoints = testPlayer2.getPoints();
        assertEquals(4000 ,testPoints);
        // Testing for wrong answer for both players. Player 1 answered first player 2 answered second.
        testRound.calculatePoints(false,"FASTEST FINGER",testPlayer,0,2);
        testPoints = testPlayer.getPoints();
        assertEquals(7300 ,testPoints);
        testRound.calculatePoints(false,"FASTEST FINGER",testPlayer2,0,2);
        testPoints = testPlayer2.getPoints();
        assertEquals(4000 ,testPoints);

        // Testing for correct answer in THERMOMETER when the player has already answered 3 questions correctly.
        testPlayer.setThermometerCorrectAnswers(3);
        testRound.calculatePoints(true,"THERMOMETER",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(7300 ,testPoints);
        testThermometer = testPlayer.getThermometerCorrectAnswers();
        assertEquals(4,testThermometer);

        // Testing for correct answer in THERMOMETER when the player has already answered 4 questions correctly.
        testRound.calculatePoints(true,"THERMOMETER",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(12300 ,testPoints);

        // Testing for wrong answer in THERMOMETER when the player has already answered 2 questions correctly.
        testPlayer.setThermometerCorrectAnswers(2);
        testRound.calculatePoints(false,"THERMOMETER",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(12300 ,testPoints);
        testThermometer = testPlayer.getThermometerCorrectAnswers();
        assertEquals(2,testThermometer);
    }
}
