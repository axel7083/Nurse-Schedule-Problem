package models;

public class Interface {

    public static String NAME_SKILLS[] = {
            "COMPETENCE_SIGNES",
            "COMPETENCE_CODAGE"};

    public static String NAME_SPECIALITY[] = {
            "SPECIALITE_MENUISERIE",
            "SPECIALITE_ELECTRICITE",
            "SPECIALITE_MECANIQUE",
            "SPECIALITE_INFORMATIQUE",
            "SPECIALITE_CUISINE"};

    public int id ;
    public boolean[] skills;
    public boolean[] specialty;
    public int maxHour;

    public Interface(int id, int maxHour, boolean[] skills) {
        this.id = id;
        this.maxHour = maxHour;
        this.skills = skills;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("["+id+"] Skills: ");

        for (int i = 0; i < skills.length; i++) {
            if(skills[i])
                stringBuilder.append(NAME_SKILLS[i]).append(" ");
        }

        stringBuilder.append("Specialities: ");
        for (int i = 0; i < specialty.length; i++) {
            if(specialty[i])
                stringBuilder.append(NAME_SPECIALITY[i]).append(" ");
        }

        return stringBuilder.toString();
    }
}
