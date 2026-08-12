package smartgarden;

import java.util.Scanner;

public class LightSensor extends AbstractSensor {
    Scanner sc = new Scanner(System.in);

    public LightSensor() {
        super("Light Sensor");
    }

    public void readData() throws InvalidSensorValueException {
        System.out.print("Enter Light Intensity value (0 - 10000): ");
        double newValue = sc.nextDouble();
        if (newValue < 0 || newValue > 10000) {
            throw new InvalidSensorValueException("Light intensity value out of range: " + newValue);
        }
        this.value = newValue;
    }
}
