import models.Instance;
import models.Solution;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        InstanceGenerator instanceGenerator = new InstanceGenerator();
        Instance instance = instanceGenerator.getInstance();

        Solver solver = new Solver(instance);
        Solution solution = solver.solve(1000).get();

    }


}
