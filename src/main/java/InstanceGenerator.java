/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import models.*;
import utils.InstanceUtils;

import java.util.Random;

/**
 *
 * @author Olivier Grunder
 */
public class InstanceGenerator {

    public int nb_learner = 20*4;
    public int nb_interface = (int) (nb_learner /4 * 1.2);

    public int dimensionArea = 200;

    // If you want to change it, you have to look at generateInterfaces()
    public static int NBR_COMPETENCES = 2;

    public int NBR_CENTRES_FORMATION = 5;
    public int NBR_SPECIALITES = NBR_CENTRES_FORMATION;
    public String NOMS_SPECIALITES[] = {
            "SPECIALITE_MENUISERIE",
            "SPECIALITE_ELECTRICITE",
            "SPECIALITE_MECANIQUE",
            "SPECIALITE_INFORMATIQUE",
            "SPECIALITE_CUISINE"};


    public InstanceGenerator() { }

    public InstanceGenerator(int nb_learner, int nb_interface, int dimensionArea, int NBR_CENTRES_FORMATION, String[] NOMS_SPECIALITES) {
        this.nb_learner = nb_learner;
        this.nb_interface = nb_interface;
        this.dimensionArea = dimensionArea;
        this.NBR_CENTRES_FORMATION = NBR_CENTRES_FORMATION;
        this.NBR_SPECIALITES = NBR_CENTRES_FORMATION;
        this.NOMS_SPECIALITES = NOMS_SPECIALITES;
    }

    public InstanceGenerator(int nb_learner, int nb_interface) {
        this.nb_learner = nb_learner;
        this.nb_interface = nb_interface;
    }

    public InstanceGenerator(int nb_learner) {
        this.nb_learner = nb_learner;
        this.nb_interface = (int) (nb_learner /4 * 1.2);
    }

    Random rand = new Random();;

    public Instance getInstance() {
        return InstanceUtils.formatInstance(new Instance(
                generateInterfaces(),
                generateCenters(),
                generateLearners(),
                generateFormations()
        ));
    }

    private Interface[] generateInterfaces() {
        Interface[] interfaces = new Interface[nb_interface];

        for (int i = 0; i < nb_interface; i++) {
            double f = rand.nextDouble() ;
            if (f < 0.1) {
                interfaces[i] = (new Interface(i, 35, new boolean[]{true, true}));
            } else if (f < 0.55) {
                interfaces[i] = (new Interface(i, 35, new boolean[]{true, false}));
            } else {
                interfaces[i] = (new Interface(i, 35, new boolean[]{false, true}));
            }
            interfaces[i].specialty = new boolean[NBR_SPECIALITES];
            for (int j = 0; j < NBR_SPECIALITES; j++) {
                interfaces[i].specialty[j] = rand.nextDouble() < 0.2;
            }
        }
        return interfaces;
    }

    private Centre[] generateCenters() {
        Centre[] centres = new Centre[1+NBR_SPECIALITES];
        centres[0] = new Centre("SESSAD",-1,
                new Coordinates(
                        (int) (rand.nextDouble() * dimensionArea),
                        (int) (rand.nextDouble() * dimensionArea)
                ));

        for (int i = 0; i < NBR_SPECIALITES; i++) {
            centres[i+1] = new Centre(NOMS_SPECIALITES[i],i,
                    new Coordinates(
                            (int) (rand.nextDouble() * dimensionArea),
                            (int) (rand.nextDouble() * dimensionArea)
                    ));
        }
        return centres;
    }

    private Learner[] generateLearners() {
        Learner[] learners = new Learner[nb_learner];
        for (int i = 0; i < nb_learner; i++) {
            learners[i] = new Learner(i,
                    new Coordinates(
                    (int) (rand.nextDouble() * dimensionArea),
                    (int) (rand.nextDouble() * dimensionArea)
            ));
        }
        return learners;
    }

    private Formation[] generateFormations() {
        Formation[] formations = new Formation[nb_learner];

        int maxi = nb_learner;
        // public static int NBR_FORMATIONS = NBR_APPRENANTS * NBR_FORMATIONS_PAR_SEMAINE;
        for (int i = 0; i < maxi; i++) { // Apprenant i

            boolean isMorning = rand.nextBoolean();
            int start = isMorning?8 + rand.nextInt(3):13 + rand.nextInt(4);
            int end = isMorning?start + rand.nextInt(11 - start) + 2:start + rand.nextInt(18 - start) + 2;

            formations[i] = new Formation(
                    i,
                    rand.nextInt(NBR_SPECIALITES),
                    rand.nextInt(NBR_COMPETENCES),
                    rand.nextInt(6),
                    start,
                    end
            );
        }
        return formations;

    }
}
