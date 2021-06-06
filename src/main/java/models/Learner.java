package models;

public class Learner {
    public int id;
    public Coordinates coordinates;

    public Learner(int id, Coordinates coordinates) {
        this.id = id;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "["+id+"] " + coordinates.toString();
    }
}
