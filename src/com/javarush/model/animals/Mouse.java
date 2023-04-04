package com.javarush.model.animals;

import com.javarush.model.animals.abstracts.Animal;
import com.javarush.model.animals.abstracts.Herbivorous;
import com.javarush.model.animals.abstracts.AnimalType;
import com.javarush.utils.GameFactory;
import com.javarush.utils.Random;

import java.util.Map;

public class Mouse extends Herbivorous {

    public Mouse(GameFactory factory, AnimalType type) {
        super(factory, type);
        this.food = Random.get(101) * 0.01/100;
    }
}
