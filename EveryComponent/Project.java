package EveryComponent;
import java.util.*;
import java.io.*;

class Project{
    public static void main(String[] args) throws FileNotFoundException {
        /*
        Scanner user = new Scanner(System.in);
        Attributes person = new Attributes();

        // Asking the user multiple questions about themselves
        System.out.println("Answer every question in lowercase.");
        System.out.println("What is your name?");
        String name = user.nextLine();

        System.out.println("What topic are you studying?");
        String subject = user.nextLine();

        System.out.println("How old are you?");
        int age = user.nextInt();
        */

        country("CountryList.txt");
        /*
        System.out.println("What city are you from?");
        String city = user.nextLine();

        user.close();
        */
    }

    public static String country(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        String line = "";

        Scanner user = new Scanner(System.in);

        System.out.println("What country are you from?");
        String country = user.nextLine();
        
        while(scan.hasNextLine()) {
            line = scan.nextLine();
            if (country.equals(line)) {
                return country;
            }
            else if(country != line) {
                if (scan.hasNextLine()) {
                    line = scan.nextLine();
                }
                else {
                    System.out.println("Invalid country");
                    System.out.println("What country are you from?");
                    country = user.nextLine();
                }
            }
            user.close();
        }
        scan.close();
        return country;
    }
}