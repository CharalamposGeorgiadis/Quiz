import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/**
 * Testers for Round class.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 11/01/2021
 */

public class PlayerStatsTest {

    /**
     * Tests sortStatsByPoints.
     */

    @Test
    public void sortStatsByPoints() {
        Stats testStats = new Stats();
        testStats.getUsernames().add("winters");
        testStats.getUsernames().add("Fawlty");
        testStats.getUsernames().add("Darth Maul");
        testStats.getPlayerHighScores().add(2000);
        testStats.getPlayerHighScores().add(6500);
        testStats.getPlayerHighScores().add(4000);
        testStats.getMultiplayerWins().add(1);
        testStats.getMultiplayerWins().add(0);
        testStats.getMultiplayerWins().add(3);
        ArrayList<String> correctUsernames = new ArrayList<>();
        correctUsernames.add("Fawlty");
        correctUsernames.add("Darth Maul");
        correctUsernames.add("winters");
        ArrayList<Integer> correctPoints = new ArrayList<>();
        correctPoints.add(6500);
        correctPoints.add(4000);
        correctPoints.add(2000);
        ArrayList<Integer> correctMultiplayerWins = new ArrayList<>();
        correctMultiplayerWins.add(0);
        correctMultiplayerWins.add(3);
        correctMultiplayerWins.add(1);
        testStats.sortStats("PLAYER_POINTS");
        assertEquals(correctUsernames, testStats.getUsernames());
        assertEquals(correctPoints, testStats.getPlayerHighScores());
        assertEquals(correctMultiplayerWins, testStats.getMultiplayerWins());
    }

    /**
     * Tests sortStatsByMultiplayerWins.
     */

    @Test
    public void sortStatsByMultiplayerWins() {
        Stats testStats = new Stats();
        testStats.getUsernames().add("winters");
        testStats.getUsernames().add("Fawlty");
        testStats.getUsernames().add("Darth Maul");
        testStats.getPlayerHighScores().add(2000);
        testStats.getPlayerHighScores().add(6500);
        testStats.getPlayerHighScores().add(4000);
        testStats.getMultiplayerWins().add(1);
        testStats.getMultiplayerWins().add(0);
        testStats.getMultiplayerWins().add(3);
        ArrayList<String> correctUsernames = new ArrayList<>();
        correctUsernames.add("Darth Maul");
        correctUsernames.add("winters");
        correctUsernames.add("Fawlty");
        ArrayList<Integer> correctPoints = new ArrayList<>();
        correctPoints.add(4000);
        correctPoints.add(2000);
        correctPoints.add(6500);
        ArrayList<Integer> correctMultiplayerWins = new ArrayList<>();
        correctMultiplayerWins.add(3);
        correctMultiplayerWins.add(1);
        correctMultiplayerWins.add(0);
        testStats.sortStats("MULTIPLAYER");
        assertEquals(correctUsernames, testStats.getUsernames());
        assertEquals(correctPoints, testStats.getPlayerHighScores());
        assertEquals(correctMultiplayerWins, testStats.getMultiplayerWins());
    }
}