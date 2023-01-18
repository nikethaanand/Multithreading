package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import com.opencsv.CSVReaderBuilder;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.util.Objects;

class BuildThreadTest {

  private CsvValueProcessor row1 = new CsvValueProcessor("AAA_2013J", 6, 10);
  private CsvValueProcessor row2 = new CsvValueProcessor("CCC_2013J", -6, 9);
  private CsvValueProcessor row3 = new CsvValueProcessor("CCC_2013J", -6, 2);
  private int capacity = 1000;
  private boolean stop = true;
  private BuildThread build1;
  private BuildThread build2;
  private BuildThread build3;

  @BeforeEach
  void setUp() {
    this.build1 = new BuildThread();
    this.build2 = new BuildThread();
    this.build3 = new BuildThread();
    build3.putIntoBuffer(row3);
  }


  @Test
  void putIntoBuffer() {
    build1.putIntoBuffer(row1);
    assertTrue(build1.getInputData()[0].equals(row1) );
  }

  @Test
  void getFromBuffer() {
    build2.putIntoBuffer(row2);
    CsvValueProcessor val = build2.getFromBuffer();
    assertEquals(val,row2);
  }

  @Test
  void isStop() {
    Boolean end = build1.isStop();
    Assertions.assertTrue(!end);
  }

  @Test
  void setStop() {
    build2.setStop(stop);
    Assertions.assertTrue(stop);
  }

  @Test
  void getInputData() {
    assertEquals(build3.getInputData()[0],row3);
  }

  @Test
  void testEquals() {

    assertTrue(build1.equals(build1));
    assertFalse(build1.equals(null));
    assertTrue(build1.equals(build2));
    assertTrue(build1.equals(new BuildThread()));

  }

  @Test
  void testHashCode() {
    assertEquals(Objects.hashCode(build1),build1.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("BuildThread{" +
        "capacity=" + build1.getCapacity() +
        "initialValue=" +  build1.getInitialValue() +
        '}',build1.toString());

  }
}