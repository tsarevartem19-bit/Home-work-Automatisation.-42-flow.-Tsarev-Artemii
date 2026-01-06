package org.example;

public class Triangle {

    public double squareTriangle(int base,int height){
        if(base <= 0){
            throw new IllegalArgumentException("Основание не может быть отрицательной или равной нулю");
        }

        if(height<= 0){
            throw new IllegalArgumentException("Высота не может быть отрицательной или равной нулю");
        }

        return (base * height) / 2.0;
    }
}

