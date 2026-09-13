public class SmartDoorLock implements Remoteable {

    private String lockId;

    public SmartDoorLock(String lockId) {
        this.lockId = lockId;
    }

    @Override
    public void connectToApp(String appId) {
        System.out.println(lockId
                + " connected to app: " + appId);
    }
}