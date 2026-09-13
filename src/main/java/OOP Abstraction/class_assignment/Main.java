public class Main {

    public static void connectAllToApp(
            Remoteable[] devices,
            String appId) {

        for (Remoteable r : devices) {
            r.connectToApp(appId);
        }
    }

    public static void main(String[] args) {

        SmartLight light =
                new SmartLight("LIGHT-01");

        SmartThermostat thermostat =
                new SmartThermostat("THERMO-01", 22.5);

        BasicLamp lamp =
                new BasicLamp("LAMP-01");

        SmartDoorLock lock =
                new SmartDoorLock("LOCK-01");


        light.turnOn();
        light.performPrimaryAction();

        thermostat.turnOn();
        thermostat.performPrimaryAction();

        lamp.turnOn();
        lamp.performPrimaryAction();


        System.out.println(
                "Thermostat power draw: "
                + thermostat.getPowerConsumption()
                + "W");


        Remoteable[] remoteDevices = {
                light,
                thermostat,
                lock
        };

        connectAllToApp(
                remoteDevices,
                "HomeConnect");
    }
}