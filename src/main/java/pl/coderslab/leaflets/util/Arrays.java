package pl.coderslab.leaflets.util;

import pl.coderslab.leaflets.model.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Arrays {
    public static List<Point> transferToList(double[][] array) {
        List<Point> points = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            Point point = new Point();
            point.setLatitude(array[i][0]);
            point.setLongitude(array[i][1]);
            points.add(point);
        }
        return points;
    }

    public static void printArray(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i][0] + "  ");
            System.out.println(array[i][1]);
        }
    }

    public static double[][] transferToArray(List<Point> points) {
        double[][] pointsArrayFromList = new double[points.size()][2];
        for (int i = 0; i < points.size(); i++) {
            pointsArrayFromList[i][0] = points.get(i).getLatitude();
            pointsArrayFromList[i][1] = points.get(i).getLongitude();
        }
        return pointsArrayFromList;
    }
}
