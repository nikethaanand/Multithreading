package concurrentSolution;
/**
 * @author divyadharshinimuruganandham  nikethaanand
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Exceptions.IllegalArgumentException;

/**
 * class ConcurrentMain
 */
public class ConcurrentMain {
  private static String inputStudent = "studentVle.csv";

  /**Main method of concurrent which has to be run by the user.The user enters the input and output
   * directory along with the threshold value in args.Producer and consumer threads are created and
   * started.The producer is the readCsv and the consumer is the write csv file.
   *
   * @param args takes threshold value as args
   * @throws IOException IOException
   * @throws IllegalArgumentException IllegalArgumentException
   * @throws InterruptedException InterruptedException
   */
  public static void main(String[] args) throws IOException, IllegalArgumentException, InterruptedException {
    File studentFile = null;
    int threshold=0;

    Scanner sc = new Scanner(System.in);
    if (args.length  == 0) {
      threshold = 0;
    }
    else {
      threshold = Integer.parseInt(args[0]);
    }
    System.out.println("Given Threshold Value is :" + threshold);

    System.out.println("Enter CSV File Directory Name");
    String inputDirectory=sc.nextLine();
    File filePath = new File((new File("").getAbsolutePath()+("/"+inputDirectory)));
    System.out.println(filePath);
    System.out.println("Please enter the output directory: ");
    Scanner outputDirectory = new Scanner(System.in);
    String outputDir = outputDirectory.nextLine().trim();

    if(inputDirectory == null || inputDirectory.equals("")) {
       throw new IllegalArgumentException("Enter Valid File Directory");
    }
    File[] fileListArray = filePath.listFiles();
    for(int i=1;i<fileListArray.length;i++) {
      String fileName = fileListArray[i].getName();
      if(fileName.equals(inputStudent))
      {
        studentFile=fileListArray[i];
      }
    }

    System.out.println("Printing StudentFile: "+studentFile);
    BuildThread createRecord = new BuildThread();
    ProducerCsv producer = new ProducerCsv(createRecord,studentFile);
    ConsumerCsv consumer = new ConsumerCsv(createRecord, threshold, outputDir); //

    producer.start();
    consumer.start();
    producer.join();
    consumer.join();
  }

  @Override
  public String toString() {
    return "Main{}";
  }
}
