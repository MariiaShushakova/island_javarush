package com.javarush.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Random {

    public static int get(double max) {

        return ThreadLocalRandom.current().nextInt((int) max);
    }
}
