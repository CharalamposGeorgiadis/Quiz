import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Menu {
    private ArrayList<char[]>controls;



    public Menu(){
        controls=new ArrayList();
        char[] inputControl=new char[4];
        inputControl[0]='1';
        inputControl[1]='2';
        inputControl[2]='3';
        inputControl[3]='4';
        controls.add(inputControl);
    }



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
        /*System.out.println("                  PLAYER 1      PLAYER 2\n");
        System.out.println(" ANSWER A:          "+controls.get(0)[0]+"             7\n");
        System.out.println(" ANSWER B:          2             8\n");
        System.out.println(" ANSWER C:          3             9\n");
        System.out.println(" ANSWER D:          4             0\n");*/
        for (int i=0; i<playerCount.size(); i++) {
                System.out.print("           PLAYER " + (i + 1));
        }
        System.out.print("\nANSWER A:     ");
        for (int i=0; i<playerCount.size(); i++) {
            System.out.print(playerCount.get(i).getControl(0)+"                  ");
        }
        System.out.print("\nANSWER B:     ");
        for (int i=0; i<playerCount.size(); i++) {
            System.out.print(playerCount.get(i).getControl(1)+"                  ");
        }
        System.out.print("\nANSWER C:     ");
        for (int i=0; i<playerCount.size(); i++) {
            System.out.print(playerCount.get(i).getControl(2)+"                  ");
        }
        System.out.print("\nANSWER D:     ");
        for (int i=0; i<playerCount.size(); i++) {
            System.out.print(playerCount.get(i).getControl(3)+"                  ");
        }
        System.out.println("\nPress any key to return to Main Menu");
        String anyKey= console.nextLine();
        return;
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
                if (choice.equals("v")) {
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

    public String chooseUsername(int currentPlayer){
        System.out.println("ENTER A USERNAME FOR PLAYER "+(currentPlayer+1));
        Scanner console = new Scanner(System.in);
        String username=console.nextLine();
        return username;
    }

    public void setControls(int currentPlayerNumber, Player currentPlayer, ArrayList<Player>playerCount){
        Scanner console = new Scanner(System.in);
        System.out.println("Set the controls for Player "+(currentPlayerNumber+1));
        char tempControl;
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
            tempControl = console.next().charAt(0);
            for (Player p : playerCount) {
                for (int j=0; j<4; j++) {
                    if (tempControl == p.getControl(j)) {
                        System.out.println("Control already bound, please set a different control");
                        break;
                    }
                }
            }
            currentPlayer.setPlayerControls(i,tempControl);
        }
    }

}









