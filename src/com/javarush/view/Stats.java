package com.javarush.view;

import com.javarush.model.animals.abstracts.Animal;
import com.javarush.model.animals.abstracts.AnimalType;

import java.util.HashMap;
import java.util.Map;

public class Stats {
    public static Map<AnimalType, Integer> mapBirthNewAnimal;
    public static Map<AnimalType, Integer> mapDeathAnimal;

    public Stats() {
        mapBirthNewAnimal = getMap();
        mapDeathAnimal = getMap();
    }

    private static Map<AnimalType, Integer> getMap() {
        Map<AnimalType, Integer> basicMap = new HashMap<>();
        AnimalType[] types = AnimalType.values();

        for (AnimalType type : types) {
            if (!type.equals(AnimalType.PLANT)){
                basicMap.put(type, 0);
            }
        }
        return basicMap;
    }
}
