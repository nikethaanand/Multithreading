package sequentialSolution;

/**
 * @author divyadharshinimuruganandham  nikethaanand
 */

import Exceptions.IllegalArgumentException;
import java.util.Map;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * class SequentialOutputGenerator
 */
public class SequentialOutputGenerator {

  private int NumberOfColumns = 2;

  private Map<String, Map<String, Integer>> parsedCsvData;

  private String fileFormatExtension = ".csv";

  /**Constructor
   *
   */
  public SequentialOutputGenerator(){

  }

  /** resultGenerator map is loaded and the output filename is passed to the write function to create file
   *
   * @param parsedCsvData map
   * @param getOutputDirectory output directory name
   * @throws IllegalArgumentException IllegalArgumentException
   */
  public void resultGenerator(Map<String, Map<Integer, Integer>> parsedCsvData, String getOutputDirectory) throws IllegalArgumentException {
    if(parsedCsvData.isEmpty()){
      String exceptionMessage ="Input .json file cannot be Empty";
      throw  new IllegalArgumentException (exceptionMessage);
    }

      try {
      File createDirectoryName =  new File(getOutputDirectory);
      createDirectoryName.mkdir();
      for(String row: parsedCsvData.keySet()){
        //System.out.println("0....."+row);
        Map<Integer,Integer> generateRow = parsedCsvData.get(row);
        //System.out.println("1....."+generateRow.toString());
        List<String[]> columnHeader = new ArrayList<>();
        columnHeader.add(new String[] { "Date","Count" });
        String outputFileName = row+ fileFormatExtension;
        File result = new File(createDirectoryName,outputFileName);
        FileWriter write = new FileWriter(result);
        write(generateRow, columnHeader, write, NumberOfColumns);
      }
    }
    catch ( IOException e) {
      e.printStackTrace();
    }
  }

  /**write gets the map contents fetches column values and writes them to a new file
   * @param writeRow writeRow
   * @param contents contents
   * @param result result
   * @param numberOfColumns numberOfColumns
   * @throws IOException IOException
   */
  public void write(Map<Integer, Integer> writeRow, List<String[]> contents, FileWriter result, int numberOfColumns) throws IOException {

    CSVWriter writer = new CSVWriter(result);
    for (Integer date : writeRow.keySet()) {
      String[] column = new String[numberOfColumns];
      column[0] = String.valueOf(date);
      column[1] = String.valueOf(writeRow.get(date));
      contents.add(column);
    }
    writer.writeAll(contents);
    writer.close();
  }

  @Override
  public String toString() {
    return "SequentialOutputGenerator{" +
        "parsedCsvData=" + parsedCsvData +
        '}';
  }

  /** getParsedCsvData method that returns map
   *
   * @return Map
   */
  public Map<String, Map<String, Integer>> getParsedCsvData() {
    return parsedCsvData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SequentialOutputGenerator that = (SequentialOutputGenerator) o;
    return Objects.equals(parsedCsvData, that.parsedCsvData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parsedCsvData);
  }
}
