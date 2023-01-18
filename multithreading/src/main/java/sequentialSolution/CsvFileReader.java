package sequentialSolution;
/**
 * @author divyadharshinimuruganandham  nikethaanand
 */
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * class CsvFileReader
 */
public class CsvFileReader {
    Map<String, Map<Integer, Integer>> csvMap =new HashMap<String, Map<Integer, Integer>>();
    File inputCourse;
    File inputStudent;

    /**Constructor
     *
     */
    public CsvFileReader() {

    }

    /** CsvReader reads the csv files and returns them as a map
     * @param inputCourse inputcourse file name
     * @param inputStudent input student filename
     * @return map
     * @throws IOException IOException
     */
    Map<String, Map<Integer, Integer>> CsvReader(File inputCourse, File inputStudent) throws IOException , FileNotFoundException{
        BufferedReader course = new BufferedReader(new FileReader(inputCourse));
        String line="";String cvsSplitBy=",";
        line = course.readLine();
        while ((line = course.readLine()) != null) {
            String[] cols = line.split(cvsSplitBy);
            String t=cols[0].substring(1,cols[0].length() - 1)+"_"+cols[1].substring(1,cols[1].length() - 1);
            csvMap.put(t,new HashMap<Integer,Integer>());
        }
        input(inputStudent);
        return csvMap;
    }

    /** input reads the student file generates the file title name and adds values to the map
     * @param inputStudent input student file
     * @throws IOException IOException
     */
    void input(File inputStudent) throws IOException
    {
        Map<Integer,Integer> map = new HashMap<>();

        BufferedReader student = new BufferedReader(new FileReader(inputStudent));
        String line1="";String cvsSplitBy=",";
        line1 = student.readLine();

        while ((line1 = student.readLine()) != null) {
            String[] cols1 = line1.split(cvsSplitBy);
            String t=cols1[4]+"_"+cols1[5];

            String key = cols1[0]+"_"+cols1[1];
            map =csvMap.get(key);
            int date = Integer.parseInt(cols1[4]);
            map.put(Integer.parseInt(cols1[4]),map.getOrDefault(date,0)+Integer.parseInt(cols1[5]));
        }

    }

    /**CsvFileReader constructor
     *
     * @param inputCourse inputCourse
     * @param inputStudent inputStudent
     */
    CsvFileReader(File inputCourse, File inputStudent){
        this.inputCourse=inputCourse;
        this.inputStudent=inputStudent;
    }

    /**getCsvMap returns map
     *
     * @return Map
     */
    public Map<String, Map<Integer, Integer>> getCsvMap() {
        return csvMap;
    }

    @Override
    public String toString() {
        return "CsvFileReader{" +
            "csvMap=" + csvMap +
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
        CsvFileReader that = (CsvFileReader) o;
        return Objects.equals(csvMap, that.csvMap) && Objects.equals(inputCourse,
            that.inputCourse) && Objects.equals(inputStudent, that.inputStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(csvMap, inputCourse, inputStudent);
    }
}



