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

    interface Middle {
        XSSFSheet createSheet(XSSFWorkbook workbook );
        void headers(Row header, int count);
        Solution[] compute(int index) throws ExecutionException, InterruptedException;
        void completeRow(Row row, int index, int count);
    }

    public static void main(String[] args) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            // Run test by timeout
            TimeoutTests(workbook, 15);

            // Run test by learner count
            LearnerTests(workbook, 15);

            // Run test by interface count
            InterfaceTests(workbook, 15);

            // Learner AND interface with proportionality
            ProportionalTests(workbook, 15);

            FileOutputStream outputStream = new FileOutputStream("./analyse.xlsx");
            workbook.write(outputStream);
            workbook.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("==Tests END==");
    }

    // Run test by timeout (making only timouts varies)
    private static void TimeoutTests(XSSFWorkbook workbook, int iteration) throws IOException, InterruptedException {
        runTests(workbook, iteration, new Middle() {
            final int count = 50;
            final int timeStep = 100;

            @Override
            public XSSFSheet createSheet(XSSFWorkbook workbook) {
                return workbook.createSheet("Timeout parameter");
            }

            @Override
            public void headers(Row header, int count) {
                header.createCell(count++).setCellValue("Time (ms)");
                header.createCell(count++).setCellValue("Formations count");
                header.createCell(count).setCellValue("Run count");
            }

            @Override
            public Solution[] compute(int index) throws ExecutionException, InterruptedException {
                // Printing end estimation
                System.out.println("[" + index + "][TimeoutTests] started estimate end ~" + getTime(index*timeStep*count));
                return run(count, index * timeStep, null);
            }

            @Override
            public void completeRow(Row row, int index, int count) {
                System.out.println("["+index+"]" + index * timeStep + ", 80, " + this.count);
                row.createCell(count++).setCellValue(index * timeStep);
                row.createCell(count++).setCellValue(80); // Default
                row.createCell(count).setCellValue(this.count); // Default
            }
        });
    }

    // Make the number of learners varies
    private static void LearnerTests(XSSFWorkbook workbook, int iteration) throws IOException, InterruptedException {
        runTests(workbook, iteration, new Middle() {
            final int count = 50;
            final int step = 5;
            final int timeout = 200;
            final int nb_interface = 5;

            @Override
            public XSSFSheet createSheet(XSSFWorkbook workbook) {
                return workbook.createSheet("Learner parameters");
            }

            @Override
            public void headers(Row header, int count) {
                header.createCell(count++).setCellValue("Time (ms)");
                header.createCell(count++).setCellValue("Formations count");
                header.createCell(count++).setCellValue("Interface count");
                header.createCell(count).setCellValue("Run count");
            }

            @Override
            public Solution[] compute(int index) throws ExecutionException, InterruptedException {

                InstanceGenerator.Argument argument = new InstanceGenerator.Argument();
                argument.nb_learner = index*step;
                argument.nb_interface = nb_interface;
                argument.dimensionArea = 200;
                argument.nb_centres = 5;
                argument.name_speciality = null;

                // Printing end estimation
                System.out.println("[" + index + "][LearnerTests] started estimate end ~" + getTime(timeout*count));
                return run(count, timeout, argument);
            }

            @Override
            public void completeRow(Row row, int index, int count) {
                row.createCell(count++).setCellValue(timeout);
                row.createCell(count++).setCellValue(index*step);
                row.createCell(count++).setCellValue(nb_interface);
                row.createCell(count).setCellValue(this.count);
            }
        });
    }

    // Make the number of interface varies
    private static void InterfaceTests(XSSFWorkbook workbook, int iteration) throws IOException, InterruptedException {
        runTests(workbook, iteration, new Middle() {
            final int count = 50;
            final int step = 3;
            final int timeout = 200;
            final int nb_learners = 80;

            @Override
            public XSSFSheet createSheet(XSSFWorkbook workbook) {
                return workbook.createSheet("Interface parameters");
            }

            @Override
            public void headers(Row header, int count) {
                header.createCell(count++).setCellValue("Time (ms)");
                header.createCell(count++).setCellValue("Formations count");
                header.createCell(count++).setCellValue("Interface count");
                header.createCell(count).setCellValue("Run count");
            }

            @Override
            public Solution[] compute(int index) throws ExecutionException, InterruptedException {

                InstanceGenerator.Argument argument = new InstanceGenerator.Argument();
                argument.nb_learner = nb_learners;
                argument.nb_interface = index*step;
                argument.dimensionArea = 200;
                argument.nb_centres = 5;
                argument.name_speciality = null;

                // Printing end estimation
                System.out.println("[" + index + "][LearnerTests] started estimate end ~" + getTime(timeout*count));
                return run(count, timeout, argument);
            }

            @Override
            public void completeRow(Row row, int index, int count) {
                row.createCell(count++).setCellValue(timeout);
                row.createCell(count++).setCellValue(nb_learners);
                row.createCell(count++).setCellValue(index*step);
                row.createCell(count).setCellValue(this.count);
            }
        });
    }

    // Run test by timeout (making only timouts varies)
    private static void ProportionalTests(XSSFWorkbook workbook, int iteration) throws IOException, InterruptedException {
        runTests(workbook, iteration, new Middle() {
            final int count = 50;
            final int timeStep = 5;
            final int timeout = 200;

            @Override
            public XSSFSheet createSheet(XSSFWorkbook workbook) {
                return workbook.createSheet("Proportional parameter");
            }

            @Override
            public void headers(Row header, int count) {
                header.createCell(count++).setCellValue("Time (ms)");
                header.createCell(count++).setCellValue("Learner count");
                header.createCell(count++).setCellValue("interface count");
                header.createCell(count).setCellValue("Run count");
            }

            @Override
            public Solution[] compute(int index) throws ExecutionException, InterruptedException {
                InstanceGenerator.Argument argument = new InstanceGenerator.Argument();
                argument.nb_learner = timeStep*index;
                argument.nb_interface = (int) (argument.nb_learner /4 * 1.2);
                argument.dimensionArea = 200;
                argument.nb_centres = 5;
                argument.name_speciality = null;

                // Printing end estimation
                System.out.println("[" + index + "][LearnerTests] started estimate end ~" + getTime(timeout*count));
                return run(count, timeout, argument);
            }

            @Override
            public void completeRow(Row row, int index, int count) {
                System.out.println("["+index+"]" + index * timeStep + ", 80, " + this.count);
                row.createCell(count++).setCellValue(timeout);
                row.createCell(count++).setCellValue(timeStep*index);
                row.createCell(count++).setCellValue((int) (timeStep*index /4 * 1.2)); // Default
                row.createCell(count).setCellValue(this.count); // Default
            }
        });
    }


    public static void runTests(XSSFWorkbook workbook, int iteration, Middle middle) throws IOException, InterruptedException {

        XSSFSheet sheet = middle.createSheet(workbook);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Matching skills (Average)");
        header.createCell(1).setCellValue("Matching speciality (Average)");
        header.createCell(2).setCellValue("Average cost");
        header.createCell(3).setCellValue("Not found");
        header.createCell(4).setCellValue("Impossible");

        // Using middle ware to set additional header
        middle.headers(header, 5);

        int numOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Running on " +numOfCores + " cores");
        ExecutorService executorService = Executors.newFixedThreadPool(numOfCores);

        ConcurrentLinkedQueue<Integer> queues = new ConcurrentLinkedQueue<>();

        Row[] rows = new Row[iteration];
        // Adding to the queue the value to test
        for(int i = 1 ; i < iteration; i ++) {
            queues.add(i);
            rows[i-1] = sheet.createRow(i); // Creating row
        }

        // Starting @numOfCores process
        for(int i = 0 ; i < Math.min(iteration, numOfCores); i ++)
            executorService.submit(() -> {

                while(!queues.isEmpty()) {
                    // Getting element from the queue
                    int index = queues.poll();

                    // Computing solution
                    Solution[] sol;
                    try {
                        sol = middle.compute(index);
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                        continue;
                    }

                    // Filling the rows with basic data
                    System.out.println("[" + index + "] " + SolutionsUtils.averageSkills(sol) + " " + SolutionsUtils.averageSpeciality(sol) + " " + SolutionsUtils.averageCosts(sol) + " " + SolutionsUtils.notFoundCount(sol) + " " + SolutionsUtils.impossibleCount(sol));
                    rows[index-1].createCell(0).setCellValue(SolutionsUtils.averageSkills(sol));
                    rows[index-1].createCell(1).setCellValue(SolutionsUtils.averageSpeciality(sol));
                    rows[index-1].createCell(2).setCellValue(SolutionsUtils.averageCosts(sol));
                    rows[index-1].createCell(3).setCellValue(SolutionsUtils.notFoundCount(sol));
                    rows[index-1].createCell(4).setCellValue(SolutionsUtils.impossibleCount(sol));

                    // Ask the interface to fill more if needed
                    middle.completeRow(rows[index-1], index, 5);

                    // Informing finish
                    System.out.println("[" + index + "] finish");
                }
            });

        // Disable new tasks from being submitted
        executorService.shutdown();
        // Wait a while for existing tasks to terminate (one hour as a limit)
        executorService.awaitTermination(1, TimeUnit.HOURS);
    }

    public static Solution[] run(int count, int timeout, InstanceGenerator.Argument argument) throws ExecutionException, InterruptedException {
        Solution[] sols = new Solution[count];
        for (int i = 0; i < count; i++) {
            Instance instance = InstanceUtils.formatInstance(new InstanceGenerator(argument).getInstance());
            sols[i] = new Solver(instance).solve(timeout).get();
        }
        return sols;
    }

    public static String getTime(int time) {
        Calendar estimate = Calendar.getInstance();
        estimate.setTimeInMillis(System.currentTimeMillis()+time);
        return new SimpleDateFormat("HH:mm:ss:SS").format(estimate.getTime());
    }
}
