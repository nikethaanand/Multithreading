package sequentialSolution;
/**
 * @author divyadharshinimuruganandham  nikethaanand
 */
import Exceptions.IllegalArgumentException;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/** Main class which has to be run
 * class SequentialMain
 */
public class SequentialMain {
    private static String inputCourse = "courses.csv";
    private static String inputStudent = "studentVle.csv";

    /** Main method of sequential which receives csv file location and csv file output directory from
     * the user using scanner class.Csv file is processed and output is generated via output generator
     * and returned.
     * @param args args
     * @throws IOException IOException
     * @throws IllegalArgumentException IllegalArgumentException
     */

    public static void main(String[] args) throws IOException, IllegalArgumentException {
        File courseFile= null;
        File studentFile = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter directory name which contains OULAD dataset");
        String inputDirectory=sc.nextLine();
        File filePath = new File((new File("").getAbsolutePath()+("/"+inputDirectory)));
        File[] fileListArray = filePath.listFiles();
        try {
            if(fileListArray.length == 0) {
                throw new IllegalArgumentException("Enter valid directory name with valid datasets");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
        for(int i=1;i<fileListArray.length;i++) {
            String fileName = fileListArray[i].getName();
            if(fileName.equals(inputCourse))
            {
                courseFile=fileListArray[i];
            }
            else if(fileName.equals(inputStudent))
            {
                studentFile=fileListArray[i];
            }
        }
        CsvFileReader csv=new CsvFileReader(courseFile,studentFile);
        Map<String, Map<Integer, Integer>> csvMap= csv.CsvReader(courseFile,studentFile);
        System.out.println("Enter CSV Output Directory");
        String outputDirectory = sc.nextLine();
        SequentialOutputGenerator output = new SequentialOutputGenerator();
        System.out.println("csvMap"+csvMap);
        System.out.println("OutputDirectory"+outputDirectory);
        output.resultGenerator(csvMap, outputDirectory);
    }

    @Override
    public String toString() {
        return "Main{}";
    }
}




