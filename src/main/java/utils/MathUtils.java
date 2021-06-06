package utils;

import models.Coordinates;

public class MathUtils {
    public static double distance(Coordinates pointA, Coordinates pointB) {
        return Math.sqrt(Math.pow(pointA.x - pointB.x,2)+Math.pow(pointA.y-pointB.y,2));
    }

    // Return an array [standardDeviation, mean]
    public static double[] calculateSD(double numArray[])
    {
        double standardDeviation = 0.0;
        int length = numArray.length;

        double mean = average(numArray);

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return new double[] {Math.sqrt(standardDeviation/length), mean};
    }

    public static double average(double numArray[]) {
        double sum = 0.0;
        for(double num : numArray) {
            sum += num;
        }

        return sum/numArray.length;
    }

}
