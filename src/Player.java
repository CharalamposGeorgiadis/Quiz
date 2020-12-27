import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a player playing the game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Player {
    private String username; // String that holds the username of a player.
    private int points; // Integer that holds the points of a player.
    private String[] controls; // String Array that holds the controls of a player.
    private boolean hasAnswered;
    private int bet;

    /**
     * Constructor
     */

    public Player() {
        username="";
        points=0;
        controls= new String[4];
        hasAnswered=false;
        bet=0;
    }

    /**
     * Gets the player's username.
     * @return A String containing the player's username.
     */

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    /**
     * Gets the player's points.
     * @return An Integer containing the player's points.
     */

    public int getPoints(){
        return points;
    }

    /**
     * Sets the player's points.
     * @param points  A String containing the player's points.
     */

    public void setPoints(int points){
        this.points=points;
    }

    /**
     * Sets the player's controls.
     * @param currentAnswer The answer of which we want to set the control (A, B, C or D)
     * @param chosenControl A String containing the control chosen by the player.
     */

    public void setPlayerControls(int currentAnswer, String chosenControl){
        controls[currentAnswer]=chosenControl.toUpperCase();
    }

    /**
     * Gets one of the player's controls.
     * @param currentControl One of the player's controls.
     * @return A char containing one of the player's controls.
     */

    public String getControl(int currentControl){
        return controls[currentControl];
    }

    public void setHasAnswered(Boolean answered){
        hasAnswered=answered;
    }

    public boolean getHasAnswered(){
        return hasAnswered;
    }

    public void setBet(int bet){
        this.bet=bet;
    }

    public int getBet(){
        return bet;
    }

    public void clearControls(){
        for (int i=0; i<4; i++)
            setPlayerControls(i,"");
    }

    public void clearUsername(){
        setUsername("");
    }

    /**
     * Adds points to the player.
     * @param points An Integer containing the points that will be added.
     */

    public void addPoints(int points) {
        this.points+=points;
    }

    public void addStats(ArrayList<Player> players) throws IOException {
        File oldFile=new File("Player Stats.txt");
        if (!oldFile.exists()) {
            oldFile.createNewFile();
        }
        Writer writer= new BufferedWriter(new FileWriter("Player Stats.txt", true));
        Scanner scannerMain=new Scanner(new File("Player Stats.txt"));
        Scanner scannerLines=new Scanner(new File("Player Stats.txt"));
        if (!scannerMain.hasNextLine())
            for (Player p: players){
                writer.write(p.username + " " + p.points+ "\n");
            }
        else {
            int lines=0;
            while (scannerLines.hasNextLine()) {
                lines++;
                scannerLines.nextLine();
            }
            scannerLines.close();
            String[] usernames=new String[lines];
            int[] points= new int[lines];
            int i=0;
            while (scannerMain.hasNextLine()) {
                usernames[i]=scannerMain.next();
                points[i]=scannerMain.nextInt();
                i++;
                if (scannerMain.hasNextLine())
                    scannerMain.nextLine();
                else
                    break;
            }
            for (Player p:players) {
                for (int j = 0; j < usernames.length; j++) {
                    if (p.getUsername().equals(usernames[j])) {
                        if (p.getPoints() < points[j]) {
                            players.remove(p);
                            Player tempPlayer = new Player();
                            tempPlayer.setUsername(usernames[j]);
                            tempPlayer.setPoints(points[j]);
                            players.add(tempPlayer);
                        }
                        usernames[j]="";
                        break;
                    }
                }
            }
            for (int j=0; j<usernames.length; j++){
                if (!usernames[j].equals("")) {
                    Player tempPlayer = new Player();
                    tempPlayer.setUsername(usernames[j]);
                    tempPlayer.setPoints(points[j]);
                    players.add(tempPlayer);
                }
            }
        }
        String[] usernamesToWrite=new String[players.size()];
        int[] pointsToWrite=new int[players.size()];
        int j=0;
        for (Player p:players){
            usernamesToWrite[j]=p.getUsername();
            pointsToWrite[j]=p.getPoints();
            j++;
        }
        for (int k=0; k<pointsToWrite.length; k++) {
            for (int l = 1; l < pointsToWrite.length -k; l++) {
                if (pointsToWrite[l-1] < pointsToWrite[l]) {
                    int tempI = pointsToWrite[l-1];
                    pointsToWrite[l-1] = pointsToWrite[l];
                    pointsToWrite[l] = tempI;
                    String tempS = usernamesToWrite[l-1];
                    usernamesToWrite[l-1] = usernamesToWrite[l];
                    usernamesToWrite[l] = tempS;
                }
            }
        }
        scannerMain.close();
        writer.close();
        Files.deleteIfExists(Paths.get("Player Stats.txt"));
        File newFile=new File("Player Stats.txt");
        newFile.createNewFile();
        Writer newWriter= new BufferedWriter(new FileWriter("Player Stats.txt", true));
        for (int k=0; k<usernamesToWrite.length; k++){
            newWriter.append(usernamesToWrite[k]+ " "+pointsToWrite[k]+"\n");
        }
        newWriter.close();
    }
}