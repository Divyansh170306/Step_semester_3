public class SmartLight extends Device
        implements Remoteable, Schedulable {

    public SmartLight(String deviceId) {
        super(deviceId);
    }

    @Override
    public void performPrimaryAction() {
        System.out.println(getDeviceId() + " is glowing at 100% brightness");
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
}