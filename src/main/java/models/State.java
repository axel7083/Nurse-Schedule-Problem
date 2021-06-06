package models;

import static utils.MathUtils.calculateSD;
import static utils.MathUtils.distance;

public class State {
    public int[] affectation;
    public int[] hours;
    public int index;

    public State(int[] affectation, int[] hours, int index) {
        this.affectation = affectation;
        this.hours = hours;
        this.index = index;
    }

    @Override
    public State clone() {
        return new State(affectation.clone(),hours.clone(),index);
    }

    public double computeCost(Instance instance) {
        Formation[] previousFormation = new Formation[instance.interfaces.length];

        double[] distances = new double[affectation.length];
        int penalty = 0;

        for (int i = 0; i < index; i++) {

            Formation formation = instance.formations[i];
            Interface anInterface = instance.interfaces[affectation[i]];

            // If it is the first time of the day moving
            if(previousFormation[affectation[i]]==null || previousFormation[affectation[i]].day != formation.day) {
                distances[affectation[i]] += distance(
                        instance.centres[0].coordinates // SESSAD centre
                        ,instance.centres[formation.speciality].coordinates);
            }
            else
            {
                distances[affectation[i]] += distance(
                        instance.centres[previousFormation[affectation[i]].speciality].coordinates
                        ,instance.centres[formation.speciality].coordinates);
            }

            previousFormation[affectation[i]] = formation;

            if(!anInterface.specialty[formation.speciality]) {
                penalty++;
            }
        }

        //min z = 0.5 ∗ (moyd(s) + ecartd(s)) + 0.5 ∗ fcorr ∗ penalite(s)
        double[] distancesSD = calculateSD(distances);
        return 0.5*(distancesSD[1]+distancesSD[0])+0.5*instance.fcorr*penalty;
    }
}
