public class Week7Problem2 {
    interface Exportable {
        String exportData();
    }

    static class ExportCounter {
        static int count = 0;
    }

    static class ReportGenerator implements Exportable {
        String reportName;

        ReportGenerator(String reportName) {
            this.reportName = reportName;
        }

        public String exportData() {
            ExportCounter.count++;
            return "Exported report: " + reportName;
        }
    }

    static class UserProfile implements Exportable {
        String username;

        UserProfile(String username) {
            this.username = username;
        }

        public String exportData() {
            ExportCounter.count++;
            return "Exported profile: " + username;
        }
    }

    static int getTotalExports() {
        return ExportCounter.count;
    }

    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        Exportable[] items = {r, u};
        exportAll(items);

        System.out.println(getTotalExports());
    }
}