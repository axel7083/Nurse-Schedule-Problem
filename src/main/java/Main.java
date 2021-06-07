import models.Instance;
import models.Solution;
import utils.InstanceUtils;

public class Main {

    public static void main(String[] args) {

        InstanceGenerator instanceGenerator = new InstanceGenerator();
        Instance instance = instanceGenerator.getInstance();

        instance = InstanceUtils.formatInstance(instance);

        //System.out.println(instance);
        //System.out.println("----------");

        Solver solver = new Solver(instance);
        solver.solve(1000);
        solver.printSolution(false);
    }


}
