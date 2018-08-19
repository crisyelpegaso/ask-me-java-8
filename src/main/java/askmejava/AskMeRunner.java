package askmejava;

import askmejava.service.GetQuestionService;
import com.spotify.apollo.Environment;
import com.spotify.apollo.RequestContext;
import com.spotify.apollo.Status;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.spotify.apollo.route.Route;
import java.util.Optional;


public class AskMeRunner {

  public static final String SERVICE_NAME = "askme";

  public static void main(String[] args) throws LoadingException {
    HttpService.boot(AskMeRunner::init, SERVICE_NAME, args);
  }

  static void init(Environment environment) {
    GetQuestionService getQuestionService = new GetQuestionService();
    environment.routingEngine().registerAutoRoute(
        Route.sync("GET", "/healthcheck", requestContext -> "'I'm still alive' - Eddie Vedder"));
    environment.routingEngine().registerAutoRoute(
        Route.sync("GET", "/question/<category>", requestContext ->
            getQuestionService.getQuestionFromCategory(requestContext.pathArgs().get("category"))));
    environment.routingEngine().registerAutoRoute(
        Route.sync("GET", "/category/<category>", requestContext -> getQuestionService
            .getCategory(requestContext.pathArgs().get("category"))));
    environment.routingEngine().registerAutoRoute(
        Route.sync("PUT", "/category/<category>", requestContext -> putQuestion(requestContext, getQuestionService)));
  }

  private static Status putQuestion(RequestContext requestContext, GetQuestionService questionService) {
    String category = requestContext.pathArgs().get("category");
    // TODO : check + move to body + return value
    Optional<String> question = requestContext.request().parameter("question");
    boolean result = questionService.appendQuestionToCategory(category, question.get());
    if (result) {
      return Status.CREATED;
    }
    return Status.INTERNAL_SERVER_ERROR;
  }


}
