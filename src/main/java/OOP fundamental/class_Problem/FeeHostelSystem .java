class FeeAccount {
    private double totalFee;
    private double amountPaid;

    FeeAccount(double totalFee) {
        this.totalFee = totalFee;
        amountPaid = 0;
    }

    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(double totalFee) {
        super(totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount);
        pay(amount);
    }
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to " + roomNo);
        }
    }
}

class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo,
               HostelFeeAccount feeAccount) {

        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = null;

        totalStudents++;
    }

    String fullStatus() {

        String roomNumber;

        if (room == null) {
            roomNumber = "unallotted";
        } else {
            roomNumber = room.roomNo;
        }

        return name + " | Due: Rs " +
                feeAccount.getDue() +
                " | Room: " + roomNumber;
    }
}

public class FeeHostelSystem {

    public static void main(String[] args) {

        HostelFeeAccount fee1 =
                new HostelFeeAccount(200000);
        fee1.pay(60000);

        HostelFeeAccount fee2 =
                new HostelFeeAccount(200000);
        fee2.pay(20000);

        HostelFeeAccount fee3 =
                new HostelFeeAccount(200000);

        SrmStudent s1 =
                new SrmStudent("Ravi", "101", fee1);

        SrmStudent s2 =
                new SrmStudent("Anitha", "102", fee2);

        SrmStudent s3 =
                new SrmStudent("Karthik", "103", fee3);

        HostelRoom room1 =
                new HostelRoom("C-214", 3, 0);

        HostelRoom room2 =
                new HostelRoom("C-507", 2, 0);

        room1.allot(s1.name);
        s1.room = room1;

        room2.allot(s2.name);
        s2.room = room2;

        // Negative payment is rejected.
        fee3.pay(-5000);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());

        System.out.println(
            "Total students: " + SrmStudent.totalStudents
        );
    }
}