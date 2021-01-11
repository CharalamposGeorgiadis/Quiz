import  org.junit.Test;
import static org.junit.Assert.*;

public class RoundTest {

    @Test
    public void getRoundDescription() {
        Round testRound = new Round();
        String countdownDescription=("You have 5 seconds for each question. Correct answers grant points equal to the time remaining(in milliseconds)x0.2");
        assertEquals(countdownDescription ,testRound.getRoundDescription("COUNTDOWN"));
        String rightAnswerDescription=("Correct answers grant 1000 points, incorrect answers grant 0.");
        assertEquals(rightAnswerDescription ,testRound.getRoundDescription("RIGHT ANSWER"));
    }

    @Test
    public void calculatePoints() {
        Round testRound = new Round();
        Player testPlayer = new Player();
        int testPoints;
        int testThermometer;
        testPlayer.setPoints(5000);
        testRound.calculatePoints(true,"RIGHT ANSWER",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(6000 ,testPoints);

        testPlayer.setBet(1000);
        testRound.calculatePoints(true,"BETTING",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(7000 ,testPoints);
        testRound.calculatePoints(false,"BETTING",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(6000 ,testPoints);

        testRound.calculatePoints(true,"COUNTDOWN",testPlayer,4000,0);
        testPoints = testPlayer.getPoints();
        assertEquals(6800 ,testPoints);

        Player testPlayer2 = new Player();
        testPlayer2.setPoints(2000);
        testRound.calculatePoints(true,"FASTEST FINGER",testPlayer2,0,2);
        testPoints = testPlayer2.getPoints();
        assertEquals(3000 ,testPoints);
        testRound.calculatePoints(true,"FASTEST FINGER",testPlayer,0,2);
        testPoints = testPlayer.getPoints();
        assertEquals(7300 ,testPoints);

        testPlayer.setThermometerCorrectAnswers(3);
        testRound.calculatePoints(true,"THERMOMETER",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(7300 ,testPoints);
        testThermometer = testPlayer.getThermometerCorrectAnswers();
        assertEquals(4,testThermometer);

        testRound.calculatePoints(true,"THERMOMETER",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(12300 ,testPoints);

        testPlayer.setThermometerCorrectAnswers(2);
        testRound.calculatePoints(false,"THERMOMETER",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(12300 ,testPoints);
        testThermometer = testPlayer.getThermometerCorrectAnswers();
        assertEquals(2,testThermometer);

        testRound.calculatePoints(false,"THERMOMETER",testPlayer,0,0);
        testPoints = testPlayer.getPoints();
        assertEquals(12300 ,testPoints);

    }
}
