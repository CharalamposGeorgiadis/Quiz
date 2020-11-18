import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Menu menu;
        Game game;
        menu=new Menu();
        while(true) {
            int menuOption=menu.mainMenu();
            if (menuOption == 1) {
                File path = new File("Buzz Questions Directory");
                File [] questions = path.listFiles();
                System.out.println("ENTER USERNAME: \n");
                Scanner console = new Scanner(System.in);
                String username=console.nextLine();


                game=new Game(questions, username);

                break;
            } else if (menuOption == 2) {
                System.out.println("2-PLAYER GAME NOT SUPPORTED, RETURNING TO MAIN MENU\n");
            } else if (menuOption == 3) {
                menu.controls();
            } else if (menuOption == 4) {
                System.out.println("PLAYER STATS NOT SUPPORTED, RETURNING TO MAIN MENU\n");
            } else {
                return;
            }
        }


        }

}












