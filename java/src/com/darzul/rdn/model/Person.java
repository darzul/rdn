package com.darzul.rdn.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DarzuL on 9/20/15.
 */
public class Person implements AnnEntity {
    public static final int GENDER_MALE = 0;
    public static final int GENDER_FEMALE = 1;

    private String mName;
    private int mGender;
    List<Float> mInputs = new LinkedList<>();

    public static Person[] buildFrom(List<String[]> data) {
        Person[] persons = new Person[data.size()];
        for (int line = 0; line < data.size(); line++) {
            String[] row = data.get(line);
            Person person = new Person(row[0], Integer.valueOf(row[1]));
            persons[line] = person;
        }
        return persons;
    }

    public Person(String name, int gender) {
        mName = name;
        mGender = gender;
    }

    public String getName() {
        return mName;
    }

    public int getGender() {
        return mGender;
    }

    @Override
    public void addInputs(float[] newInputs) {
        for (Float newInput : newInputs) {
            mInputs.add(newInput);
        }
    }

    @Override
    public List<Float> getInputs() {
        return mInputs;
    }

    @Override
    public String getOutputs() {
        return String.valueOf(mGender);
    }
}
