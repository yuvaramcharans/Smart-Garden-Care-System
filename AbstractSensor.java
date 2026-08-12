package smartgarden;

public abstract class AbstractSensor {
    protected String name;
    protected double value;

    public AbstractSensor(String name) {
        this.name = name;
        this.value = 0.0;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public abstract void readData() throws InvalidSensorValueException;
}
