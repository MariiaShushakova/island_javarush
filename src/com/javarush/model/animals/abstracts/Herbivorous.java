package com.javarush.model.animals.abstracts;

import com.javarush.model.animals.abstracts.Animal;
import com.javarush.utils.GameFactory;

import java.util.ArrayList;
import java.util.Set;

public abstract class Herbivorous extends Animal{

    public Herbivorous(GameFactory factory, AnimalType type) {
        super(factory, type);
    }

    public void eat() {
        if (this.food < factory.getMaxAmountOfFoodToFeed(type)) {

            double grassWeight = currentCell.grass.getWeight();
            if (grassWeight > 0) {
                this.food += 1;
                this.currentCell.grass.setWeight(grassWeight - 1);
            };
        }

    }
}
