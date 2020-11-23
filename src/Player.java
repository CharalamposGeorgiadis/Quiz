import java.util.Scanner;

public class Player {
    private String username;
    private int points;
    private char[] controls;


    Player(String username){
        this.username=username;
        points=0;
        controls = new char[4];
    }


    public void setPlayerControls(int currentAnswer, char chosenControl){
        controls[currentAnswer]=chosenControl;
    }

    public String getUsername(){
        return username;
    }

    public int getPoints(){
        return points;
    }

    public char getControl(int currentControl){
        return controls[currentControl];
    }

    public void setPoints(int points){
        this.points=points;
    }


}