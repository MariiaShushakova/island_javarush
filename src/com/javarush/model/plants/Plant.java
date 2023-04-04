package com.javarush.model.plants;

import com.javarush.model.animals.abstracts.AnimalType;
import com.javarush.utils.GameFactory;
import com.javarush.utils.Random;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Plant {
    protected static final double AMOUNT = GameFactory.getInstance().getMaxAmount(AnimalType.PLANT);
    protected double weight;

    public Plant() {
        this.weight = Random.get(AMOUNT);
    }

    public void growth() {
        for (int i = 0; i < 10; i++) {
            if(weight < AMOUNT) {
                weight++;
            } else {
                break;
            }
        }
    }
}
