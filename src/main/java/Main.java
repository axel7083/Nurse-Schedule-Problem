import models.Instance;
import models.Solution;
import utils.InstanceUtils;

public class Main {

    public static void main(String[] args) {

        /*InstanceGenerator instanceGenerator = new InstanceGenerator();
        Instance instance = instanceGenerator.getInstance();

        instance = InstanceUtils.formatInstance(instance);

        //System.out.println(instance);
        //System.out.println("----------");

        Solver solver = new Solver(instance);
        solver.solve(10000);
        solver.printSolution(false);*/
    }

    public static void runTests() {

        for(int i = 50; i < 1000; i += 50) {
            System.out.println("i=500");
        }
    }

    public static double averageSkills(Solution[] sols) {
        int sum = 0;
        for (Solution e : sols) {
            sum+= (e != null)?e.matchingSkills:0;
        }
        return (double) sum/sols.length;
    }

    public static double averageSpeciality(Solution[] sols) {
        int sum = 0;
        for (Solution e : sols) {
            sum+= (e != null)?e.matchingSpeciality:0;
        }
        return (double) sum/sols.length;
    }

    public static Solution[] tests(int count, int timeout) {
        Solution[] sols = new Solution[count];

        for (int i = 0; i < count; i++) {
            //System.out.println("Test " + i + "/" + count);
            Instance instance = InstanceUtils.formatInstance(new InstanceGenerator().getInstance());

            Solver solver = new Solver(instance).solve(timeout);
            sols[i] = solver.computerSolution();
        }
        return sols;
    }


}
