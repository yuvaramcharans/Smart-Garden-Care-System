package smartgarden;

import java.util.Scanner;

public class SoilMoistureSensor extends AbstractSensor {
    Scanner sc = new Scanner(System.in);

    public SoilMoistureSensor() {
        super("Soil Moisture Sensor");
    }

    public void readData() throws InvalidSensorValueException {
        System.out.print("Enter Soil Moisture value (0-100): ");
        double newValue = sc.nextDouble();
        if (newValue < 0 || newValue > 100) {
            throw new InvalidSensorValueException("Soil moisture value out of range: " + newValue);
        }
        this.value = newValue;
    }
}
