package concurrentSolution;
/**
 * @author divyadharshinimuruganandham  nikethaanand
 */
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * class OutputGenerator
 */
public class OutputGenerator implements Runnable{

  private  int threshold;

  private  String outputDirectory;

  private  File makeDirectory;

  private int headerCount = 3;

  private Map<String, Map<Integer, Integer>> csvData;

  private String[] outputHeaderValue = {"Course", "Date", "Count"};

  private String fileExtension = ".csv";

  private String fileTitle = "activity-";

  /**OutputGenerator constructor
   *
   * @param csvData csvData
   * @param output output
   * @param threshold threshold
   */
  public OutputGenerator (Map<String, Map<Integer, Integer>> csvData, String output, int threshold) {
    this.threshold = threshold;
    this.csvData = csvData;
    this.outputDirectory = output;
    this.makeDirectory = new File(outputDirectory);
    makeDirectory.mkdir();
  }
  /**
   * constructor
   */
  public OutputGenerator(){
  }

  /**
   * run method calls the generateThreshold function
   */
  @Override
  public void run() {

    try {
      generateThreshold(threshold);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  /**generateThreshold Takes the threshold and loads the filename to be created.It calls the writeThreshold
   * function which creates the csv files based on the threshold values
   * @param threshold threshold
   * @throws IOException IOException
   */
  private void generateThreshold(int threshold) throws IOException
  {
      FileWriter outputFile = null;
      List<String[]> headerListThreshold = new ArrayList<>();
      headerListThreshold.add(outputHeaderValue);
      List<String[]> headerListWithoutThreshold = new ArrayList<>();
      headerListWithoutThreshold.add(outputHeaderValue);
      String fileName = fileTitle + threshold + fileExtension;
      File file = new File(makeDirectory, fileName);
      outputFile = new FileWriter(file);
      writeThreshold(outputFile,threshold,headerListThreshold,headerListWithoutThreshold, headerCount);
  }

  /**writeThreshold is the write function.It takes all the column values from the file.Incase
   * the user has a threshold it adds values to the headerListThreshold else it adds values to the
   * headerListWithoutThreshold.Based on the flag value set the file is created and returned
   * @param outputFile outputFile
   * @param threshold threshold
   * @param headerListThreshold headerListThreshold
   * @param headerListWithoutThreshold headerListWithoutThreshold
   * @param headerCount headerCount
   * @throws IOException IOException
   */
  private void writeThreshold(FileWriter outputFile,int threshold,List<String[]> headerListThreshold,List<String[]> headerListWithoutThreshold,int headerCount)throws IOException
  {
    int flag = 0;
    CSVWriter writer = new CSVWriter(outputFile);
    for (String item : csvData.keySet())
    {
      Map<Integer, Integer> r =  csvData.get(item);
      for (Integer key: r.keySet())
      {
        int temp;
        String[] val = new String[headerCount];
        val[0] = item;
        val[1] = String.valueOf(key);
        val[2] = String.valueOf(r.get(key));
        temp=Integer.parseInt(val[2]);
        if(temp>=threshold) {
          headerListThreshold.add(val);
          flag=0;
        }
        else {
          headerListWithoutThreshold.add(val);
          flag=1;
        }
      }
    }
    if(flag==0) {
      writer.writeAll(headerListThreshold);
    }
    else {
      writer.writeAll(headerListWithoutThreshold);
    }
    writer.close();
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OutputGenerator that = (OutputGenerator) o;
    return Objects.equals(threshold, that.threshold);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(csvData, outputDirectory, threshold);
    result = 31 * result + Arrays.hashCode(outputHeaderValue);
    return result;
  }
}
