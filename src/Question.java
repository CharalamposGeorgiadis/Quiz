import java.util.ArrayList;

/**
 * This class represents a question and its information.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Question {
    private String question; // String that holds a question.
    private String category; // String that holds the category of the question.
    private ArrayList<String> answers; // ArrayList of Strings that holds the possible answers of the question.
    protected String correctAnswer; // String that holds the correct answer of the question.
    private String media; // String containing the question's accompanying media.

    /**
     * Constructor.
     */

    public Question() {
        question = "";
        category="";
        answers=new ArrayList<>();
        correctAnswer="";
        media ="";
    }

    /**
     * Gets a question.
     * @return String containing a question.
     */

    public String getQuestion(){
        return question;
    }

    /**
     * Sets the question.
     * @param question String containing a question.
     */

    public void setQuestion(String question){
        this.question=question;
    }

    /**
     * Gets the question's category.
     * @return String containing the question's category.
     */

    public String getCategory(){
        return category;
    }

    /**
     * Sets the question's category.
     * @param category String containing the question's category.
     */

    public void setCategory(String category){
        this.category=category;
    }

    /**
     * Gets the question's possible answers.
     * @return ArrayList of Strings containing the question's possible answers.
     */

    public ArrayList<String> getAnswers(){
        return answers;
    }

    /**
     * Sets one of the question's possible answers.
     * @param answer String containing one of the possible answers.
     */

    public void setAnswer(String answer){
        answers.add(answer);
    }

    /**
     * Gets the question's correct answer.
     * @return String containing the question's correct answer.
     */

    public String getCorrectAnswer(){
        return correctAnswer;
    }

    /**
     * Sets the question's correct answer.
     * @param correctAnswer String containing the question's correct answer.
     */

    public void setCorrectAnswer(String correctAnswer){
        this.correctAnswer=correctAnswer;
    }

    /**
     * Gets the question's accompanying media.
     * @return String containing the question's accompanying media.
     */

    public String getMedia(){
        return media;
    }

    /**
     * Sets the question's accompanying media.
     * @param media String containing the question's accompanying media. Media is NULL if the question is not accompanied by a media.
     */

    public void setMedia(String media) {
        this.media = media;
    }
}