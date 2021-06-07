import models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static models.Formation.WEEK_DAY;
import static models.Interface.NAME_SKILLS;
import static models.Interface.NAME_SPECIALITY;

public class Solver {
    private static int MAX_HOUR = 35;

    private Instance instance;

    public Solver(Instance instance) {
        this.instance = instance;
    }

    private long timeout = Long.MAX_VALUE;
    private long start;

    private boolean verbose = false;

    public void solve(int timeout, boolean verbose) {
        this.verbose = verbose;
        solve(timeout);
    }

    public void solve(int timeout) {
        start = System.currentTimeMillis();
        this.timeout = timeout;

        explore(new State(new int[instance.formations.length],new int[instance.interfaces.length],0));
    }



    public void printSolution(boolean full) {

        if(bestAffectation == null) {
            System.out.println("No solution found.");
            return;
        }

        System.out.println("Solution: ");
        System.out.println("("+bestCost+") " + Arrays.toString(bestAffectation));

        if(bestAffectation == null)
            return;

        int matchingSpeciality = 0;
        int matchingSkills = 0;

        int[] interfacesHours = new int[instance.interfaces.length];


        for(int i = 0 ; i < bestAffectation.length; i++) {

            if(full) {
                System.out.println("-- Formation " + i);
                System.out.println("Schedule: " + WEEK_DAY[instance.formations[i].day] + " from " + instance.formations[i].startHour + " to " + instance.formations[i].endHour);
                System.out.println("Designated interface: " + bestAffectation[i]);
            }
            interfacesHours[bestAffectation[i]]+= instance.formations[i].endHour - instance.formations[i].startHour;

            boolean specialityMatching = instance.interfaces[bestAffectation[i]].specialty[instance.formations[i].speciality];
            if(specialityMatching)
                matchingSpeciality++;

            if(full)
                System.out.println("Speciality: " + NAME_SPECIALITY[instance.formations[i].speciality] + " respected? " + specialityMatching);

            boolean skillsmatching = instance.interfaces[bestAffectation[i]].skills[instance.formations[i].skill];
            if(skillsmatching)
                matchingSkills++;

            if(full)
                System.out.println("Skill " + NAME_SKILLS[instance.formations[i].skill] + " respected? " + skillsmatching);
        }

        System.out.println("Stats: matchingSpeciality: " + matchingSpeciality +"/" + bestAffectation.length + " and matchingSkills: " + matchingSkills + "/" + bestAffectation.length);
        System.out.println("hours " + Arrays.toString(interfacesHours));
        System.out.println("nodeExplored " + nodeExplored);
        //System.out.println("SD " + calculateSD(interfacesHours));
    }

    int[] bestAffectation = null;
    double bestCost = Double.MAX_VALUE;

    long nodeExplored = 0;

    public void explore(State state) {
        nodeExplored++;

        // We reached a leave
        if(state.index == instance.formations.length) {
            double cost = state.computeCost(instance);
            if(cost < bestCost) {
                if(verbose) System.out.println("Better solution: " + cost);
                bestAffectation = state.affectation;
                bestCost = (cost);
            }
            return;
        }

        // Timeout
        if(System.currentTimeMillis() - start > timeout)
            return;

        ArrayList<Interface> interfacesAvailable = findKBest(state,2);

        interfacesAvailable.forEach(anInterface -> {
            State nState = state.clone();
            nState.affectation[state.index] = anInterface.id;
            nState.hours[anInterface.id]+= instance.formations[state.index].endHour - instance.formations[state.index].startHour;
            nState.index++;

            if(nState.computeCost(instance) < bestCost)
                explore(nState);

        });
    }

    public ArrayList<Interface> findKBest(State state, int k) {
        // Get the formation we search the best
        Formation formation = instance.formations[state.index];

        // Get all the interfaces
        ArrayList<Interface> interfaces = new ArrayList<>(k);

        for(Interface anInterface : instance.interfaces) {
            if(anInterface.skills[formation.skill] && isCompatible(state, formation, anInterface) && state.hours[anInterface.id] <= anInterface.maxHour) {
                interfaces.add(anInterface);
            }
        }

        // People with wrong speciality are put at the end, while we sort the rest by hour rate
        interfaces.sort(Comparator.comparingInt(o -> (o.specialty[formation.speciality] ? state.hours[o.id]: MAX_HOUR + state.hours[o.id])));

        // We do not take all of them because we are memory limited
        interfaces.trimToSize();

        return interfaces;
    }


    private boolean isCompatible(State state, Formation formation, Interface anInterface) {
        if(state.index == 0)
            return true;

        int dayHour = 0;

        // We check the formation BACKWARD
        for(int i = state.index - 1; i >= 0 ; i--) {
            // If we reached the previous day we stop
            if(instance.formations[i].day != formation.day)
                return true;

            if(state.affectation[i] == anInterface.id) {
                // If the end our is after OR the same that the start hour, it is not valid
                // after => one previous formation end after the starting date so incompatible
                // same => our interface cannot teleport, therefore, not compatible
                if(formation.startHour <= instance.formations[i].endHour)
                    return false;

                // Summing number of hours per day
                dayHour += instance.formations[i].endHour - instance.formations[i].startHour;
                // If working would be more than 8 hours for THIS day is is not compatible
                if(dayHour + (formation.endHour-formation.startHour) >= 8)
                    return false;
            }
        }
        return true;
    }
}
