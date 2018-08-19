package askmejava.service;

import askmejava.model.Question;
import com.spotify.apollo.Environment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetQuestionService {

  private List<Question> questions;

  public GetQuestionService(String filePath) {
    this.questions = loadQuestions(filePath);
  }

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

  public String getQuestion() {
    Random rand = new Random();

    int randomNumber = rand.nextInt(questions.size());
    String question = questions.get(randomNumber).getQuestion();
    System.out.println(question);
    return question;
  }
}
