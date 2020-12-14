import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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

    public static void main(String[] args) throws IOException {
        GUI gui = new GUI();
        gui.startGUI();
       /* Menu menu;
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
                    File stats=new File("Player Stats.txt");
                    Scanner console = new Scanner(System.in);
                    try{
                        Scanner sc = new Scanner(new File("Player Stats.txt"));
                        System.out.println("PLAYER STATS");
                        while (sc.hasNextLine()) {
                            System.out.println("       " + sc.nextLine());
                        }
                        sc.close();
                    }
                    catch (IOException e) {
                        System.out.println("No Player Stats file found");
                    }
                    System.out.println("\nPress any key to return to Main Menu");
                    console.nextLine();
                    break;
                default:
                    return;
            }
        }*/
    }
 }
