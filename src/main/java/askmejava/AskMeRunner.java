package askmejava;

import askmejava.service.GetQuestionService;
import com.spotify.apollo.Environment;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.spotify.apollo.route.Route;


public class AskMeRunner {

  public static final String SERVICE_NAME = "askme";

  public static void main(String[] args) throws LoadingException {
    String filePath = args[0];
    HttpService.boot(environment -> AskMeRunner.init(environment, filePath), SERVICE_NAME, args);
  }

  static void init(Environment environment, String filePath) {
    GetQuestionService getQuestionService = new GetQuestionService(filePath);
    environment.routingEngine().registerAutoRoute(
        Route.sync("GET", "/healthcheck", requestContext -> "'I'm still alive' - Eddie Vedder"));
    environment.routingEngine().registerAutoRoute(
        Route.sync("GET", "/question", requestContext -> getQuestionService.getQuestion()));
  }


}
