package String.string_operation;

import java.util.Scanner;

public class StudentRecordParser {

    static void parseStudentRecord(String record) {

        String[] data = record.split(",");

        String name = data[0].trim();
        String rollNumber = data[1].trim();
        String department = data[2].trim();
        double cgpa = Double.parseDouble(data[3].trim());

        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Department: " + department);
        System.out.println("CGPA: " + cgpa);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student record (Name,RollNo,Department,CGPA): ");
        String record = sc.nextLine();

        parseStudentRecord(record);

        sc.close();
    }
}