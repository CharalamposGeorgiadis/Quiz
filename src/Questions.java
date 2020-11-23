import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Questions {
    String question;
    String category;
    ArrayList<String> answers;
    String correctAnswer;
    BufferedImage questionImage;


    public Questions() {
        question = "";
        category="";
        answers=new ArrayList<>();
        String correctAnswer="";
        BufferedImage questionImage;
    }

    public void setQuestion(String question){
        this.question=question;
    }

    public void setCategory(String category){
        this.category=category;
    }

    public void setAnswer(String answer){
        answers.add(answer);
    }

    public void setCorrectAnswer(String correctAnswer){
        this.correctAnswer=correctAnswer;
    }

    public void setQuestionImage(){   //Not supported in version 1
        questionImage=null;
    }

    public String getCategory(){
        return category;
    }

    public String getQuestion(){
        return question;
    }

    public ArrayList<String> getAnswers(){
        return answers;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }
}

