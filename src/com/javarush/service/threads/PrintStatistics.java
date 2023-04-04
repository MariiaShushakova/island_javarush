package com.javarush.service.threads;

import com.javarush.service.Field;

public class PrintStatistics implements Runnable{

    private final Field field;

    public PrintStatistics(Field field) {

        this.field = field;
    }

    @Override
    public void run() {
        synchronized (field) {
            field.printStatistics();
        }
    }
}
