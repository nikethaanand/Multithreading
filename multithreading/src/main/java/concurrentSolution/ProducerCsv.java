package concurrentSolution;
/**
 * @author divyadharshinimuruganandham  nikethaanand
 */
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * class ProducerCsv
 */
public class ProducerCsv extends Thread{

  CSVReader csvReader;
  private FileReader csvFileReader;
  private BuildThread threadPool;

  /**ProducerCsv constructor that assigns values and reads csvfile data using CSVReader
   *
   * @param threadPool threadPool
   * @param studentFile studentFile name
   * @throws FileNotFoundException FileNotFoundException
   */
  public ProducerCsv(BuildThread threadPool, File studentFile)  throws FileNotFoundException {
    this.threadPool = threadPool;
    this.csvFileReader = new FileReader(studentFile);
    this.csvReader =  new CSVReaderBuilder(csvFileReader).withSkipLines(1).build();
  }

  /**
   * run method,calls the CsvValueProcessor class,which in turns processes the csv file
   */
  @Override
  public void run(){
    String[] iterateItem;
    try{
      while ((iterateItem = csvReader.readNext()) != null) {
        int dateField = Integer.parseInt(iterateItem[4]);
        String course = iterateItem[0]+"_"+iterateItem[1];
        int numberOfClicks = Integer.parseInt(iterateItem[5]);
        CsvValueProcessor record = new CsvValueProcessor(course, dateField, numberOfClicks);
        threadPool.putIntoBuffer(record);

      }
      threadPool.setStop(true);
    }
    catch (CsvValidationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    return "ReadCsv{" +
        "csvReader=" + csvReader +
        ", csvFileReader=" + csvFileReader +
        ", threadPool=" + threadPool +
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
    ProducerCsv readCsv = (ProducerCsv) o;
    return Objects.equals(csvReader, readCsv.csvReader) && Objects.equals(
        csvFileReader, readCsv.csvFileReader) && Objects.equals(threadPool,
        readCsv.threadPool);
  }

  @Override
  public int hashCode() {
    return Objects.hash(csvReader, csvFileReader, threadPool);
  }

}
