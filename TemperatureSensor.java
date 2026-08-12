package smartgarden;

import java.util.Scanner;

public class TemperatureSensor extends AbstractSensor {
    Scanner sc = new Scanner(System.in);

    public TemperatureSensor() {
        super("Temperature Sensor");
    }

    public void readData() throws InvalidSensorValueException {
        System.out.print("Enter Temperature value (-10 to 50): ");
        double newValue = sc.nextDouble();
        if (newValue < -10 || newValue > 50) {
            throw new InvalidSensorValueException("Temperature value out of range: " + newValue);
        }
        this.value = newValue;
    }
}
