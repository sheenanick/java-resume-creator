import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Month;
import java.text.DateFormatSymbols;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static LocalDate localDate(String startDate) {
  int year = Integer.parseInt(startDate.substring(0,4));
  int month = Integer.parseInt(startDate.substring(5,7));
  int day = Integer.parseInt(startDate.substring(8));
  String stringMonth = new DateFormatSymbols().getMonths()[month-1].toUpperCase();
  LocalDate test = LocalDate.of(year, Month.valueOf(stringMonth), day);
  return test;
  }
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();
     model.put("jobs", request.session().attribute("jobs"));
     model.put("name", request.session().attribute("name"));
     model.put("template", "templates/index.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());


    post("/jobs", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();

    ArrayList<Job> jobs = request.session().attribute("jobs");
    if (jobs == null) {
      jobs = new ArrayList<Job>();
      request.session().attribute("jobs", jobs);
    }

    String name = request.queryParams("name");
    String title = request.queryParams("title");
    String company = request.queryParams("company");
    String location = request.queryParams("location");
    String description = request.queryParams("description");
    String startDate = request.queryParams("startDate");
    String endDate = request.queryParams("endDate");

    LocalDate realStartDate = localDate(startDate);
    LocalDate realEndDate = localDate(endDate);

    request.session().attribute("name", name);

    Job newJob = new Job(title,company,location,description,realStartDate,realEndDate);
    jobs.add(newJob);
    model.put("template", "templates/success.vtl");
    return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

  }
}
