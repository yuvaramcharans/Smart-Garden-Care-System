# 🌱 Smart Garden Care System

A Java-based Smart Garden Care System designed to simulate and manage garden-care activities using sensors, actuators, plants, and scheduled care operations.

## 📌 Project Overview

The Smart Garden Care System provides a simple software-based simulation of a smart garden environment. It manages plants and monitors environmental conditions such as **soil moisture, temperature, and light** using sensors.

Based on the sensor readings and plant requirements, the system can control actuators such as a **water pump and grow light** and manage scheduled plant-care activities.

## ✨ Features

* 🌱 Plant management
* 💧 Soil moisture monitoring
* 🌡️ Temperature monitoring
* 💡 Light-level monitoring
* 🚿 Automatic water-pump control
* 🔆 Grow-light control
* 📅 Plant-care scheduling
* 🏡 Garden management
* ⚠️ Sensor value validation
* ❌ Custom exception handling
* 🧩 Object-oriented Java implementation

## 🛠️ Technologies Used

* **Java**
* Object-Oriented Programming (OOP)
* Java Exception Handling
* Java Collections
* Inheritance and Abstraction

## 📂 Project Structure

```text
smartgarden/
│
├── AbstractActuator.java
├── AbstractSensor.java
├── Actuator.java
├── CareSchedule.java
├── Garden.java
├── GrowLight.java
├── InvalidSensorValueException.java
├── LightSensor.java
├── Plant.java
├── PlantCareManager.java
├── PlantType.java
├── Sensor.java
├── SmartGardenMain.java
├── SoilMoistureSensor.java
├── TemperatureSensor.java
├── WaterPump.java
└── .gitignore
```

## 🏗️ System Components

### Sensors

The system uses different sensors to monitor garden conditions:

* **Soil Moisture Sensor** — monitors soil moisture levels.
* **Temperature Sensor** — monitors temperature.
* **Light Sensor** — monitors light conditions.

### Actuators

The system uses actuators to perform garden-care actions:

* **Water Pump** — provides water when required.
* **Grow Light** — provides additional light when required.

### Garden & Plant Management

The system maintains information about:

* Gardens
* Plants
* Plant types
* Plant-care schedules
* Sensor readings
* Actuator operations

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/yuvaramcharans/Smart-Garden-Care-System.git
```

### 2. Open the project

Open the project folder in an IDE such as **VS Code**, **IntelliJ IDEA**, or **Eclipse**.

### 3. Compile the Java files

```bash
javac *.java
```

### 4. Run the application

```bash
java SmartGardenMain
```

## 🎯 Objective

The main objective of this project is to demonstrate how **Java Object-Oriented Programming concepts** can be applied to build a software model of a smart garden.

The project combines sensors, actuators, plants, and care schedules into a single system that can be extended to support real-world IoT-based garden automation.

## 🚀 Future Enhancements

* Integration with real IoT sensors
* Real-time sensor monitoring
* Web-based dashboard
* Database integration
* Automated notifications
* Mobile application
* Weather API integration
* AI-based plant-care recommendations

## 👨‍💻 Author

**Yuva Ramcharan Sunkireddy**

Computer Science and Engineering

## 📄 License

This project is created for educational and learning purposes.
