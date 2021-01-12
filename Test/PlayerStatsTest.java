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
        PlayerStats testPlayerStats = new PlayerStats();
        testPlayerStats.getUsernames().add("winters");
        testPlayerStats.getUsernames().add("Sally");
        testPlayerStats.getUsernames().add("Darth Maul");
        testPlayerStats.getHighScores().add(2000);
        testPlayerStats.getHighScores().add(6500);
        testPlayerStats.getHighScores().add(4000);
        testPlayerStats.getMultiplayerWins().add(1);
        testPlayerStats.getMultiplayerWins().add(0);
        testPlayerStats.getMultiplayerWins().add(3);
        ArrayList<String> correctUsernames = new ArrayList<>();
        correctUsernames.add("Sally");
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
        testPlayerStats.sortStatsByPoints();
        assertEquals(correctUsernames,testPlayerStats.getUsernames());
        assertEquals(correctPoints,testPlayerStats.getHighScores());
        assertEquals(correctMultiplayerWins,testPlayerStats.getMultiplayerWins());
    }

    /**
     * Tests sortStatsByMultiplayerWins.
     */

    @Test
    public void sortStatsByMultiplayerWins() {
        PlayerStats testPlayerStats = new PlayerStats();
        testPlayerStats.getUsernames().add("winters");
        testPlayerStats.getUsernames().add("Sally");
        testPlayerStats.getUsernames().add("Darth Maul");
        testPlayerStats.getHighScores().add(2000);
        testPlayerStats.getHighScores().add(6500);
        testPlayerStats.getHighScores().add(4000);
        testPlayerStats.getMultiplayerWins().add(1);
        testPlayerStats.getMultiplayerWins().add(0);
        testPlayerStats.getMultiplayerWins().add(3);
        ArrayList<String> correctUsernames = new ArrayList<>();
        correctUsernames.add("Darth Maul");
        correctUsernames.add("winters");
        correctUsernames.add("Sally");
        ArrayList<Integer> correctPoints = new ArrayList<>();
        correctPoints.add(4000);
        correctPoints.add(2000);
        correctPoints.add(6500);
        ArrayList<Integer> correctMultiplayerWins = new ArrayList<>();
        correctMultiplayerWins.add(3);
        correctMultiplayerWins.add(1);
        correctMultiplayerWins.add(0);
        testPlayerStats.sortStatsByMultiplayerWins();
        assertEquals(correctUsernames,testPlayerStats.getUsernames());
        assertEquals(correctPoints,testPlayerStats.getHighScores());
        assertEquals(correctMultiplayerWins,testPlayerStats.getMultiplayerWins());
    }

}