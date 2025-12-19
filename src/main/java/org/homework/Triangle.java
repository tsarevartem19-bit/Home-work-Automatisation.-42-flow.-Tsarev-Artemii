package org.homework;

public class Triangle implements Shape {
    private double a, b, c;
    private String fillColor;
    private String borderColor;

    public Triangle(double a, double b, double c, String fillColor, String borderColor) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getArea() {
        // формула Герона
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double[] getSides() {
        return new double[]{a, b, c};
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
