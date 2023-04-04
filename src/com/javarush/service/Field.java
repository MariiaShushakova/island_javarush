package com.javarush.service;

import com.javarush.model.animals.*;
import com.javarush.model.animals.abstracts.Animal;
import com.javarush.model.animals.abstracts.AnimalType;
import com.javarush.utils.GameFactory;
import com.javarush.utils.Random;
import com.javarush.view.Stats;

import java.util.*;

public class Field{
    private static Field field = null; //Singleton
    private GameFactory factory;
    private static Stats statistics;
    private CellConfig cellConfig;;

    private Field(int length, int width) {
        this.cellConfig = new CellConfig(length, width);
        this.factory = new GameFactory();
        statistics = new Stats();

        fillTheField();
        System.out.println("Number of animals on the field is: " + currentCountOfAnimal());
        System.out.println("Number of grass on the field is: " + countGrass());
    }

    public static Field getInstance(){
        if (field == null) {
            field = new Field(20, 20);
        }
        return field;
    }

    /**
     * Put animals on the field before game.*/
    private void fillTheField() {
        for (Cell cell : cellConfig.getAllCell()) {
            AnimalType[] types = AnimalType.values();

            for (AnimalType type : types) {
                if (!type.equals(AnimalType.PLANT)) {
                    int countAnimalOfCell = Random.get(factory.getMaxAmount(type));
                    for (int i = 0; i < countAnimalOfCell; i++) {
                        Animal animal = createAnimal(type);
                        animal.setCurrentCell(cell);
                    }
                }
            }
        }
    }
    public static Animal createAnimal(AnimalType type) {
        return switch (type) {
            case CATERPILLAR -> new Caterpillar(GameFactory.getInstance(), AnimalType.CATERPILLAR);
            case BEAR -> new Bear(GameFactory.getInstance(), AnimalType.BEAR);
            case BOA -> new Boa(GameFactory.getInstance(), AnimalType.BOA);
            case EAGLE -> new Eagle(GameFactory.getInstance(), AnimalType.EAGLE);
            case FOX -> new Fox(GameFactory.getInstance(), AnimalType.FOX);
            case WILDPIG -> new WildPig(GameFactory.getInstance(), AnimalType.WILDPIG);
            case BUFFALOES -> new Buffaloes(GameFactory.getInstance(), AnimalType.BUFFALOES);
            case DEER -> new Deer(GameFactory.getInstance(), AnimalType.DEER);
            case DUCK -> new Duck(GameFactory.getInstance(), AnimalType.DUCK);
            case GOAT -> new Goat(GameFactory.getInstance(), AnimalType.GOAT);
            case HORSE -> new Horse(GameFactory.getInstance(), AnimalType.HORSE);
            case MOUSE -> new Mouse(GameFactory.getInstance(), AnimalType.MOUSE);
            case RABBIT -> new Rabbit(GameFactory.getInstance(), AnimalType.RABBIT);
            case SHEEP -> new Sheep(GameFactory.getInstance(), AnimalType.SHEEP);
            default -> new Wolf(GameFactory.getInstance(), AnimalType.WOLF);
        };
    }

    public int currentCountOfAnimal() {
        int count = 0;
        for (Cell cell :cellConfig.getAllCell()) {
            count += cell.getCountOfAnimalOfTheCell();
        }
        return count;
    }

    public double countGrass() {
        double count = 0;
        for (Cell cell : cellConfig.getAllCell()) {
            count += cell.grass.getWeight();
        }
        return count;
    }

    public static void createdChildAnimal(Cell cell, AnimalType type) {
        Animal animalChild = createAnimal(type);
        animalChild.setCurrentCell(cell);
        Stats.mapBirthNewAnimal.compute(type, (a, b) -> b+1);
    }

    public void animalsMove() {
        for (Cell cell : cellConfig.getAllCell()) {
            for (Animal animal : cell.getAllAnimalFromCurrentCell()) {
                animal.move();
            }
        }
    }

    public void animalsEat() {
        for (Cell cell : cellConfig.getAllCell()) {
            for (Animal animal : cell.getAllAnimalFromCurrentCell()) {
                animal.eat();
            }
        }
    }

    public void animalsReproduced() {
        for (Cell cell : cellConfig.getAllCell()) {
            ArrayList<AnimalType> types = new ArrayList<>();
            for (Animal animal : cell.getAllAnimalFromCurrentCell()) {
                if (!types.contains(animal.type)){
                    animal.reproduce();
                }
                types.add(animal.type);
            }
        }
    }

    public void  animalsBecomeHungry() {
        for (Cell cell : cellConfig.getAllCell()) {
            for (Animal animal : cell.getAllAnimalFromCurrentCell()) {
                animal.becomeHungry();
            }
        }
    }

    public void growGrassInTheField() {
        for (Cell cell : cellConfig.getAllCell()) {
            cell.grass.growth();
        }
    }

    public void printStatistics() {
        AnimalType[] types = AnimalType.values();
        StringBuilder result = new StringBuilder();
        for (AnimalType type : types) {
            if (!type.equals(AnimalType.PLANT)) {
                int countAllAnimal = 0;
                for (Cell cell : cellConfig.getAllCell()) {
                    countAllAnimal += cell.getCountTheTypeOfAnimalOfTheCell(type);
                }
                int countBornAnimal = Stats.mapBirthNewAnimal.get(type);
                int countDeadAnimal = Stats.mapDeathAnimal.get(type);
                result.append(type.name()).append(":").append(countAllAnimal).append("/+").append(countBornAnimal).append("/-").append(countDeadAnimal).append("; ");
            }
        }
        System.out.println(result);
    }

}
