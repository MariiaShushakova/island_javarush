package com.javarush.model.animals;

import com.javarush.model.animals.abstracts.Animal;
import com.javarush.model.animals.abstracts.AnimalType;
import com.javarush.utils.GameFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Bear extends Animal {

    public Bear(GameFactory instance, AnimalType bear) {
        super(instance,bear);
    }
}
