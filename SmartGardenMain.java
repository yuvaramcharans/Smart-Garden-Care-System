package smartgarden;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SmartGardenMain 
{
    public static void main(String[] args) 
    {
        run();
    }
    
    private static Scanner sc = new Scanner(System.in);
    private static SoilMoistureSensor soilSensor = new SoilMoistureSensor();
    private static TemperatureSensor tempSensor = new TemperatureSensor();
    private static LightSensor lightSensor = new LightSensor();
    private static WaterPump pump = new WaterPump();
    private static GrowLight growLight = new GrowLight();
    private static Garden garden = new Garden();
    private static PlantCareManager careManager = new PlantCareManager();
    private static int logSerialNumber = 1;
    
    private static void run() 
    {
        boolean exit = false;
        while (!exit) 
        {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) 
            {
                case 1: addPlant(); break;
                case 2: removePlant(); break;
                case 3: viewPlants(); break;
                case 4: viewSensors(); break;
                case 5: controlIrrigation(); break;
                case 6: controlGrowLight(); break;
                case 7: analyzeData(); break;
                case 8: managePlantCareSchedule(); break;
                case 9:
                    exit = true;
                    System.out.println("Application Exiting......");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        sc.close();
    }
    
    private static void printMenu() 
    {
        System.out.println("---------------------- SMART GARDEN CARE SYSTEM -----------------------");
        System.out.println("1. Add a Plant (with type: Flower/Fruit/Vegetable)");
        System.out.println("2. Remove a Plant");
        System.out.println("3. View All Plants");
        System.out.println("4. View Current Sensor Readings");
        System.out.println("5. Control Irrigation System (Manual/Auto)");
        System.out.println("6. Control Grow Lights (Manual/Auto)");
        System.out.println("7. Analyze Sensor Data");
        System.out.println("8. Manage Plant Care Schedule");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void addPlant() 
    {
        System.out.print("Enter Plant Name: ");
        String name = sc.nextLine();
        System.out.println("Select Plant Type:");
        System.out.println("1. Flower");
        System.out.println("2. Fruit");
        System.out.println("3. Vegetable");
        System.out.print("Enter choice: ");
        int typeChoice = sc.nextInt();
        sc.nextLine();
        
        String type;
        switch (typeChoice) 
        {
            case 1: type = PlantType.FLOWER; break;
            case 2: type = PlantType.FRUIT; break;
            case 3: type = PlantType.VEGETABLE; break;
            default: type = "Other"; break;
        }
        
        Plant plant = new Plant(name, type);
        if (garden.addPlant(plant)) 
        {
            System.out.println("Added Plant: " + plant);
            log("Plant added: " + plant);
        }
        else 
        {
            System.out.println("Garden is full, cannot add more plants.");
            log("Failed to add plant: " + plant + " - Garden full");
        }
    }
    
    private static void removePlant() 
    {
        System.out.print("Enter Plant Name to remove: ");
        String name = sc.nextLine();
        if (garden.removePlant(name)) 
        {
            System.out.println("Removed Plant: " + name);
            log("Plant removed: " + name);
        }
        else 
        {
            System.out.println("Plant not found: " + name);
            log("Failed to remove plant: " + name);
        }
    }
    
    private static void viewPlants() 
    {
        Plant[] plants = garden.getPlants();
        if (plants.length == 0) 
        {
            System.out.println("No plants in the garden.");
        }
        else 
        {
            System.out.println("Plants in the garden:");
            for (Plant p : plants) 
            {
                System.out.println("- " + p);
            }
        }
    }
    
    private static void viewSensors() 
    {
        try 
        {
            soilSensor.readData();
            tempSensor.readData();
            lightSensor.readData();
            
            System.out.println("------------------------------------------------------------");
            System.out.println("Current Sensor Readings:");
            System.out.printf("- Soil Moisture: %.2f%%%n", soilSensor.getValue());
            System.out.printf("- Temperature: %.2f °C%n", tempSensor.getValue());
            System.out.printf("- Light Intensity: %.2f lux%n", lightSensor.getValue());
            System.out.println("------------------------------------------------------------");
            log(String.format("Sensor Readings: Soil=%.2f%% Temp=%.2f°C Light=%.2f lux", 
                soilSensor.getValue(), tempSensor.getValue(), lightSensor.getValue()));
        }
        catch (InvalidSensorValueException e) 
        {
            System.out.println("Error reading sensors: " + e.getMessage());
            log("Sensor Error: " + e.getMessage());
        }
    }
    
    private static void controlIrrigation() 
    {
        System.out.println("Control Irrigation System");
        System.out.println("1. Turn ON");
        System.out.println("2. Turn OFF");
        System.out.println("3. Automatic mode");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) 
        {
            case 1:
                actuatorCountdown("Motor ON", 3000);
                pump.turnOn();
                System.out.println("Motor is turned ON.");
                log("Motor turned ON manually.");
                break;
            case 2:
                actuatorCountdown("Motor OFF", 3000);
                pump.turnOff();
                System.out.println("Motor is turned OFF.");
                log("Motor turned OFF manually.");
                break;
            case 3:
                try 
                {
                    soilSensor.readData();
                    if (soilSensor.getValue() < 40.0) 
                    {
                        actuatorCountdown("Motor ON", 3000);
                        pump.turnOn();
                        System.out.println("Motor turned ON due to low moisture (" + soilSensor.getValue() + "%).");
                        log("Motor ON automatic.");
                    }
                    else 
                    {
                        actuatorCountdown("Motor OFF", 3000);
                        pump.turnOff();
                        System.out.println("Motor turned OFF due to sufficient moisture (" + soilSensor.getValue() + "%).");
                        log("Motor OFF automatic.");
                    }
                }
                catch (InvalidSensorValueException e) 
                {
                    System.out.println("Error in automatic irrigation: " + e.getMessage());
                    log("Irrigation error: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid option!");
        }
    }
    
    private static void controlGrowLight() 
    {
        System.out.println("Control Grow Light");
        System.out.println("1. Turn ON");
        System.out.println("2. Turn OFF");
        System.out.println("3. Automatic mode");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) 
        {
            case 1:
                actuatorCountdown("Grow Light ON", 3000);
                growLight.turnOn();
                System.out.println("Grow Light is turned ON.");
                log("Grow Light turned ON manually.");
                break;
            case 2:
                actuatorCountdown("Grow Light OFF", 3000);
                growLight.turnOff();
                System.out.println("Grow Light is turned OFF.");
                log("Grow Light turned OFF manually.");
                break;
            case 3:
                try 
                {
                    lightSensor.readData();
                    if (lightSensor.getValue() < 300.0) 
                    {
                        actuatorCountdown("Grow Light ON", 3000);
                        growLight.turnOn();
                        System.out.println("Grow Light turned ON due to low light (" + lightSensor.getValue() + " lux).");
                        log("Grow Light ON automatic.");
                    }
                    else 
                    {
                        actuatorCountdown("Grow Light OFF", 3000);
                        growLight.turnOff();
                        System.out.println("Grow Light turned OFF due to sufficient light (" + lightSensor.getValue() + " lux).");
                        log("Grow Light OFF automatic.");
                    }
                }
                catch (InvalidSensorValueException e) 
                {
                    System.out.println("Error in automatic grow light control: " + e.getMessage());
                    log("Grow Light error: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid option!");
        }
    }
    
    private static void analyzeData() 
    {
        System.out.println("Data Analysis:");
        try 
        {
            double soil = soilSensor.getValue();
            double temp = tempSensor.getValue();
            double light = lightSensor.getValue();
            System.out.println("Soil Moisture is " + (soil < 40 ? "Low" : "Optimal"));
            System.out.println("Temperature is " + (temp < 18 ? "Cold" : (temp > 30 ? "Hot" : "Normal")));
            System.out.println("Light Intensity is " + (light < 300 ? "Low" : "Sufficient"));
            log("Data analyzed.");
        }
        catch (Exception e) 
        {
            System.out.println("Error analyzing data.");
            log("Data analysis error: " + e.getMessage());
        }
    }
    
    private static void managePlantCareSchedule() 
    {
        int choice;
        do 
        {
            System.out.println("\n--- Plant Care Schedule Management  ---");
            System.out.println("1. Add Care Schedule");
            System.out.println("2. View All Schedules");
            System.out.println("3. Clear All Schedules");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) 
            {
                case 1: addCareSchedule(); break;
                case 2: careManager.displayAllSchedulesWithIterator(); break;
                case 3: clearSchedules(); break;
                case 4: break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
    
    private static void addCareSchedule() 
    {
        System.out.print("Enter Plant Name: ");
        String plantName = sc.nextLine();
        System.out.println("Select Care Type:");
        System.out.println("1. Watering");
        System.out.println("2. Fertilizing");
        System.out.println("3. Pruning");
        System.out.print("Enter choice: ");
        int typeChoice = sc.nextInt();
        sc.nextLine();
        
        String careType;
        switch (typeChoice) 
        {
            case 1: careType = "Watering"; break;
            case 2: careType = "Fertilizing"; break;
            case 3: careType = "Pruning"; break;
            default: careType = "Other"; break;
        }
        
        System.out.print("Enter Care Interval (days): ");
        int interval = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Select Priority:");
        System.out.println("1. High");
        System.out.println("2. Medium");
        System.out.println("3. Low");
        System.out.print("Enter choice: ");
        int priority = sc.nextInt();
        sc.nextLine();
        
        if (priority < 1 || priority > 3) 
        {
            priority = 2;
        }
        
        CareSchedule schedule = new CareSchedule(plantName, careType, interval, priority);
        careManager.addSchedule(schedule);
        System.out.println("Care schedule added successfully.");
        log("Care schedule added: " + schedule);
    }
    
    private static void clearSchedules() 
    {
        careManager.clearAllSchedules();
        System.out.println("All care schedules cleared.");
        log("Care schedules cleared.");
    }
    
    private static void actuatorCountdown(String message, int durationMs) 
    {
        try 
        {
            System.out.println(message + " in:");
            int remainingSeconds = durationMs / 1000;
            while (remainingSeconds > 0) 
            {
                System.out.println(remainingSeconds);
                Thread.sleep(1000);
                remainingSeconds--;
            }
        }
        catch (InterruptedException e) 
        {
            System.out.println("Operation interrupted.");
        }
    }
    
    private static void log(String logEntry) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("system_logs.txt", true))) 
        {
            writer.write("Log" + (logSerialNumber++) + "/ " + logEntry);
            writer.newLine();
        }
        catch (IOException e) 
        {
            System.out.println("Error writing log: " + e.getMessage());
        }
    }
}
