public class DeliveryAccount {

    protected String studentId;
    protected double orderValue;

    static {
        System.out.println("Nightly reconciliation started");
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Delay cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        int first = Math.min(delayMinutes, 5);
        fee += first * orderValue * 0.005;

        if (delayMinutes > 5) {
            int second = Math.min(delayMinutes, 15) - 5;
            fee += second * orderValue * 0.01;
        }

        if (delayMinutes > 15) {
            int third = delayMinutes - 15;
            fee += third * orderValue * 0.02;
        }

        return fee;
    }

    public void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account == null) {
            return;
        }

        double fee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof Premium) {

            System.out.println(
                    "Premium account: " + account.studentId);
            System.out.println("Surge fee: " + fee);

        } else {

            System.out.println(
                    "Regular account: " + account.studentId);
            System.out.println("Surge fee: " + fee);
        }
    }

    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        int limit = Math.min(
                accounts.length,
                Math.min(amounts.length, delayMinutesArray.length)
        );

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotal = 0.0;

        DeliveryAccount processor =
                new DeliveryAccount("PROCESSOR");

        for (int i = 0; i < limit; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            processor.processAccount(
                    accounts[i],
                    amounts[i],
                    delayMinutesArray[i]
            );

            double fee =
                    accounts[i].calculateSurgeFee(
                            delayMinutesArray[i]);

            grandTotal += fee;
            processed++;

            if (accounts[i] instanceof Premium) {
                premium++;
            } else {
                regular++;
            }
        }

        System.out.println();
        System.out.println(processed + " processed");
        System.out.println(nullSkipped + " null skipped");
        System.out.println(premium + " premium");
        System.out.println(regular + " regular");
        System.out.println(
                "Grand total surge fees = " + grandTotal);
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
            500, 400, 300
        };

        int[] delays = {
            10, 5, 0
        };

        processBatch(accounts, amounts, delays);
    }
}


class Premium extends DeliveryAccount {

    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}