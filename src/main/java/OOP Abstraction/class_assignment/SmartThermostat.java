public class SmartThermostat extends Device
        implements Remoteable, Schedulable, EnergyMonitorable {

    private double temperature;

    public SmartThermostat(String deviceId, double temperature) {
        super(deviceId);
        this.temperature = temperature;
    }

    @Override
    public void performPrimaryAction() {
        System.out.println(getDeviceId()
                + " is regulating temperature to "
                + temperature + " degrees");
    }

    @Override
    public void connectToApp(String appId) {
        System.out.println(getDeviceId()
                + " connected to app: " + appId);
    }

    @Override
    public void scheduleAction(String time) {
        System.out.println(getDeviceId()
                + " scheduled for " + time);
    }

    @Override
    public double getPowerConsumption() {
        return 45.5;
    }
}