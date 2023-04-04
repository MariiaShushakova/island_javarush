package com.javarush.controller;

import com.javarush.service.Field;
import com.javarush.service.threads.EatPrayLove;
import com.javarush.service.threads.GrowGrass;
import com.javarush.service.threads.PrintStatistics;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
        Field field = Field.getInstance();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(new GrowGrass(field), 0, 2, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new EatPrayLove(field), 0, 2, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new PrintStatistics(field), 0, 1, TimeUnit.SECONDS);



        while (true) {
            try {
                Thread.sleep(3000);

                if (field.currentCountOfAnimal() == 0) {
                    scheduledExecutorService.shutdown();
                    System.out.println("Game over!");
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
