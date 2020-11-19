public class Player {
    private String username;
    private int points;


    Player(String username){
        this.username=username;
        points=0;
    }

    public String getUsername(){
        return username;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points=points;
    }


}