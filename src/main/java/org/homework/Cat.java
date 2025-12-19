package org.homework;


public class Cat extends Animal{
    private static int catCount = 0;
    private boolean satiety;

    public Cat(String name){
        super(name);
        this.satiety = false;
        catCount++;
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void eat(FoodBowl bowl,int amount){
        if (bowl.decreaseFood(amount)){
            System.out.println("Кошка по имени: " + name + " опустошила миску");
            satiety = true;
        }
        else{
            System.out.println("Кошка по имени: " + name + " не хочет есть");
        }
    }

    public static int getCatCount() {
        return catCount;
    }

    @Override
    public void run(int run){
        if (run <=200){
            System.out.println("Кошка по имени " + name + " пробежала: " + run + " м.");
        }
        else{
            System.out.println("Кошка по имени " + name + " не сможет  пробежать:" + run +" м.");
        }

    }

    @Override
    public void swim (int swim){

        System.out.println("Кошка по имени " + name +" не умеет плавать");
    }
}