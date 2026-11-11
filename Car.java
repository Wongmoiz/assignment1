import java.awt.*;

public abstract class Car implements Movable {
    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private double x;
    private double y;
    private int direction; // 0 is forward, 1 is right, 2 is down and 3 is left

    public Car(int nrDoors, double enginePower, Color color, String modelName){
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.currentSpeed = 0;
    }
    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    public void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    public void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    public void gas(double amount) {
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        decrementSpeed(amount);
    }

    public abstract double speedFactor();

    public void move(){
        if (direction == 0){
            y = y + getCurrentSpeed();}

        else if (direction == 1){
            x = x + getCurrentSpeed();
        }
        else if (direction == 2){
            y = y - getCurrentSpeed();
        }
        else if (direction == 3){
            x = x - getCurrentSpeed();
        }
    }
    public void turnLeft(){
        if (direction == 0) {
            direction = 3;
        } else if (direction == 3) {
            direction = 2;
        } else if (direction == 2) {
            direction = 1;
        } else if (direction == 1) {
            direction = 0;
        }
    }
    public void turnRight() {
        if (direction == 0) {
            direction = 1;
        } else if (direction == 1) {
            direction = 2;
        } else if (direction == 2) {
            direction = 3;
        } else if (direction == 3) {
            direction = 0;
        }
    }
}


