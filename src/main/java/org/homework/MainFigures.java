package org.homework;

public class MainFigures {
    public static void main(String[] args) {

        Shape[] shapes = {
                new Circle(5, "Красный", "Черный"),
                new Rectangle(4, 6, "Синий", "Белый"),
                new Triangle(3, 4, 5, "Зеленый", "Черный")
        };

        for (Shape shape : shapes) {
            System.out.println("Фигура: " + shape.getClass().getSimpleName());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println("Периметр: " + shape.getPerimeter());
            System.out.println("Цвет фона: " + shape.getColorBackground());
            System.out.println("Цвет границы: " + shape.getColorBorder());
        }
    }
}
