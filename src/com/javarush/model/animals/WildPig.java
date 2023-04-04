package com.javarush.model.animals;

import com.javarush.model.animals.abstracts.Animal;
import com.javarush.model.animals.abstracts.AnimalType;
import com.javarush.model.animals.abstracts.Herbivorous;
import com.javarush.utils.GameFactory;

import java.util.Map;

public class WildPig extends Herbivorous {

    public WildPig(GameFactory factory, AnimalType type) {
        super(factory, type);
    }
}
