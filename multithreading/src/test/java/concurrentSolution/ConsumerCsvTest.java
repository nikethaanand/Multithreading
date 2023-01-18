package concurrentSolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

class ConsumerCsvTest {

  private BuildThread threadPool;
  private int thresholdValue;
  private String outputDirectory;
  private Map<String, Map<Integer, Integer>> csvData;
  private ConsumerCsv output1;
  private ConsumerCsv output2;
  private final static String testFileName = "src/test/resources/testValue.csv";

  @BeforeEach
  void setUp() throws FileNotFoundException{

    threadPool = new BuildThread();
    this.output1 = new ConsumerCsv();
    this.output2 = new ConsumerCsv();
    thresholdValue = 0;
    outputDirectory = "src/test/resources/generatedResult";
    csvData = new HashMap<>();
    File truncatedFile = new File(testFileName);
    ProducerCsv rf = new ProducerCsv(threadPool,truncatedFile);
    rf.run();
  }


  @Test
  void run() {
    output1 = new ConsumerCsv(threadPool, thresholdValue, outputDirectory);
    output1.run();
    File fileExist = new File(outputDirectory, "activity-0.csv");
    Assertions.assertTrue(fileExist.exists());
  }

  @Test
  void testEquals() {
    assertTrue(output1.equals(output1));
    assertFalse(output1.equals(null));
    assertTrue(output1.equals(output2));
    assertTrue(output1.equals(new ConsumerCsv()));

  }

  @Test
  void testHashCode() {
    assertEquals(Objects.hashCode(output1),output1.hashCode());
  }
}