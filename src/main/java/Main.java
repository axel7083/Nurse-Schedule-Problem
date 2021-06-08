import models.Instance;
import models.Solution;
import utils.InstanceUtils;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create an instance generator
        InstanceGenerator instanceGenerator = new InstanceGenerator();

        // Getting instance from generator
        Instance instance = instanceGenerator.getInstance();

        // Creating a solver
        Solver solver = new Solver(instance);

        // Joining Future<Solution> to main thread
        Solution solution = solver.solve(1000).get();

        // Printing solution
        System.out.println(solution);
    }


}
