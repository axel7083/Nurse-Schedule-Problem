package models;

import static utils.MathUtils.distance;

public class Instance {

    public Interface[] interfaces;
    public Centre[] centres;
    public Learner[] learners;
    public Formation[] formations;
    public double fcorr;

    public Instance(Interface[] interfaces, Centre[] centres, Learner[] learners, Formation[] formations) {
        this.interfaces = interfaces;
        this.centres = centres;
        this.learners = learners;
        this.formations = formations;

        computeCorrelationFactor();
    }

    private void computeCorrelationFactor() {
        double sum = 0;
        for(Formation a : formations) {
            for(Formation b : formations) {
                sum+=distance(centres[a.speciality].coordinates,centres[b.speciality].coordinates);
            }
        }
        fcorr = sum/formations.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("fcorr:").append(fcorr).append("\n");



        stringBuilder.append("interfaces:\n");
        for (int i = 0; i < interfaces.length; i++) {
            stringBuilder.append(interfaces[i].toString()).append("\n");
        }

        stringBuilder.append("centres:\n");
        for (int i = 0; i < centres.length; i++) {
            stringBuilder.append(centres[i].toString()).append("\n");
        }

        stringBuilder.append("learners:\n");
        for (int i = 0; i < learners.length; i++) {
            stringBuilder.append(learners[i].toString()).append("\n");
        }

        stringBuilder.append("formations:\n");
        for (int i = 0; i < formations.length; i++) {
            stringBuilder.append(formations[i].toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}
