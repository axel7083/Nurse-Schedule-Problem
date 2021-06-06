package models;

public class Centre {
    public String name;
    public int speciality;
    public Coordinates coordinates;

    public Centre(String name, int speciality, Coordinates coordinates) {
        this.name = name;
        this.speciality = speciality;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "[" +name + "] " + ((speciality > 0)?Interface.NAME_SPECIALITY[speciality]:"") + " " + coordinates.toString();
    }
}
