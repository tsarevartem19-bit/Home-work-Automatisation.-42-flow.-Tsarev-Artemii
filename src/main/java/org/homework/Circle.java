package org.homework;

public class Circle implements Shape {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double[] getSides() {
        return new double[]{2 * Math.PI * radius};
    }

    @Override
    public String getColorBackground() {
        return fillColor;
    }

    @Override
    public String getColorBorder() {
        return borderColor;
    }
}
