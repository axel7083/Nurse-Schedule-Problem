import models.Instance;
import utils.InstanceUtils;

public class Main {

    public static void main(String[] args) {
        InstanceGenerator instanceGenerator = new InstanceGenerator(4);
        Instance instance = instanceGenerator.getInstance();

        instance = InstanceUtils.formatInstance(instance);

        System.out.println(instance);
        System.out.println("----------");

        Solver solver = new Solver(instance);
        solver.solve(2000);
        solver.printSolution(false);
    }

    /*public static void main(String[] args) throws InterruptedException {

        // Generate instance
        InstanceGenerator instanceGenerator = new InstanceGenerator(80);
        Instance instance = instanceGenerator.getInstance();

        // Sort the instance in chronological order
        instance = InstanceUtils.formatInstance(instance);

        // Get the suitable number of processors we could use
        int processors = Runtime.getRuntime().availableProcessors();

        // Creating a BlockingQueue with $processors maximum elements
        ArrayBlockingQueue<State> statesQueue = new ArrayBlockingQueue<>(processors);

        // Creating a threading pool.
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Explorer main = new Explorer(0, statesQueue, instance, false, processors);
        threadPool.execute(main);

        for(int i = 1 ; i < processors; i++) {
            // Creating Explorers object (extending from Runnable)
            threadPool.execute(new Explorer(i, statesQueue, instance, true, processors));
        }

        threadPool.awaitTermination(30000, TimeUnit.MILLISECONDS);
        System.out.println("Finish");
        threadPool.shutdown();

        main.running = false;
        main.printSolution();
    }*/


}
