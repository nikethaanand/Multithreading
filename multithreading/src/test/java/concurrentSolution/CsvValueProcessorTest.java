package concurrentSolution;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

class CsvValueProcessorTest {

  private String course = "BBB_2013B";

  private int numberOfClicks = 10;
  private int dateField = 4;
  private CsvValueProcessor value;
  private CsvValueProcessor value1;

  @BeforeEach
  void setUp() {
    value = new CsvValueProcessor(course, dateField, numberOfClicks);
    value1 = new CsvValueProcessor(course, dateField, numberOfClicks);

  }

  @Test
  void getDateField() {
    Assertions.assertEquals(value.getDateField(), this.dateField);
  }

  @Test
  void getNumberOfClicks() {
    Assertions.assertEquals(value.getNumberOfClicks(), this.numberOfClicks);
  }

  @Test
  void getCourse() {
    Assertions.assertEquals(value.getCourse(), this.course);
  }

  @Test
  void testToString() {
    assertEquals("CsvValueProcessor{" +
        "dateField=" + dateField +
        ", numberOfClicks=" + numberOfClicks +
        ", course='" + course + '\'' +
        '}',value.toString());
  }

  @Test
  void testEquals() {
    assertTrue(value.equals(value));
    assertFalse(value.equals(null));
    assertTrue(value.equals(value1));
    assertFalse(value.equals(new CsvValueProcessor()));
  }

  @Test
  void testHashCode() {
    assertEquals(Objects.hashCode(value),value.hashCode());
  }
}