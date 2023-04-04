package com.javarush.model.animals;

import com.javarush.model.animals.abstracts.Animal;
import com.javarush.model.animals.abstracts.Herbivorous;
import com.javarush.model.animals.abstracts.AnimalType;
import com.javarush.utils.GameFactory;

import java.util.Map;

public class Horse extends Herbivorous {

    public Horse(GameFactory factory, AnimalType type) {
        super(factory, type);
    }
}
