package EveryComponent;
import java.util.*;
import java.io.*;

class Test{
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner user = new Scanner(System.in);
        String fileName = "PeopleData.csv";
        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        String[] data = new String[4];
        int lineCount = 0;
        String[] comparedData = new String[lineCount];  //Array that has the info of other people and also the number of similarities you have with them
        int similarCount = 0;

        // Asking the user multiple questions about themselves
        System.out.println("What is your name?");
        String name = user.nextLine();

        System.out.println("What topic are you studying? (Answer in lowercase)");
        String subject = user.nextLine();

        System.out.println("How old are you?");
        int age = user.nextInt();
        String ageStr = Integer.toString(age);
        
        System.out.println("What is your first language? (Answer in lowercase)");
        String language = user.nextLine();
        language = user.nextLine();
        
        if (scan.hasNextLine() == false) {
            addData(name, subject, ageStr, language, fileName);
            System.out.println("Sorry, there is no other data at the moment");
        }

        else if (scan.hasNextLine()) {
            addData(name, subject, ageStr, language, fileName);
            lineCount = lineCount(fileName); 
            for (int i = 0; i < lineCount; i++) {
                data = combiningMethods(fileName);
                for (int j = 0; j < 3; j++) {
                    if (subject.equals(data[1]) || ageStr.equals(data[2]) || language.equals(data[3])) {
                        similarCount++;
                    }
                }
                comparedData[i] = similarCount + ", " + data[0];
            }
        }

        order(comparedData);
        printArray(comparedData);

        scan.close();
        user.close();
    }

    /*
     * Method that writes into a csv file the data that the user inputs about themselves
     */
    public static void addData(String name, String subject, String age, String language, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(name + "," + subject + "," + age + "," + language);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    /*
     * Method that scans and stores lines in a string
     */
    public static String line(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        String line = scan.nextLine();
        scan.close();
        return line;
    }

    /*
     * Method that finds commas in the line that separates the data points
     */
    public static int findComma(String line) throws FileNotFoundException {
        int charCount = line.length();
        for (int i = 0; i < charCount; i++) {
            if (line.charAt(i) == ',') {
                return i;
            }
        }
        return 0;
    }

    /*
     * Method that returns the name of previous users. Essentially same method as subtractName
     */
    public static String datapoint(String line, int comma) throws FileNotFoundException {
        String name = line.substring(0, comma);
        return name;
    }

    /*
     * Essentially same method as name, returns different value
     */
    public static String leftoverData(String line, int comma) throws FileNotFoundException {
        int lineLength = line.length();
        String notName = line.substring(comma + 1, lineLength);
        return notName;
    }

    public static String[] combiningMethods(String fileName) throws FileNotFoundException {
        String[] data = new String[4];
        String line = line(fileName);
        int comma = 0;

        for (int i = 0; i < 3; i++) {
            comma = findComma(line);
            data[i] = datapoint(line, comma);
            line = leftoverData(line, comma);
        }
        data[3] = line;
        return data;
    }

    public static int lineCount(String fileName) throws FileNotFoundException {
        int lineCount = 0;
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()) {
            lineCount++;
        }
        scan.close();
        return lineCount;
    }

    public static void order(String[] arr) {
        String tempStr;
        int temp;
        int arrLength = arr.length;
        int[] num = new int[arrLength];

        for (int i = 0; i < arrLength; i++) {
            String x = arr[i].substring(0, 1);
            int xnum = Integer.parseInt(x);
            num[i] = xnum;
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

    public static void printArray(String[] arr) {
        System.out.println("People that match the best with you...");
        int max = arr.length;
        for (int i = max; i >= max - 5; i--) {
            System.out.println(arr[i]);
        }
    }
}