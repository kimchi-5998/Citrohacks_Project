package EveryComponent;
import java.util.*;
import java.io.*;

class Project{
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner user = new Scanner(System.in);
        String fileName = "PeopleData.csv";
        String[] data = new String[4];
        int count = 0;

        // Asking the user multiple questions about themselves
        System.out.println("What is your name?");
        String name = user.nextLine();

        System.out.println("What topic are you studying? (Answer in lowercase)");
        String subject = user.nextLine();

        System.out.println("How old are you?");
        int age = user.nextInt();
        String ageStr = Integer.toString(age);
        
        System.out.println("What is your first language?");
        String language = user.nextLine();
        language = user.nextLine();

        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        if (scan.hasNextLine() == false) {
            addData(name, subject, ageStr, language, fileName);
            System.out.println("Sorry, there is no other data at the moment");
        }
        
        else if (scan.hasNextLine()) {
            addData(name, subject, ageStr, language, fileName);
            while(scan.hasNextLine()) {
                data = combiningMethods(fileName);
                if (subject.equals(data[1])) {
                    count =+ 1;
                }
                else if (ageStr.equals(data[2])) {
                    count =+ 1;
                }
                else if (language.equals(data[3])) {
                    count =+ 1;
                }
            }
        }

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
}