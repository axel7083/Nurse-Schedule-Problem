import models.Instance;
import models.Solution;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.InstanceUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.*;


public class Tests {

    public static void main(String[] args) {
        try {
            runTests(15,500, 100);
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static void runTests(int iteration, int timeStep, int count) throws IOException, ExecutionException, InterruptedException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("IT45-BackTrack");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Time (ms)");
        header.createCell(1).setCellValue("Formations count");
        header.createCell(2).setCellValue("Matching skills (Average)");
        header.createCell(3).setCellValue("Matching speciality (Average)");
        header.createCell(4).setCellValue("Run count");
        header.createCell(5).setCellValue("Average cost");
        header.createCell(6).setCellValue("Not found");
        header.createCell(7).setCellValue("Impossible");

        int numOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Running on " +numOfCores + " cores");
        ExecutorService executorService = Executors.newFixedThreadPool(numOfCores);

        ConcurrentLinkedQueue<Integer> queues = new ConcurrentLinkedQueue<>();

        // Adding to the queue the value to test
        for(int i = 1 ; i < iteration; i ++)
            queues.add(i);

        // Starting @numOfCores process
        for(int i = 0 ; i < Math.min(iteration, numOfCores); i ++)
            executorService.submit(() -> {
                while(!queues.isEmpty()) {
                    // Getting element from the queue
                    int index = queues.poll();

                    // Printing end estimation
                    System.out.println("[" + index + "] started estimate end ~" + getEstimate(index*timeStep*count));

                    // Computing solution
                    Solution[] sol;
                    try {
                        sol = tests(count, index*timeStep);
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                        continue;
                    }

                    // Saving in the excel file
                    Row row1 = sheet.createRow(index);
                    row1.createCell(0).setCellValue(index*timeStep);
                    row1.createCell(1).setCellValue(80); //TODO: change if needed
                    row1.createCell(2).setCellValue(averageSkills(sol));
                    row1.createCell(3).setCellValue(averageSpeciality(sol));
                    row1.createCell(4).setCellValue(count);
                    row1.createCell(5).setCellValue(averageCosts(sol));
                    row1.createCell(6).setCellValue(notFoundCount(sol));
                    row1.createCell(7).setCellValue(impossibleCount(sol));


                    // Informing finish
                    System.out.println("[" + index + "] finish");
                }
            });

        // Disable new tasks from being submitted
        executorService.shutdown();
        // Wait a while for existing tasks to terminate
        executorService.awaitTermination(1, TimeUnit.HOURS);

        FileOutputStream outputStream = new FileOutputStream("./excel.xlsx");
        workbook.write(outputStream);
        workbook.close();
        System.out.println("==Tests END==");
    }

    public static String getEstimate(int time) {
        Calendar estimate = Calendar.getInstance();
        estimate.setTimeInMillis(System.currentTimeMillis()+time);
        return new SimpleDateFormat("HH:mm:ss:SS").format(estimate.getTime());
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

    public static int notFoundCount(Solution[] sols) {
        int count = 0;
        for (Solution e : sols) {
            if(e == null || e.failReason == Solution.FailReason.TIMEOUT)
                count++;
        }
        return count;
    }

    public static int impossibleCount(Solution[] sols) {
        int count = 0;
        for (Solution e : sols) {
            if(e == null || e.failReason == Solution.FailReason.IMPOSSIBLE)
                count++;
        }
        return count;
    }

    public static double averageCosts(Solution[] sols) {
        int sum = 0;
        for (Solution e : sols) {
            sum+= (e != null)?e.cost:0;
        }
        return (double) sum/sols.length;
    }

    public static Solution[] tests(int count, int timeout) throws ExecutionException, InterruptedException {

        Solution[] sols = new Solution[count];

        for (int i = 0; i < count; i++) {
            //System.out.println("[tests] " + i + "/" + count);
            Instance instance = InstanceUtils.formatInstance(new InstanceGenerator().getInstance());
            sols[i] = new Solver(instance).solve(timeout).get();
        }
        return sols;
    }
}
