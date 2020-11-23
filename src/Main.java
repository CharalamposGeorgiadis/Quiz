import java.io.FileNotFoundException;
import java.util.Scanner;
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
                    //String username = menu.chooseUsername();
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
            /*if (menuOption.equals("1") ) {
                File path = new File("Buzz Questions Directory");
                File [] questions = path.listFiles();
                String username=menu.chooseUsername();
                game=new Game(questions, username);
                game.startGame();
            } else if (menuOption.equals("2")) {
                System.out.println("2-PLAYER GAME NOT SUPPORTED, RETURNING TO MAIN MENU\n");
            } else if (menuOption.equals("3")){
                menu.controls();
            } else if (menuOption.equals("4")) {
                System.out.println("PLAYER STATS NOT SUPPORTED, RETURNING TO MAIN MENU\n");
            } else {
                return;
            }*/
        }
    }












