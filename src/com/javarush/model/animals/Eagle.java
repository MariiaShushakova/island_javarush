package com.javarush.model.animals;

import com.javarush.model.animals.abstracts.Animal;
import com.javarush.model.animals.abstracts.AnimalType;
import com.javarush.utils.GameFactory;

import java.util.Map;

public class Eagle extends Animal {

    public Eagle(GameFactory factory, AnimalType type) {
        super(factory, type);
    }
}
