package org.example;

public class Factorial {

    public int factorial (int number){
        if(number == 0){
            return 1;
        }

        if (number < 0){
            throw new IllegalArgumentException("Факториал отрицательного числа не существует");
        }
        int result = 1;

        for (int i = 1; i <= number; i++){
            result = i * result;
        }
        return result;
    }

}
