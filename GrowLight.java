package smartgarden;

public class GrowLight extends AbstractActuator {
    private boolean isOn = false;

    public GrowLight() {
        super("Grow Light");
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
