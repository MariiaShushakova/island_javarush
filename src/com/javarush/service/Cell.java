package com.javarush.service;

import com.javarush.model.animals.abstracts.Animal;
import com.javarush.model.animals.abstracts.AnimalType;
import com.javarush.model.plants.Plant;
import com.javarush.utils.Direction;
import com.javarush.view.Stats;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Cell {
    public final String name;
    private int row;
    private int col;

    public Plant grass;
    private List<Animal> animals = new ArrayList<>();
    public Map<Direction, Cell> mapOfDirections;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.name = this.row + "_" + this.col;
        this.grass = new Plant();
        this.mapOfDirections = new HashMap<>() {{
            put(Direction.TOP, null);
            put(Direction.BOTTOM, null);
            put(Direction.LEFT, null);
            put(Direction.RIGHT, null);
        }};
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    /** Animal dies */
    public void removeAnimal(Animal animal, boolean collectStatistics) {
        if (collectStatistics) {
            Stats.mapDeathAnimal.compute(animal.type, (a, b) -> b + 1);
        }
        animals.remove(animal);
    }

    public Animal[] getAllAnimalFromCurrentCell() {
        return animals.toArray(new Animal[animals.size()]);
    }

    public int getCountOfAnimalOfTheCell() {
        return animals.size();
    }

    public int getAmountTypeOfAnimal(AnimalType type) {
        int countTypeOfAnimal = 0;

        for (Animal animal : animals) {
            if (type.equals(animal.type)) {
                countTypeOfAnimal++;
            }
        }
        return countTypeOfAnimal;
    }

    public ArrayList<Animal> getListOfVictim(ArrayList<AnimalType> types) {
        ArrayList<Animal> listOfVictim = new ArrayList<>();

        for (AnimalType type : types) {
            for (Animal animal : this.animals) {
                if (type.equals(animal.type)) {
                    listOfVictim.add(animal);
                }
            }
        }
        Collections.shuffle(listOfVictim);
        return listOfVictim;
    }

    public ArrayList<AnimalType> getTypeOfCreatureOfTheCell() {
        ArrayList<AnimalType> typeOfAnimalOfTheCell = new ArrayList<>();

        for (Animal animal : animals) {
            if (!typeOfAnimalOfTheCell.contains(animal.type)) {
                typeOfAnimalOfTheCell.add(animal.type);
            }
        }
        return typeOfAnimalOfTheCell;
    }

    public Map<AnimalType, Integer> getMapCountDifferentTypeOfAnimal() {
        Map<AnimalType, Integer> mapCountDifferentTypeOfAnimal = new HashMap<>();

        for (AnimalType type : getTypeOfCreatureOfTheCell()) {
            mapCountDifferentTypeOfAnimal.put(type, getNumberAnimalsOfTheSameType(type));
        }
        return mapCountDifferentTypeOfAnimal;
    }

    public int getNumberAnimalsOfTheSameType(AnimalType type) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal.type.equals(type)) {
                count++;
            }
        }
        return count;
    }
    public int getCountTheTypeOfAnimalOfTheCell(AnimalType type) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal.type.equals(type)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "Cell{" + name + '\'' +
                ", animals=" + animals +
                '}';
    }
}
