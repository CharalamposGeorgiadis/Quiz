import java.util.ArrayList;

/**
 * This class represents a question and its information.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Questions {
    private String question; // String that holds a question.
    private String category; // String that holds the category of the question.
    private ArrayList<String> answers; // List of Strings that holds the possible answers of the question.
    protected String correctAnswer; // String that holds the correct answer of the question.

    /**
     * Constructor
     */

    public Questions() {
        question = "";
        category="";
        answers=new ArrayList<>();
        String correctAnswer="";
    }

    /**
     * Sets the question.
     * @param question A String containing a question.
     */

    public void setQuestion(String question){
        this.question=question;
    }

    /**
     * Sets the question's category.
     * @param category A String containing the question's category.
     */

    public void setCategory(String category){
        this.category=category;
    }

    /**
     * Sets one of the question's possible answers.
     * @param answer A String containing one of the possible answers.
     */

    public void setAnswer(String answer){
        answers.add(answer);
    }

    /**
     * Sets the question's correct answer.
     * @param correctAnswer A String containing the question's correct answer.
     */

    public void setCorrectAnswer(String correctAnswer){
        this.correctAnswer=correctAnswer;
    }


    /**
     * Gets the question's category.
     * @return A string representing the question's category.
     */

    public String getCategory(){
        return category;
    }

    /**
     * Gets a question.
     * @return A string representing a question.
     */

    public String getQuestion(){
        return question;
    }

    /**
     * Gets the question's possible answers.
     * @return An ArrayList of Strings representing the question's possible answers.
     */

    public ArrayList<String> getAnswers(){
        return answers;
    }

    /**
     * Gets the question's correct answer.
     * @return An ArrayList of Strings representing the question's possible answers.
     */

    public String getCorrectAnswer(){
        return correctAnswer;
    }
}
