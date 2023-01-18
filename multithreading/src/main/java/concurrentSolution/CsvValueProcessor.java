package concurrentSolution;
/**
 * @author divyadharshinimuruganandham  nikethaanand
 */
import java.util.Objects;

/**
 * class CsvValueProcessor
 */
public class CsvValueProcessor {

  private int dateField;

  private int numberOfClicks;
  private String course;

  /**CsvValueProcessor constructor
   * @param course course
   * @param dateField dateField
   * @param numberOfClicks numberOfClicks
   */
  public CsvValueProcessor(String course, int dateField, int numberOfClicks){
    this.course = course;
    this.dateField = dateField;
    this.numberOfClicks = numberOfClicks;
  }

  /**
   * constructor
   */
  public CsvValueProcessor(){
  }

  /**
   *
   * @return dateField
   */
  public int getDateField() {
    return dateField;
  }

  /**
   *
   * @return numberOfClicks
   */
  public int getNumberOfClicks() {
    return numberOfClicks;
  }

  /**
   *
   * @return course
   */
  public String getCourse() {
    return course;
  }

  @Override
  public String toString() {
    return "CsvValueProcessor{" +
        "dateField=" + dateField +
        ", numberOfClicks=" + numberOfClicks +
        ", course='" + course + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CsvValueProcessor that = (CsvValueProcessor) o;
    return Objects.equals(dateField,that.dateField) && Objects.equals(numberOfClicks,that.numberOfClicks)
        && Objects.equals(course, that.course);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateField, numberOfClicks, course);
  }
}
