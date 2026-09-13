abstract class Device {

    String deviceId;
    boolean powerOn;

    Device(String deviceId) {
        this.deviceId = deviceId;
        this.powerOn = false;
    }

    void turnOn() {
        powerOn = true;
        System.out.println(deviceId + " is now ON");
    }

    void turnOff() {
        powerOn = false;
        System.out.println(deviceId + " is now OFF");
    }

    abstract void performPrimaryAction();
}


class SmartLight extends Device {

    SmartLight(String deviceId) {
        super(deviceId);
    }

    @Override
    void performPrimaryAction() {
        System.out.println(deviceId + " is glowing");
    }
}


public class Main {
    public static void main(String[] args) {

        SmartLight light = new SmartLight("LIGHT-01");

        light.turnOn();
        light.performPrimaryAction();
        light.turnOff();
    }
}