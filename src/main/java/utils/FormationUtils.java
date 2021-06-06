package utils;

import models.Formation;

import java.util.ArrayList;

public class FormationUtils {

    public static Formation[] getFormationByDay(Formation[] formations, int day) {
        ArrayList<Formation> formationArrayList = new ArrayList<Formation>();

        for(Formation formation : formations) {
            if(formation.day == day)
                formationArrayList.add(formation);
        }

        return (Formation[]) formationArrayList.toArray();
    }

}
