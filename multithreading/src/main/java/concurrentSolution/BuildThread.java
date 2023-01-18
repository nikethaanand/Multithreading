package concurrentSolution;
/**
 * @author divyadharshinimuruganandham  nikethaanand
 */
import java.util.Arrays;
import java.util.Objects;

/**
 * class BuildThread
 */
public class BuildThread {

  private CsvValueProcessor[] inputData;
  private int capacity = 1000;
  private int initialValue;
  private boolean stop;

  /**BuildThread constructor
   */
  public BuildThread() {
    this.inputData = new CsvValueProcessor[capacity];
    this.initialValue = 0;
    this.stop = false;
  }

  /**synchronized method putIntoBuffer
   * adds each record into the buffer
   * @param item item
   */
  public synchronized void putIntoBuffer(CsvValueProcessor item){
    while(initialValue >= inputData.length){
      try{
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    inputData[initialValue] = item;
    initialValue++;
    this.notifyAll();
  }

  /**synchronized method getFromBuffer
   * gets record from the buffer
   * @return fileData
   */
  public synchronized CsvValueProcessor getFromBuffer(){
    while(initialValue <= 0){

      if(this.isStop()) {
        notifyAll();
        return null;
      }

      try{
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    initialValue--;

    CsvValueProcessor fileData = inputData[initialValue];
    notifyAll();
    return fileData;
  }

  /**
   * @return boolean
   */
  public boolean isStop() {
    return stop;
  }

  /**
   * sets stop value
   * @param stop stop
   */
  public synchronized void setStop(boolean stop) {
    this.stop = stop;
    this.notifyAll();
  }

  /**
   *
   * @return capacity
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   *
   * @return  initialValue
   */
  public int getInitialValue() {
    return initialValue;
  }

  /**
   *
   * @return inputData
   */
  public CsvValueProcessor[] getInputData() {
    return inputData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BuildThread that = (BuildThread) o;
    return Arrays.equals(inputData, that.inputData);

  }

  @Override
  public int hashCode() {
    int result = Objects.hash(capacity, initialValue, stop);
    result = 31 * result + Arrays.hashCode(inputData);
    return result;
  }

  @Override
  public String toString() {
    return "BuildThread{" +
        "capacity=" + capacity +
        "initialValue=" + initialValue+ '}';
  }

}
