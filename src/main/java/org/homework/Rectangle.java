package org.homework;

public class Rectangle implements Shape {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double[] getSides() {
        return new double[]{width, height, width, height};
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
