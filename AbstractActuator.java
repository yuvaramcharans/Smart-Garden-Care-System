package smartgarden;

public abstract class AbstractActuator implements Actuator {
    protected String name;

    public AbstractActuator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
