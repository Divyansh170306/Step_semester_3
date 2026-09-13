public class BasicLamp extends Device {

    public BasicLamp(String deviceId) {
        super(deviceId);
    }

    @Override
    public void performPrimaryAction() {
        System.out.println(getDeviceId()
                + " is simply lit, nothing fancy");
    }
}