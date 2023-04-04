package com.javarush.utils;

import com.javarush.model.animals.abstracts.AnimalType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.javarush.utils.ConfigCharacters.*;

public class GameFactory {
    private static GameFactory factory;

    public static GameFactory getInstance(){
        if (factory == null) {
            factory = new GameFactory();
        }
        return factory;
    }

    //TODO Config
    private Map<AnimalType, double[]> animalCharacteristics() {

        Map<AnimalType, double[]> animalCharacteristics = new HashMap<>() {{
            put(AnimalType.BEAR,        new double[]{BEAR_WEIGHT, BEAR_CAPACITY, BEAR_SPEED, BEAR_FOOD});
            put(AnimalType.BOA,         new double[]{    15,     30,   1,      3});
            put(AnimalType.EAGLE,       new double[]{     6,     20,   3,      1});
            put(AnimalType.FOX,         new double[]{     8,     30,   2,      2});
            put(AnimalType.WOLF,        new double[]{    50,     30,   3,      8});
            put(AnimalType.WILDPIG,     new double[]{   400,     50,   2,     50});
            put(AnimalType.BUFFALOES,   new double[]{   700,     10,   3,    100});
            put(AnimalType.CATERPILLAR, new double[]{  0.01,   1000,   0,      0});
            put(AnimalType.DEER,        new double[]{   300,     20,   4,     50});
            put(AnimalType.DUCK,        new double[]{     1,    200,   4,   0.15});
            put(AnimalType.GOAT,        new double[]{    60,    140,   3,     10});
            put(AnimalType.HORSE,       new double[]{   400,     20,   4,     60});
            put(AnimalType.MOUSE,       new double[]{  0.05,    500,   1,   0.01});
            put(AnimalType.RABBIT,      new double[]{     2,    150,   2,   0.45});
            put(AnimalType.SHEEP,       new double[]{    70,    140,   3,     15});
            put(AnimalType.PLANT,       new double[]{     1,    200,   0,      0});

        }};
        return animalCharacteristics;
    }

    public double getWeight(AnimalType type) {
        return animalCharacteristics().getOrDefault(type, null)[0];
    }
    public double getMaxAmount(AnimalType type) {
        return animalCharacteristics().getOrDefault(type, null)[1];
    }

    public double getSpeed(AnimalType type) {
        return animalCharacteristics().getOrDefault(type, null)[2];
    }

    public double getMaxAmountOfFoodToFeed(AnimalType type) {
        return animalCharacteristics().getOrDefault(type, null)[3];
    }

    private Map<AnimalType, Integer[]> diets() {

        Map<AnimalType, Integer[]> diets = new HashMap<>() {{
            put(AnimalType.WOLF,        new Integer[]{ null,     0,     0,     0,     0,    10,    15,    60,    80,    60,    70,    15,    10,    40,     0,    0});
            put(AnimalType.BOA,         new Integer[]{    0,  null,    15,     0,     0,     0,     0,    20,    40,     0,     0,     0,     0,    10,     0,    0});
            put(AnimalType.FOX,         new Integer[]{    0,     0,  null,     0,     0,     0,     0,    70,    90,     0,     0,     0,     0,    60,    40,    0});
            put(AnimalType.BEAR,        new Integer[]{    0,    80,     0,  null,     0,    40,    80,    80,    90,    70,    70,    50,    20,    10,     0,    0});
            put(AnimalType.EAGLE,       new Integer[]{    0,     0,    10,     0,  null,     0,     0,    90,    90,     0,     0,     0,     0,    80,     0,    0});
            put(AnimalType.HORSE,       new Integer[]{    0,     0,     0,     0,     0,  null,     0,     0,     0,     0,     0,     0,     0,     0,     0,  100});
            put(AnimalType.DEER,        new Integer[]{    0,     0,     0,     0,     0,     0,  null,     0,     0,     0,     0,     0,     0,     0,     0,  100});
            put(AnimalType.RABBIT,      new Integer[]{    0,     0,     0,     0,     0,     0,     0,  null,     0,     0,     0,     0,     0,     0,     0,  100});
            put(AnimalType.MOUSE,       new Integer[]{    0,     0,     0,     0,     0,     0,     0,     0,  null,     0,     0,     0,     0,     0,    90,  100});
            put(AnimalType.GOAT,        new Integer[]{    0,     0,     0,     0,     0,     0,     0,     0,     0,  null,     0,     0,     0,     0,     0,  100});
            put(AnimalType.SHEEP,       new Integer[]{    0,     0,     0,     0,     0,     0,     0,     0,     0,     0,  null,     0,     0,     0,     0,  100});
            put(AnimalType.WILDPIG,     new Integer[]{    0,     0,     0,     0,     0,     0,     0,     0,    50,     0,     0,  null,     0,     0,    90,  100});
            put(AnimalType.BUFFALOES,   new Integer[]{    0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,  null,     0,     0,  100});
            put(AnimalType.DUCK,        new Integer[]{    0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,  null,    90,  100});
            put(AnimalType.CATERPILLAR, new Integer[]{    0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,  100});
            put(AnimalType.PLANT,       new Integer[]{    0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,    0});
        }};
        return diets;
    }

    public Integer getChance(AnimalType thisType, AnimalType type) {
        return switch (type) {
            case WOLF -> diets().get(thisType)[0];
            case BOA -> diets().get(thisType)[1];
            case FOX -> diets().get(thisType)[2];
            case BEAR -> diets().get(thisType)[3];
            case EAGLE -> diets().get(thisType)[4];
            case HORSE -> diets().get(thisType)[5];
            case DEER -> diets().get(thisType)[6];
            case RABBIT -> diets().get(thisType)[7];
            case MOUSE -> diets().get(thisType)[8];
            case GOAT -> diets().get(thisType)[9];
            case SHEEP -> diets().get(thisType)[10];
            case WILDPIG -> diets().get(thisType)[11];
            case BUFFALOES -> diets().get(thisType)[12];
            case DUCK -> diets().get(thisType)[13];
            case CATERPILLAR -> diets().get(thisType)[14];
            default -> diets().get(thisType)[15];
        };
    }

    public ArrayList<AnimalType> listOfTypesForEating(ArrayList<AnimalType> typeOfAnimalInTheCell, AnimalType thisType) {
        ArrayList<AnimalType> typeForEating = new ArrayList<>();

        for (AnimalType type : typeOfAnimalInTheCell) {
            for (Map.Entry<AnimalType, Integer[]> entry : diets().entrySet()) {
                if (entry.getKey().equals(type)) {

                    if (getChance(thisType, type) == null || getChance(thisType, type) == 0) {
                    }
                    else {
                        typeForEating.add(type);
                    }
                }
            }
        }
        return typeForEating;
    }
}
