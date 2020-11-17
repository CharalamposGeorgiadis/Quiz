import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu;
        Game game;
        menu=new Menu();
        while(true) {
            int menuOption=menu.mainMenu();
            if (menuOption == 1) {
                game=new Game();
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












