package EveryComponent;
import java.util.*;
import java.io.*;

class Project{
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner user = new Scanner(System.in);
        Attributes person = new Attributes();
        String fileName = "PeopleData.csv";

        // Asking the user multiple questions about themselves
        System.out.println("Answer every question in lowercase.");
        System.out.println("What is your name?");
        String name = user.nextLine();

        System.out.println("What topic are you studying?");
        String subject = user.nextLine();

        System.out.println("How old are you?");
        int age = user.nextInt();
        
        System.out.println("What country are you from?");
        String country = user.nextLine();
        country = user.nextLine();
        
        System.out.println("What city are you from?");
        String city = user.nextLine();

        person.userName(name);
        person.userSubject(subject);
        person.userAge(age);
        person.userCountry(country);
        person.userCity(city);

        addData(name, subject, age, country, city, fileName);

        user.close();
        
    }

    public static void addData(String name, String subject, int age, String country, String city, String fileName) {
        
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(name + "," + subject + "," + age + "," + country + "," + city);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}