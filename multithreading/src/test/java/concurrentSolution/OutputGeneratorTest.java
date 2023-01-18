package concurrentSolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputGeneratorTest {

  private  int threshold;
  private OutputGenerator w1;
  private OutputGenerator w2;
  private OutputGenerator w3;
  private int threshold1;
  private int threshold2;
  private  String outputDirectory;
  private  File makeDirectory;
  private Map<String, Map<Integer, Integer>> csvData;

  @BeforeEach
  void setUp() {

    this.threshold = 1;
    this.threshold1 = 1;
    this.threshold2 = 0;
    this.csvData = new HashMap<>();
    this.outputDirectory = "src/test/resources/testOutputGenerator";
    this.makeDirectory = new File(outputDirectory);
    makeDirectory.mkdir();
    Map<Integer, Integer> row = new HashMap<>();
    row.put(2,2);
    row.put(9,3);
    csvData.put("BBB_2013B",row );
    w1 = new OutputGenerator(csvData,outputDirectory,threshold);
    w2 = new OutputGenerator(csvData,outputDirectory,threshold1);
    w3 = new OutputGenerator(csvData,outputDirectory,threshold2);

  }

  @Test
  void run() {
    w1.run();
    w2.run();
    w3.run();
  }

  @Test
  void testEquals() {
    assertTrue(w1.equals(w1));
    assertFalse(w1.equals(null));
    assertTrue(w1.equals(w2));
  }

  @Test
  void testHashCode() {
    assertEquals(Objects.hashCode(w1),w1.hashCode());
  }

}
