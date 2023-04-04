package com.javarush.model.animals.abstracts;

import com.javarush.service.Cell;
import com.javarush.service.Field;
import com.javarush.utils.Direction;
import com.javarush.utils.GameFactory;
import com.javarush.utils.Random;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class Animal {

    public AnimalType type;
    protected String icon;
    protected double weight;
    protected double speed;
    protected double food;
    protected Map<AnimalType, Integer> diet;
    public GameFactory factory;
    protected Cell currentCell;

    protected Animal(GameFactory factory, AnimalType type) {
        this.factory = factory;
        this.type = type;
        //this.icon = factory.getIcon(type); //TODO
        this.weight = factory.getWeight(type);
        this.speed = factory.getSpeed(type);

        double maxAmountOfFoodToFeed = factory.getMaxAmountOfFoodToFeed(type);
        this.food = food = Random.get(maxAmountOfFoodToFeed + 1);

        this.diet = diet;

    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
        currentCell.addAnimal(this);
    }

    private Cell chooseDirection() {
        List<Cell> optionCell = new ArrayList<>();

        for (Map.Entry<Direction, Cell> entry : this.currentCell.mapOfDirections.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getAmountTypeOfAnimal(this.type) < this.factory.getMaxAmount(this.type)) {
                    optionCell.add(entry.getValue());
                }
            }
        }
        if (optionCell.size() != 0) {
            int direction = Random.get(optionCell.size());
            return optionCell.get(direction);
        }
        return null;
    }

    public void move() {
        int countStep = (int) this.speed;

        for (int step = 1; step <= countStep; step++) {

            var cell = chooseDirection();
            if (cell != null) {
                this.currentCell.removeAnimal(this, false);
                this.currentCell = cell;
                cell.addAnimal(this);
            }
        }
    }

    public void eat() {
        ArrayList<Animal> listOfVictim = currentCell.
                getListOfVictim(factory.listOfTypesForEating(currentCell.getTypeOfCreatureOfTheCell(), type));

        if (this.food < factory.getMaxAmountOfFoodToFeed(type)) {
            //eatAnimal(listOfVictim);

            for (Animal victimAnimal : listOfVictim) {
                if (!wasItEaten(type, victimAnimal.type)) {
                    continue;
                }
                food += victimAnimal.getWeight();
                if (food > factory.getMaxAmountOfFoodToFeed(type)) {
                    food = factory.getMaxAmountOfFoodToFeed(type);
                }
                currentCell.removeAnimal(victimAnimal, true);
            }
        }
    }

    /** Percentage chance to perform some action */
    public boolean wasItEaten(AnimalType thisType, AnimalType victimType){

        double n = Random.get(101);
        return (n <= factory.getChance(thisType, victimType));
    }


    public void reproduce() {
        Map<AnimalType, Integer> mapCountDifferentTypeOfAnimal = this.currentCell.getMapCountDifferentTypeOfAnimal();

        for (Map.Entry<AnimalType, Integer> entry : mapCountDifferentTypeOfAnimal.entrySet()) {
            if (this.type.equals(entry.getKey())) {
                int newAnimals = (int) (entry.getValue() / 2); //no gender

                if (this.factory.getMaxAmount(this.type) > entry.getValue()) {
                    for (int i = 0; i < newAnimals; i++) {
                        Field.createdChildAnimal(this.currentCell, this.type);
                    }
                }
            }
        }
    }

    public void becomeHungry() {
        this.food -= factory.getMaxAmountOfFoodToFeed(this.type) * 0.1; //each cycle animal loose 0.1 of their food
        if (this.food < 0) { // and dies if they are completely hungry
            currentCell.removeAnimal(this, true);
        }
    }

    @Override
    public String toString() {
        return icon;
    }
}
