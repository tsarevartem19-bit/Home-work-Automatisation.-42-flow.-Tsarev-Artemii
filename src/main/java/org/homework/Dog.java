package org.homework;


public class Dog extends Animal {
    private static int dogCount = 0;

    public Dog(String name){
        super(name);
        dogCount++;
    }

    public static int getDogCount(){
        return dogCount;
    }
    @Override
    public void run(int run){
        if (run <=500){
            System.out.println("Собака по имени " + name + " пробежала: " + run + " м.");
        }
        else{
            System.out.println("Собака по имени " + name + " не сможет  пробежать:" + run +" м.");
        }

    }

    @Override
    public void swim (int swim){
        if (swim <=10){
            System.out.println("Собака по имени " + name + " проплыл " + swim + " м.");
        }
        else{
            System.out.println("Собака по имени " + name + " на расстояние: " + swim + " м. не сможет проплыть");
        }
    }
}

