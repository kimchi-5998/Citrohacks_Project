package EveryComponent;
import java.util.*;
import java.io.*;

class Project{
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner user = new Scanner(System.in);  // For user inputs
        String fileName = "PeopleData.csv"; // Only 1 file to read and write into
        File file = new File(fileName); // Making the file variable
        Scanner scan = new Scanner(file);   // Scanner that scans the file

        String[] data = new String[4];  // 4 different types of data in one array
        int lineCount = 0;  // Initializing line count variable
        String[] comparedData = new String[lineCount];  // Array that has the info of other people and also the number of similarities you have with them
        int similarCount = 0;   // Initializing the similar count variable

        // Asking the user multiple questions about themselves
        System.out.println("What is your name?");
        String name = user.nextLine();

        System.out.println("What topic are you studying? (Answer in lowercase)");
        String subject = user.nextLine();

        System.out.println("How old are you?");
        int age = user.nextInt();
        String ageStr = Integer.toString(age);  // Changing the integer to a string so it can be stored in the data array
        
        System.out.println("What is your first language? (Answer in lowercase)");
        String language = user.nextLine();
        language = user.nextLine();
        
        if (scan.hasNextLine() == false) {  // If there is nothing in the file
            addData(name, subject, ageStr, language, fileName); // Add the person's data
            System.out.println("Sorry, there is no other data at the moment");  // Say sorry that there is no other info
        }

        else if (scan.hasNextLine()) {  // If there are lines of data
            lineCount = lineCount(fileName);    // Runs the lineCount method and stores the value in the line count integer initialized earlier
            for (int i = 0; i < lineCount; i++) {   // Runs a for loop as many times as there are lines
                data = combiningMethods(fileName);  // Run the combiningMethods method which stores data points in the data array
                for (int j = 0; j < 3; j++) {   // Runs another for loop inside the for loop that compares the data points to the ones entered by the user currently
                    if (subject.equals(data[1]) || ageStr.equals(data[2]) || language.equals(data[3])) {
                        similarCount++; // Increases the similarCount by 1 if any one of 3 conditionals are met
                    }
                }
                comparedData[i] = similarCount + ", " + data[0];    // Stores the number of similarities and name of person that was compared into an array
            }
            addData(name, subject, ageStr, language, fileName); // Adds the person that was using the programs, info
        }

        order(comparedData);    // Sorts the compared data from least to most similarities
        printArray(comparedData);   // Prints the array order backwars so that the people with the most similarities are put on top

        // Closing scanners
        scan.close();
        user.close();
    }

    /*
     * Method that writes into a csv file the data that the user inputs about themselves
     * @return - no return
     */
    public static void addData(String name, String subject, String age, String language, String fileName) {
        try {
            // Needed to be able to write into a file
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            // Prints into a csv file in this format
            pw.println(name + "," + subject + "," + age + "," + language);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    /*
     * Method that scans and stores lines in a string
     * @return - String line
     */
    public static String line(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        String line = scan.nextLine();  // Stores the next line in the file
        scan.close();
        return line;
    }

    /*
     * Method that finds commas in the line that separates the data points
     * @return - position of comma in integer form
     */
    public static int findComma(String line) throws FileNotFoundException {
        int charCount = line.length();  // Gets length of string
        for (int i = 0; i < charCount; i++) {   // Runs the for loop for as many characters there are
            if (line.charAt(i) == ',') {    // If the character at point i is a comma, it breaks and returns the number comma was at
                return i;
            }
        }
        return 0;   // If no comma is found, return 0
    }

    /*
     * Method that returns the name of previous users. Essentially same method as subtractName
     * @return - String datapoint
     */
    public static String datapoint(String line, int comma) throws FileNotFoundException {
        String datapoint = line.substring(0, comma); // Substrings to find datapoint
        return datapoint;
    }

    /*
     * Essentially same method as name, returns different value
     * @returns - String leftovers after datapoint was taken out
     */
    public static String leftoverData(String line, int comma) throws FileNotFoundException {
        int lineLength = line.length(); // Gets length of the line
        String notName = line.substring(comma + 1, lineLength); // Substrings the leftover data
        return notName;
    }

    /*
     * Stores data points in a String array
     * @return String[] data
     */
    public static String[] combiningMethods(String fileName) throws FileNotFoundException {
        String[] data = new String[4];  // 4 data points per person
        String line = line(fileName);   // Run line method to get the initial string
        int comma = 0;  // Initialize the comma int

        for (int i = 0; i < 3; i++) {   // Runs for loop 3 times
            comma = findComma(line);    // Finds comma using method
            data[i] = datapoint(line, comma);   // Get the data points corresponding to the number of times the for loop ran
            line = leftoverData(line, comma);   // Get the leftover data each time
        }
        data[3] = line; // Stores the final leftover in the final spot
        return data;
    }

    /*
     * Counts lines in the file
     * @return int linecount
     */
    public static int lineCount(String fileName) throws FileNotFoundException {
        int lineCount = 0;
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) { // If there is a next line
            lineCount++;    // Count increases by 1
        }
        scan.close();
        return lineCount;
    }

    /* 
     * Sorts the arrays
     * @return - no return
     */
    public static void order(String[] arr) {
        String tempStr;
        int temp;
        int arrLength = arr.length;
        int[] num = new int[arrLength];

        for (int i = 0; i < arrLength; i++) {   // For each line of data
            String x = arr[i].substring(0, 1);      // get the number of similarities
            int xnum = Integer.parseInt(x); // Turn that into an int
            num[i] = xnum;  // Store it in an array
        }

        for (int i = 0; i < arr.length - 1; i++) {  
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (num[j] > num[j+1]) {
                    temp = num[j+1];    // Hold the right value in temp
                    tempStr = arr[j+1];
                    num[j+1] = num[j];  // Overwrite the right value with the left value
                    arr[j+1] = arr[j];
                    num[j] = temp;      // Replace the left value with the original right value
                    arr[j] = tempStr;
                }
            }
        }
    }

    /*
     * Prints the top 5 people with the most similarities
     * @return - no return
     */
    public static void printArray(String[] arr) {
        System.out.println("People that match the best with you...");
        int max = arr.length;
        for (int i = max; i >= max - 5; i--) { // For loop that prints 5 people
            System.out.println(arr[i]);
        }
    }
}