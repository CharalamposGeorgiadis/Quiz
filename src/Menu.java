import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * This class represents the different menu options that appear during the course of the game.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */


public class Menu {

    /**
     * Represents the main menu that appears at the start of the game.
     * @return  A String containing the chosen menu option.
     */

    public String mainMenu() {
        Scanner console = new Scanner(System.in);
        System.out.println("PRESS 1 TO START GAME (MORE THAN 1 PLAYERS NOT SUPPORTED AT THE MOMENT)\n"); //max players=8
        System.out.println("PRESS 2 TO VIEW PLAYER STATS (NOT SUPPORTED AT THE MOMENT)\n");
        System.out.println("PRESS ANY KEY TO EXIT GAME");
        return console.nextLine();
    }

    /**
     * Asks the user to choose the number of players.
     * @return  An Integer containing the chosen number of players.
     * @exception NumberFormatException on String input.
     */

    public int chooseNumberOfPlayers() {
        System.out.println("Choose Number of Players");
        Scanner console = new Scanner(System.in);
        while (true) {
            String choice = console.nextLine();
            try {
                if (Integer.parseInt(choice)>0 && Integer.parseInt(choice)<9)
                    return Integer.parseInt(choice);
                else
                    System.out.println("Please choose an appropriate amount of players");
            } catch (NumberFormatException e) {
                System.out.println("Please choose a suitable control");
            }
        }
    }

    /**
     * Shows each player's controls.
     * @param playerCount A list that contains the information of each player.
     */

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

    /**
     * Asks a player to choose a category or view the current controls.
     * @param categories HashSet of Strings containing all the available categories.
     * @param playerCount A list that contains the information of each player.
     * @return A String containing the chosen menu option.
     * @exception NumberFormatException on String input.
     */

    public String chooseCategory(HashSet<String> categories, ArrayList<Player> playerCount) {
        System.out.println("CHOOSE A CATEGORY");
        int i = 1;
        String[] temp = new String[categories.size()];
        for (String category : categories) {
            System.out.println("Press " + i + " to choose " + category);
            temp[i - 1] = category;
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

    /**
     * Asks each player to bet points for the betting portion of the game.
     * @return An Integer containing the chosen bet.
     */

    public int betPoints(Player player) {
        Scanner console = new Scanner(System.in);
        System.out.println("Choose your betting points");
        System.out.println("A: 250");
        System.out.println("B: 500");
        System.out.println("C: 750");
        System.out.println("D: 1000");
        while (true) {
            String chosenBet = console.nextLine();
            if (chosenBet.equals(String.valueOf(player.getControl(0))))
                return 250;
            else if (chosenBet.equals(String.valueOf(player.getControl(1))))
                return 500;
            if (chosenBet.equals(String.valueOf(player.getControl(2))))
                return 750;
            else if (chosenBet.equals(String.valueOf(player.getControl(3))))
                return 1000;
            System.out.println("Please press an acceptable control!");
        }
    }

    /**
     * Asks a player to choose their username.
     * @param currentPlayerNumber The nth player who is currently choosing their username.
     * @return A String containing the chosen username.
     */

    public String chooseUsername(int currentPlayerNumber){
        System.out.println("ENTER A USERNAME FOR PLAYER "+(currentPlayerNumber+1));
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    /**
     * Asks the current player to set their controls.
     * @param currentPlayerNumber The nth player who is currently choosing their username.
     * @param currentPlayer The player who is currently setting their controls.
     * @param playerCount A list that contains the information of each player.
     */

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

            // Loop that checks if the chosen control is already bound, whereupon it asks for a new control and restarts.
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
