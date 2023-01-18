# Multithreading



Sequential Solution

The entry point of Sequential is the SequentialMain.java which can directly be run.On running the user needs to enter the csv File directory
name which has the CsvFiles followed by the output directory name. The csvFileReader class is called to load the csvFile using buffer and 
returned to a map.This map is then passed to the sequantialOutputGenerator. The sequantialOutputGenerator has a resultgenerator function and a
write function which writes files in the output directory entered by the user.

Input:
Enter directory name which contains OULAD dataset:
Dataset
Enter CSV Output Directory:
sequentialSolutionOutput
-- The desired output will be generated in the given output folder

Concurrent Solution

The entry point of the concurrent is concurrentMain, which receives threshold from the using using args.Incase the user does not enter 
threshold by default it is assigned to zero.The user next has to enter the csv File directory name followed by the output directory name.
Producer thread is used for csv processing and the consumer thread is used for writing csvFiles based on the threshold entered by the user.

Input : Threshold is given as args
Given Threshold Value is : given threshold value will be displayed for example if given threshold 20
Enter CSV File Directory Name:
Dataset
Please enter the output directory: 
concurrentSolutionOutput
-- The desired output will be generated as activity-20.csv is generated (number of clicks >=20)

