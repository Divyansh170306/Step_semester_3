public class Week7Problem5 {
    static abstract class HomeDevice {
        private static int count = 1000;
        private final String serialNumber;

        HomeDevice() {
            serialNumber = "HD-" + (++count);
        }

        public abstract String activate();

        String getSerialNumber() {
            return serialNumber;
        }
    }

    interface RemoteControllable {
        String connect(String appId);
    }

    interface EnergyTrackable {
        double getConsumptionWatts();
    }

    static class WashingMachine extends HomeDevice
            implements RemoteControllable, EnergyTrackable {
        double consumptionWatts;

        WashingMachine(double consumptionWatts) {
            this.consumptionWatts = consumptionWatts;
        }

        public String activate() {
            return "Washing machine " + getSerialNumber()
                    + " started a cycle";
        }

        public String connect(String appId) {
            return getSerialNumber() + " connected to " + appId;
        }

        public double getConsumptionWatts() {
            return consumptionWatts;
        }
    }

    static class Refrigerator extends HomeDevice
            implements EnergyTrackable {
        double consumptionWatts;

        Refrigerator(double consumptionWatts) {
            this.consumptionWatts = consumptionWatts;
        }

        public String activate() {
            return "Refrigerator " + getSerialNumber() + " activated";
        }

        public double getConsumptionWatts() {
            return consumptionWatts;
        }
    }

    static class MobileApp implements RemoteControllable {
        String appName;

        MobileApp(String appName) {
            this.appName = appName;
        }

        public String connect(String appId) {
            return appName + " connected to " + appId;
        }
    }

    static void connectAll(RemoteControllable[] items, String appId) {
        for (RemoteControllable item : items) {
            System.out.println(item.connect(appId));
        }
    }

    static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            EnergyTrackable obj = (EnergyTrackable) d;
            return obj.getConsumptionWatts();
        }
        return 0.0;
    }

    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine(500.0);
        Refrigerator fridge = new Refrigerator(150.0);
        MobileApp app = new MobileApp("HomeConnect App");

        System.out.println(wm.activate());

        connectAll(new RemoteControllable[]{wm, app}, "HomeConnect");

        System.out.println(getConsumptionIfTrackable(wm));
        System.out.println(getConsumptionIfTrackable(fridge));
    }
}