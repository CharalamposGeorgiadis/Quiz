import java.io.FileNotFoundException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Menu menu;
        Game game;
        menu=new Menu();
        while(true) {
            String menuOption=menu.mainMenu();
            switch(menuOption) {
                case "1":
                    File path = new File("Buzz Questions Directory");
                    File[] questions = path.listFiles();
                    game = new Game(questions);
                    game.startGame();
                    break;
                case "2":
                    System.out.println("PLAYER STATS NOT SUPPORTED, RETURNING TO MAIN MENU\n");
                    break;
                default:
                    return;
            }
        }
    }
}












