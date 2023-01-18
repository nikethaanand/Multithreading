package sequentialSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
class CsvFileReaderTest {

 private static String inputCourse = "courses.csv";

 private static String inputStudent = "studentVle.csv";
 private File course = null;
 private File student = null;

 private File filePath;

 private CsvFileReader value;
 private CsvFileReader value2;


 @BeforeEach
 void setUp(){
  value = new CsvFileReader();
  value2 = new CsvFileReader();
  this.filePath = new File("src/test/resources/testDataset");
  File[] fileListArray = filePath.listFiles();
  for(int i=1;i<fileListArray.length;i++) {
   String fileName = fileListArray[i].getName();
   if (fileName.equals(inputCourse)) {
    course = fileListArray[i];
   } else if (fileName.equals(inputStudent)) {
    student = fileListArray[i];
   }
  }
 }

  @Test
  void csvReader() throws IOException {
   value.CsvReader(course,student);
 }

  @Test
  void testToString() {
   assertEquals("CsvFileReader{" +
       "csvMap=" + value.getCsvMap()+
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