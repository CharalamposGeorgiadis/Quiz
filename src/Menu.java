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
        System.out.println("PRESS 1 TO START GAME (MORE THAN 1 PLAYERS NOT SUPPORTED AT THE MOMENT)\n"); //max players=4
        System.out.println("PRESS 2 TO VIEW PLAYER STATS (NOT SUPPORTED AT THE MOMENT)\n");
        System.out.println("PRESS ANY OTHER KEY TO EXIT GAME");
        return console.nextLine();
    }

    /**
     * Asks the user to choose the number of players.
     * @return  An Integer containing the chosen number of players.
     * @exception NumberFormatException on String input.
     */

    public int chooseNumberOfPlayers() {
        System.out.println("Choose Number of Players (Max players:4)");
        Scanner console = new Scanner(System.in);
        while (true) {
            String choice = console.nextLine();
            try {
                if (Integer.parseInt(choice)>0 && Integer.parseInt(choice)<5)
                    return Integer.parseInt(choice);
                else
                    System.out.println("Please choose an appropriate amount of players");
            } catch (NumberFormatException e) {
                System.out.println("Please enter an appropriate number");
            }
        }
    }

    /**
     * Shows each player's controls.
     * @param players A list that contains the information of each player.
     */

    public void viewControls(ArrayList<Player> players) {
        Scanner console = new Scanner(System.in);
        for (int i=0; i<players.size(); i++) {
                System.out.print("           PLAYER " + (i + 1));
        }
        System.out.print("\nANSWER A:     ");
        for (Player p : players) {
            System.out.print(p.getControl(0) + "                  ");
        }
        System.out.print("\nANSWER B:     ");
        for (Player p : players) {
            System.out.print(p.getControl(1) + "                  ");
        }
        System.out.print("\nANSWER C:     ");
        for (Player p : players) {
            System.out.print(p.getControl(2) + "                  ");
        }
        System.out.print("\nANSWER D:     ");
        for (Player p : players) {
            System.out.print(p.getControl(3) + "                  ");
        }
        System.out.println("\nPress any key to return to Main Menu");
        console.nextLine();
    }

    /**
     * Asks a player to choose a category or view the current controls.
     * @param categories HashSet of Strings containing all the available categories.
     * @param players A list that contains the information of each player.
     * @return A String containing the chosen menu option.
     * @exception NumberFormatException on String input.
     */

    public String chooseCategory(HashSet<String> categories, ArrayList<Player> players) {
        System.out.println("CHOOSE A CATEGORY");
        int i = 1;
        String[] temp = new String[categories.size()]; //Temporary string array that will hold the names of each category.
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
                    System.out.println("Please enter a valid input");
            }
            catch (NumberFormatException e) {
                if (choice.toLowerCase().equals("v")) {
                    this.viewControls(players);
                    return chooseCategory(categories,players);
                }
                else
                    System.out.println("Please enter a valid input");
            }
        }
    }

    /**
     * Asks each player to bet points for the betting portion of the game.
     * @param player One of the players
     * @return An Integer containing the chosen bet.
     */

    public int betPoints(Player player) {
        Scanner console = new Scanner(System.in);
        System.out.println("Choose your betting points (Use your respective controls)");
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
            System.out.println("Please enter an acceptable control!");
        }
    }

    /**
     * Asks a player to choose their username.
     * If no username was entered or if the username only contains spaces, it asks the user for a new username.
     * @param currentPlayerNumber The nth player who is currently choosing their username.
     * @return A String containing the chosen username.
     */

    public String chooseUsername(int currentPlayerNumber){
        System.out.println("ENTER A USERNAME FOR PLAYER "+(currentPlayerNumber+1));
        Scanner console = new Scanner(System.in);
        String choice=console.nextLine();
        while(true){
            if (choice.length()==0 || choice.trim().isEmpty()) { //
                System.out.println("Please enter a valid Username");
                choice = console.nextLine();
            }
            else
                return choice;
        }
    }

    /**
     * Asks the current player to set their controls.
     * @param currentPlayerNumber The nth player who is currently choosing their username.
     * @param currentPlayer The player who is currently setting their controls.
     * @param players A list that contains the information of each player.
     */

    public void setControls(int currentPlayerNumber, Player currentPlayer, ArrayList<Player> players){
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
            for (int k=0; k<players.size(); k++) {
                for (int j=0; j<4; j++) {
                    if (tempControl.equals(String.valueOf(players.get(k).getControl(j)))) {
                            System.out.println("Control already bound, please enter a different control");
                            tempControl = console.nextLine();
                            k=-1;
                            j=4;
                    }
                    else if (tempControl.length() != 1) {
                        System.out.println("Please enter a valid control");
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