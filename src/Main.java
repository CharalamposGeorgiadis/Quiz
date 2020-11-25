import java.io.FileNotFoundException;
import java.io.File;

/**
 * Main class of the application.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Main {

    /**
     * Main function of the application.
     * @param args An array of command-line arguments for the application
     * @throws FileNotFoundException if a file is not found.
     */
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