import java.util.Scanner;
import java.util.Set;

public class Menu {



    public int mainMenu(){
        Scanner console = new Scanner(System.in);
        System.out.println("PRESS 1 TO START 1-PLAYER GAME\n");
        System.out.println("PRESS 2 TO START 2-PLAYER GAME (NOT SUPPORTED AT THE MOMENT)\n");
        System.out.println("PRESS 3 TO VIEW CONTROLS\n");
        System.out.println("PRESS 4 TO VIEW PLAYER STATS (NOT SUPPORTED AT THE MOMENT)\n");
        System.out.println("PRESS 5 TO EXIT GAME\n");
        return console.nextInt();
    }


    public int controls(){
        Scanner console = new Scanner(System.in);
        System.out.println(" PLAYER 1                        PLAYER 2\n");
        System.out.println(" ANSWER A:          1             7\n");
        System.out.println(" ANSWER B:          2             8\n");
        System.out.println(" ANSWER C:          3             9\n");
        System.out.println(" ANSWER D:          4             0\n");
        System.out.println("Press 0 to return to Main Menu");
        return console.nextInt();
    }




    public String chooseCategory(Set categories) {
        System.out.println("CHOOSE A CATEGORY");
        int i=1;
        String[] temp=new String[categories.size()];
        for (Object category : categories) {
            System.out.println("Press "+ i+" to choose "+category);
            temp[i-1]=category.toString();
            i++;
        }
        Scanner console = new Scanner(System.in);
        int choice=console.nextInt();
        return temp[choice-1];

    }
}
