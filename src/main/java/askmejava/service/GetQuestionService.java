package askmejava.service;

import askmejava.model.Question;
import com.spotify.apollo.Environment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetQuestionService {

  private static List<Question> loadQuestions(String filePath) {
    // TODO : Handle exceptions properly
    List<Question> questions = new ArrayList<Question>();
    FileReader in;
    try {
      in = new FileReader(new File(filePath));
      BufferedReader bufferIn = new BufferedReader(in);

      String quizQuestion;
      while ((quizQuestion = bufferIn.readLine()) != null) {
        Question q = new Question();
        q.setQuestion(quizQuestion);
        questions.add(q);
      }
      bufferIn.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return questions;
  }

  private boolean appendQuestion(String question, String category){
    Writer output;
    try {
      output = new BufferedWriter(new FileWriter("quiz/" + category + ".txt", true));
      output.append(question);
      output.close();
    } catch (IOException e) {
      //TODO
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public String getQuestionFromCategory(String category) {
    List<Question> questions = getCategory(category);
    Random rand = new Random();
    int randomNumber = rand.nextInt(questions.size());
    String question = questions.get(randomNumber).getQuestion();
    return question;
  }

  public List<Question> getCategory(String category) {
    // TODO : check file exists
    List<Question> questions = loadQuestions("quiz/" + category + ".txt");
    return questions;
  }

  public boolean appendQuestionToCategory(String category, String question) {
    // TODO : check file exists
    return appendQuestion(question, category);
  }
}

