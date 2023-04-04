package com.javarush.model.animals;

import com.javarush.model.animals.abstracts.Animal;
import com.javarush.model.animals.abstracts.AnimalType;
import com.javarush.utils.GameFactory;

public class Caterpillar extends Animal {

    public Caterpillar(GameFactory instance, AnimalType caterpillar) {
        super(instance, caterpillar);
    }

    @Override
    public void move() {
        //doNothing
    }
}
