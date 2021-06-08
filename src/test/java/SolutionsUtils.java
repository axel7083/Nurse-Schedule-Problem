import models.Solution;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SolutionsUtils {

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
}
