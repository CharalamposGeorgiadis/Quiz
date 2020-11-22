import java.util.Scanner;
import java.util.Set;

public class Menu {


    public int mainMenu() {
        Scanner console = new Scanner(System.in);
        System.out.println("PRESS 1 TO START 1-PLAYER GAME\n");
        System.out.println("PRESS 2 TO START 2-PLAYER GAME (NOT SUPPORTED AT THE MOMENT)\n");
        System.out.println("PRESS 3 TO VIEW CONTROLS\n");
        System.out.println("PRESS 4 TO VIEW PLAYER STATS (NOT SUPPORTED AT THE MOMENT)\n");
        System.out.println("PRESS 5 TO EXIT GAME");
        return console.nextInt();
    }


    public void controls() {
        Scanner console = new Scanner(System.in);
        System.out.println(" PLAYER 1                        PLAYER 2\n");
        System.out.println(" ANSWER A:          1             7\n");
        System.out.println(" ANSWER B:          2             8\n");
        System.out.println(" ANSWER C:          3             9\n");
        System.out.println(" ANSWER D:          4             0\n");
        System.out.println("Press any key to return to Main Menu");
        String anyKey= console.nextLine();
        return;
    }


    public String chooseCategory(Set categories) {
        System.out.println("CHOOSE A CATEGORY");
        int i = 1;
        String[] temp = new String[categories.size()];
        for (Object category : categories) {
            System.out.println("Press " + i + " to choose " + category);
            temp[i - 1] = category.toString();
            i++;
        }
        Scanner console = new Scanner(System.in);
        while(true) {
            int choice = console.nextInt();
            if (choice > 0 && choice < temp.length+1)
                return temp[choice - 1];
            else
                System.out.println("Please choose a suitable control");
        }
    }

    public int betPoints() {
        Scanner console = new Scanner(System.in);
        System.out.println("Choose your betting points");
        System.out.println("1: 250");
        System.out.println("2: 500");
        System.out.println("3: 750");
        System.out.println("4: 1000");
        while (true) {
            int chosenBet = console.nextInt();
            if (chosenBet == 1)
                return 250;
            else if (chosenBet == 2)
                return 500;
            if (chosenBet == 3)
                return 750;
            else if (chosenBet == 4)
                return 1000;
            System.out.println("Please press an acceptable control!");
        }
    }

    public String chooseUsername(){
        System.out.println("ENTER USERNAME:");
        Scanner console = new Scanner(System.in);
        String username=console.nextLine();
        return username;
    }

}









