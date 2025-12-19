package org.homework;

public class MainAnimals {
    public static void main(String[] args) {

        FoodBowl bowl = new FoodBowl(40);
        Cat catRuningAndSwiming = new Cat("Милка");
        catRuningAndSwiming.run(200);
        catRuningAndSwiming.run(201);
        catRuningAndSwiming.swim(1);

        Dog dog = new Dog("Лея");
        dog.run(500);
        dog.run(501);
        dog.swim(10);
        dog.swim(11);

        Cat[] cats = {
                new Cat("Барсик"),
                new Cat("Мурзик"),
                new Cat("Рыжик")
        };

        for (Cat cat : cats) {
            cat.eat(bowl, 15);
        }

        System.out.println(" Сытость котов");
        for (Cat cat : cats) {
            System.out.println(cat.name + " сытый: " + cat.isSatiety());
        }

        System.out.println("Осталось еды в миске: " + bowl.getFood());

        bowl.addFood(20);
        System.out.println("Добавили еду в миску, осталось: " + bowl.getFood() );
    }
}
