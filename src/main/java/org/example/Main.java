package org.example;

public class Main {

    public static void main(String[] args) {
        String arraySize [] [] = new String [3] [4];
        String[][] arrayData = {
                {"1", "2", "три", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        try {
            int value = matrix[3][0];
            System.out.println("Элемент: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Произошла ошибка: выход за границы двумерного массива!");
            System.out.println("Сообщение исключения: " + e.getMessage());
        }

        try {
            int value2 = matrix[1][5];
            System.out.println("Элемент: " + value2);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Произошла ошибка: выход за границы строки массива!");
            System.out.println("Сообщение исключения: " + e.getMessage());
        }

        try {
            checkArraySize(arraySize);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: " + e.getMessage());
        }

        try {
            int total = sumArray(arrayData);
            System.out.println("Сумма всех элементов: " + total);
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }

    }

    public static void checkArraySize(String array [][]) throws MyArraySizeException {
        if(array.length != 4){
            throw new MyArraySizeException("Массив должен быть 4x4");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Массив должен быть 4x4");
            }
        }
    }

    public static int sumArray(String array [] []) throws MyArrayDataException {
        int sum = 0;
        for(int i =0;i<array.length;i++){
            for(int j =0;j<array[i].length;j++){
                try{
                    sum += Integer.parseInt(array[i][j]);
                }
                catch (NumberFormatException e ){
                    throw new MyArrayDataException(i, j, array[i][j]);

                }
            }
        }
        return sum;

    }
}