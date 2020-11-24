import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Menu {

    public String mainMenu() {
        Scanner console = new Scanner(System.in);
        System.out.println("PRESS 1 TO START GAME (MORE THAN 1 PLAYERS NOT SUPPORTED AT THE MOMENT)\n"); //max players=8
        System.out.println("PRESS 2 TO VIEW PLAYER STATS (NOT SUPPORTED AT THE MOMENT)\n");
        System.out.println("PRESS ANY KEY TO EXIT GAME");
        return console.nextLine();
    }

    public int chooseNumberOfPlayers() {
        System.out.println("Choose Number of Players");
        Scanner console = new Scanner(System.in);
        while (true) {
            String choice = console.nextLine();
            try {
                if (Integer.parseInt(choice)>0&& Integer.parseInt(choice)<9)
                    return Integer.parseInt(choice);
                else
                    System.out.println("Please choose an appropriate amount of players");
            } catch (NumberFormatException e) {
                System.out.println("Please choose a suitable control");
            }
        }
    }

    public void viewControls(ArrayList<Player> playerCount) {
        Scanner console = new Scanner(System.in);
        for (int i=0; i<playerCount.size(); i++) {
                System.out.print("           PLAYER " + (i + 1));
        }
        System.out.print("\nANSWER A:     ");
        for (Player p : playerCount) {
            System.out.print(p.getControl(0) + "                  ");
        }
        System.out.print("\nANSWER B:     ");
        for (Player p : playerCount) {
            System.out.print(p.getControl(1) + "                  ");
        }
        System.out.print("\nANSWER C:     ");
        for (Player p : playerCount) {
            System.out.print(p.getControl(2) + "                  ");
        }
        System.out.print("\nANSWER D:     ");
        for (Player p : playerCount) {
            System.out.print(p.getControl(3) + "                  ");
        }
        System.out.println("\nPress any key to return to Main Menu");
        console.nextLine();
    }

    public String chooseCategory(Set categories, ArrayList<Player> playerCount) {
        System.out.println("CHOOSE A CATEGORY");
        int i = 1;
        String[] temp = new String[categories.size()];
        for (Object category : categories) {
            System.out.println("Press " + i + " to choose " + category);
            temp[i - 1] = category.toString();
            i++;
        }
        System.out.println("Press 'v' to view current controls");
        Scanner console = new Scanner(System.in);
        while(true) {
            String choice = console.nextLine();
            try
            {
                if (Integer.parseInt(choice) > 0 && Integer.parseInt(choice) < temp.length+1)
                    return temp[Integer.parseInt(choice) - 1];
                else
                    System.out.println("Please choose a suitable control");
            }
            catch (NumberFormatException e)
            {
                if (choice.toLowerCase().equals("v")) {
                    this.viewControls(playerCount);
                    return chooseCategory(categories,playerCount);
                }
                else
                    System.out.println("Please choose a suitable control");
            }
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
            String chosenBet = console.nextLine();
            if (chosenBet.equals("1"))
                return 250;
            else if (chosenBet.equals("2"))
                return 500;
            if (chosenBet.equals("3"))
                return 750;
            else if (chosenBet.equals("4"))
                return 1000;
            System.out.println("Please press an acceptable control!");
        }
    }

    public String chooseUsername(int currentPlayer){
        System.out.println("ENTER A USERNAME FOR PLAYER "+(currentPlayer+1));
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    public void setControls(int currentPlayerNumber, Player currentPlayer, ArrayList<Player>playerCount){
        Scanner console = new Scanner(System.in);
        System.out.println("Set the controls for Player "+ (currentPlayerNumber+1));
        String tempControl;
        for (int i=0; i<4; i++) {
            switch (i) {
                case 0:
                    System.out.println("Set control for answer A");
                    break;
                case 1:
                    System.out.println("Set control for answer B");
                    break;
                case 2:
                    System.out.println("Set control for answer C");
                    break;
                case 3:
                    System.out.println("Set control for answer D");
                    break;
            }
            tempControl = console.nextLine();
            for (int k=0; k<playerCount.size(); k++) {
                for (int j=0; j<4; j++) {
                    if (tempControl.equals(String.valueOf(playerCount.get(k).getControl(j)))) {
                            System.out.println("Control already bound, please set a different control");
                            tempControl = console.nextLine();
                            k=-1;
                            j=4;
                    }
                    else if (tempControl.length() != 1){
                        System.out.println("Please choose a suitable control");
                        tempControl = console.nextLine();
                        k=-1;
                        j=4;
                    }
                }
            }
            currentPlayer.setPlayerControls(i,tempControl.charAt(0));
        }
    }
}









