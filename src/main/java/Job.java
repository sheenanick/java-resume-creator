import java.time.LocalDate;
import java.time.Period;

public class Job {
  private String mTitle;
  private String mCompany;
  private String mLocation;
  private String mDescription;
  private LocalDate mStartDate;
  private LocalDate mEndDate;

  public Job(String title, String company, String location, String description, LocalDate startDate, LocalDate endDate) {
    mTitle = title;
    mCompany = company;
    mLocation = location;
    mDescription = description;
    mStartDate = startDate;
    mEndDate = endDate;
  }

  public String getTitle() {
    return mTitle;
  }
  public String getCompany() {
    return mCompany;
  }
  public String getLocation() {
    return mLocation;
  }
  public String getDescription() {
    return mDescription;
  }
  public LocalDate getStartDate() {
    return mStartDate;
  }
  public LocalDate getEndDate() {
    return mEndDate;
  }
  public String timeLapse(LocalDate startDate, LocalDate endDate) {
    Period p = Period.between(startDate, endDate);
    return "You worked for " + p.getYears() + " years, " + p.getMonths() + " months, and " + p.getDays() + " days" ;
  }

}
