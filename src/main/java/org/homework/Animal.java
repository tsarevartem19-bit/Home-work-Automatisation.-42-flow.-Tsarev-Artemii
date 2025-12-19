package org.homework;

public class Animal{
    protected String name;

    protected static int animalCount = 0;

    public void run(int run) {

        System.out.println("Животное пробежало: " + run + " м.");
    }

    public void swim(int swim) {

        System.out.println("Животное проплыло: " + swim + " м.");
    }

    public Animal(String name){
        this.name = name;
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }
}