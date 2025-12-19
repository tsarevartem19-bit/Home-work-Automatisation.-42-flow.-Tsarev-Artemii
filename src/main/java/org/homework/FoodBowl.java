package org.homework;

public class FoodBowl {
    int catFood;

    public FoodBowl(int catFood) {
        this.catFood = catFood;
    }

    public void addFood(int amount) {
        catFood += amount;
    }

    public boolean decreaseFood(int amount) {
        if (catFood >= amount) {
            catFood -= amount;
            return true;
        } else {
            return false;
        }
    }

    public int getFood() {
        return catFood;
    }
}
