import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.Scanner;
import java.util.Iterator;
import java.io.BufferedReader;


public class Game {
    ArrayList<Player> playerCount;
    ArrayList<Questions> availableQuestions;
    Menu currentMenu;
    Set<String> categories;


    public Game(File[] questions, String username) throws FileNotFoundException {
        availableQuestions=new ArrayList();
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].isFile()) {
                Scanner scan = new Scanner(questions[i]);
                Questions tempQuestion;
                tempQuestion=new Questions();
                availableQuestions.add(tempQuestion);
                while (scan.hasNextLine()) {
                    tempQuestion.setCategory(scan.nextLine());
                    tempQuestion.setQuestion(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setAnswer(scan.nextLine());
                    tempQuestion.setCorrectAnswer(scan.nextLine());
                    //In version 2 add image
                }
                System.out.println(tempQuestion.getCategory());
                System.out.println(tempQuestion.getQuestion());
                System.out.println(tempQuestion.getAnswers());
                System.out.println(tempQuestion.getCorrectAnswer());
            }
            playerCount=new ArrayList();
            Player tempPlayer=new Player(username);
            playerCount.add(tempPlayer);



        }

    }
}

