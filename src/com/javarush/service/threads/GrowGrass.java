package com.javarush.service.threads;


import com.javarush.service.Field;

public class GrowGrass implements Runnable{

    private final Field field;

    public GrowGrass(Field field) {
        this.field = field;
    }

    @Override
    public void run() {
        synchronized (field) {
            field.growGrassInTheField();
        }
    }
}
