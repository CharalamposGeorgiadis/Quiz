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
            String menuOption=menu.mainMenu(); //Displays the main menu.
            switch(menuOption) { //Checks which menu option the player chose.
                case "1":
                    File path = new File("Buzz Questions Directory");
                    File[] questions = path.listFiles();
                    if (questions != null) {
                        game = new Game(questions,menu);
                        game.startGame(menu);
                    }
                    else {
                        System.out.println("PLEASE LOCATE THE BUZZ QUESTIONS DIRECTORY FOLDER.");
                        return;
                    }
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