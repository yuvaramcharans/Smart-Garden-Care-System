package smartgarden;

public class WaterPump extends AbstractActuator {
    private boolean isOn = false;

    public WaterPump() {
        super("Water Pump");
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " is ON");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " is OFF");
    }
}
