package concurrentSolution;
/**
 * @author divyadharshinimuruganandham  nikethaanand
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * class ConsumerCsv
 */
public class ConsumerCsv extends Thread{

  private BuildThread threadPool;
  private int threshold;
  private String output;
  private Map<String, Map<Integer, Integer>> csvData;

  /**
   * @param threadPool threadPool
   * @param threshold threshold
   * @param output output
   */
  public ConsumerCsv(BuildThread threadPool, int threshold, String output) {
    this.threadPool = threadPool;
    this.output = output;
    this.threshold = threshold;
    this.csvData = new HashMap<>();
  }
  /**
   * constructor
   */
  public ConsumerCsv(){

  }
  /**
   * run method created a thread and calls the OutputGenerator which writes files
   */
  @Override
  public void run(){
    while(true){
      CsvValueProcessor record = threadPool.getFromBuffer();
      if(record == null) {
        new Thread(new OutputGenerator(csvData,output,threshold)).start();
        break;
      }
      Map<Integer, Integer> course = csvData.getOrDefault(record.getCourse(), new HashMap<>());
      course.put(record.getDateField(),course.getOrDefault(record.getDateField(),0) + record.getNumberOfClicks());
      csvData.put(record.getCourse(),course);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConsumerCsv that = (ConsumerCsv) o;

    return Objects.equals(threshold,that.threshold) && Objects.equals(output, that.output) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(threadPool, threshold, output, csvData);
  }
}
