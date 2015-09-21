package com.darzul.rdn;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DarzuL on 9/20/15.
 */
public class Splitter<E> {

    /**
     * @param clazz           Entity class
     * @param data            data
     * @param dataTestPercent percent of data to use for test between 0 and 1
     * @return 2 arrays [0] -> trainingBase; [1] -> testingBase
     */
    public List<E[]> split(Class<E> clazz, final E[] data, final float dataTestPercent) {

        List<E[]> bases = new ArrayList<>(2);

        final int testingData = (int) (data.length * dataTestPercent);
        final int trainingData = data.length - testingData;
        @SuppressWarnings("unchecked")
        final E[] testingBase = (E[]) Array.newInstance(clazz, testingData);
        @SuppressWarnings("unchecked")
        final E[] trainingBase = (E[]) Array.newInstance(clazz, trainingData);

        int line = 0;
        for (int position = 0; position < trainingData; line++, position++) {
            trainingBase[position] = data[line];
        }
        for (int position = 0; position < testingData; line++, position++) {
            testingBase[position] = data[line];
        }

        bases.add(trainingBase);
        bases.add(testingBase);

        return bases;
    }
}
