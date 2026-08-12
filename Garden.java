package smartgarden;

public class Garden {
    private Plant[] plants;
    private int count;
    private final int capacity = 100;

    public Garden() {
        plants = new Plant[capacity];
        count = 0;
    }

    public boolean addPlant(Plant plant) {
        if (count < capacity) {
            plants[count++] = plant;
            return true;
        }
        return false;
    }

    public boolean removePlant(String name) {
        for (int i = 0; i < count; i++) {
            if (plants[i].getName().equalsIgnoreCase(name)) {
                for (int j = i; j < count - 1; j++) {
                    plants[j] = plants[j + 1];
                }
                plants[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    public Plant[] getPlants() {
        Plant[] currentPlants = new Plant[count];
        for (int i = 0; i < count; i++) {
            currentPlants[i] = plants[i];
        }
        return currentPlants;
    }
}
