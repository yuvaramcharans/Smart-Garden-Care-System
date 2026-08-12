package smartgarden;

public interface Sensor {
    void readData() throws InvalidSensorValueException;
    double getValue();
    String getName();
}
