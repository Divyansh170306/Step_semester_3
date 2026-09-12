class SrmStudent {
    String name;
    String regNo;
    int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    // classAverage is static because it works on the whole class,
    // while isEligible belongs to one particular student.
    static double classAverage(SrmStudent[] students) {
        int sum = 0;

        for (SrmStudent s : students) {
            sum += s.attendance;
        }

        return (double) sum / students.length;
    }
}

public class AttendanceSystem {
    public static void main(String[] args) {

        SrmStudent[] students = {
            new SrmStudent("Ravi", "101", 82),
            new SrmStudent("Anitha", "102", 68),
            new SrmStudent("Karthik", "103", 91),
            new SrmStudent("Meera", "104", 74),
            new SrmStudent("Suresh", "105", 60)
        };

        for (SrmStudent s : students) {
            System.out.println(
                s.name + " - " + s.attendance + "% - " +
                (s.isEligible() ? "Eligible" : "Detained")
            );
        }

        System.out.println("Class average: " +
                SrmStudent.classAverage(students) + "%");
    }
}