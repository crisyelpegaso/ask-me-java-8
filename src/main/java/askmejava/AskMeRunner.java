package askmejava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import askmejava.model.Question;


public class AskMeRunner {

  private static final String FILENAME = "quiz.txt";

  private static int randomNumber;

  private static List<Question> questions = new ArrayList<Question>();

  public static void main(String[] args) {

    AskMeRunner.loadQuestions(args[0]);

    getRandomQuestion();
  }

  private static void getRandomQuestion() {
    Random rand = new Random();
    AskMeRunner.randomNumber = rand.nextInt(questions.size());
    System.out.println(questions.get(randomNumber).getQuestion());
  }

  private static void loadQuestions(String filePath) {
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
  }

}
