package org.homework;

public interface Shape {
    double getArea();
    default double getPerimeter() {
        double sum = 0;
        for (double side : getSides()) {
            sum += side;
        }
        return sum;
    }
    String getColorBackground();
    String getColorBorder();
    double[] getSides();
}
