package models;

import static models.Interface.NAME_SKILLS;
import static models.Interface.NAME_SPECIALITY;

public class Formation {
    public int id;
    public int speciality;
    public int skill;
    public int day;
    public int startHour;
    public int endHour;

    public Formation(int id, int speciality, int skill, int day, int startHour, int endHour) {
        this.id = id;
        this.speciality = speciality;
        this.skill = skill;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public static String WEEK_DAY[] = {
            "LUNDI",
            "MARDI",
            "MERCREDI",
            "JEUDI",
            "VENDREDI",
            "SAMEDI"};


    @Override
    public String toString() {
        return "["+id+"] speciality:" + NAME_SPECIALITY[speciality] + ", skill:" + NAME_SKILLS[skill] + ", day:" + WEEK_DAY[day] + ", startHour:" + startHour +", endHour" + endHour;
    }
}
