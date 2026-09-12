import java.util.HashSet;

public class BusTicket {

    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {

        if (passengerName == null || passengerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        if (!passengerName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Name must contain only letters");
        }

        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid destination");
        }

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }

    public void markCheckedIn() {

        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in successfully");
        } else {
            System.out.println("Already checked in");
        }
    }

    static void processBatch(String[][] rawBookings) {

        HashSet<String> accepted = new HashSet<>();

        int valid = 0;
        int rejected = 0;
        int duplicate = 0;

        for (String[] booking : rawBookings) {

            try {
                BusTicket ticket =
                        new BusTicket(booking[0], booking[1]);

                String key = booking[0].trim().toLowerCase()
                        + "|" + booking[1].trim().toLowerCase();

                if (accepted.contains(key)) {
                    duplicate++;
                } else {
                    accepted.add(key);
                    valid++;
                }

            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid);
        System.out.println("Rejected: " + rejected);
        System.out.println("Duplicates skipped: " + duplicate);
    }

    public static void main(String[] args) {

        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        processBatch(bookings);
    }
}