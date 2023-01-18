package sequentialSolution;

import static org.junit.jupiter.api.Assertions.*;

import Exceptions.IllegalArgumentException;
import concurrentSolution.OutputGenerator;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SequentialOutputGeneratorTest {
  private int NumberOfColumns;

  private Map<String, Map<Integer, Integer>> parsedCsvData;

  private Map<String, Map<Integer, Integer>> csvData;

  private String fileFormatExtension;

  private  String outputDirectory;

  private  File makeDirectory;

  private File filePath;

  private static String inputCourse = "courses.csv";

  private static String inputStudent = "studentVle.csv";

  private SequentialOutputGenerator value;
  private SequentialOutputGenerator value2;

  @BeforeEach
  void setUp() throws IOException {

    value = new SequentialOutputGenerator();
    value2 = new SequentialOutputGenerator();
    this.NumberOfColumns = 2;
    this.fileFormatExtension = ".csv";
    this.outputDirectory = "src/test/resources/sequentialOutput";
    this.makeDirectory = new File(outputDirectory);
    makeDirectory.mkdir();
    File courseFile= null;
    File studentFile = null;
    this.csvData = new HashMap<>();
    this.filePath = new File("src/test/resources/testDataset");
    File[] fileListArray = filePath.listFiles();
    for(int i=1;i<fileListArray.length;i++) {
      String fileName = fileListArray[i].getName();
      if (fileName.equals(inputCourse)) {
        courseFile = fileListArray[i];
      } else if (fileName.equals(inputStudent)) {
        studentFile = fileListArray[i];
      }
    }
    CsvFileReader csv=new CsvFileReader(courseFile,studentFile);
    Map<String, Map<Integer, Integer>> csvData = csv.CsvReader(courseFile,studentFile);
    //System.out.println(csvData+"parsed");
    this.parsedCsvData = csvData;

  }

  @Test
  void resultGenerator() throws IllegalArgumentException {
    //System.out.println("parsedtest"+parsedCsvData);
    value.resultGenerator(parsedCsvData,outputDirectory);
  }

  @Test
  void testToString() {
    assertEquals("SequentialOutputGenerator{" +
        "parsedCsvData=" + value.getParsedCsvData()+
        '}',value.toString());
  }

  @Test
  void testEquals() {
    assertTrue(value.equals(value));
    assertFalse(value.equals(null));
    assertTrue(value.equals(value2));
  }

  @Test
  void testHashCode() {
    assertEquals(Objects.hashCode(value),value.hashCode());
  }

}