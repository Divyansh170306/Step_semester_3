class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
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

    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount);
        pay(amount);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() - (getDue() * scholarshipPercent / 100);
    }
}

public class FeeAccountSystem {
    public static void main(String[] args) {

        FeeAccount plain = new FeeAccount("101", 150000);
        plain.pay(150000);

        HostelFeeAccount hostel =
                new HostelFeeAccount("102", 200000);
        hostel.payInTwoInstallments(30000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("103", 180000, 20);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        System.out.println("Plain account due: Rs " + plain.getDue());

        for (FeeAccount account : accounts) {

            if (account instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs "
                        + account.getDue());
            }

            if (account instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount s =
                        (ScholarshipFeeAccount) account;

                System.out.println("Scholarship account effective due: Rs "
                        + s.effectiveDue());
            }
        }
    }
}