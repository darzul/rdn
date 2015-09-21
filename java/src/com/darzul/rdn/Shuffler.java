package com.darzul.rdn;

import java.util.Random;

/**
 * Created by DarzuL on 9/20/15.
 */
public class Shuffler<T> {

    public void shuffle(T[] data) {
        Random random = new Random();

        for (int index = 0; index < data.length; index++) {
            int randomIndex = random.nextInt(index + 1);
            T tmp = data[index];
            data[index] = data[randomIndex];
            data[randomIndex] = tmp;
        }
    }
}
