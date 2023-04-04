package com.javarush.service.threads;


import com.javarush.service.Field;

public class EatPrayLove implements Runnable {

    private final Field field;

    public EatPrayLove(Field field) {
        this.field = field;
    }

    @Override
    public void run() {
        synchronized (field) {
            field.animalsEat();
            field.animalsReproduced();
            field.animalsMove();
            field.animalsBecomeHungry();
        }
    }
}
