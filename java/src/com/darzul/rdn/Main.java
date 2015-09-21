package com.darzul.rdn;

import com.darzul.rdn.model.Person;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DarzuL on 9/19/15.
 */
public class Main {
    public static void main(String[] args) {
//        prepare();
        test();
    }

    private static void prepare() {
        List<String[]> data = new LinkedList<>();
        data.addAll(Parser.parse("/home/desktop/Documents/Git/fann/project/garcon.csv", ";"));
        data.addAll(Parser.parse("/home/desktop/Documents/Git/fann/project/fille.csv", ";"));
        Person[] persons = Person.buildFrom(data);

        Shuffler<Person> shuffler = new Shuffler<>();
        shuffler.shuffle(persons);

        String[] names = new String[persons.length];
        for (int line = 0; line < persons.length; line++) {
            Person person = persons[line];
            names[line] = person.getName();
        }

        char[] charset = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int[][] charsFrequency = Analyzer.computeCharsFrequency(names, charset);
        float[][] charsFrequencyNormalized = Analyzer.normalize(charsFrequency, 0, 1);
//        float[][] firstChar = Analyzer.computeFirstChar(names, charset);
//        float[][] lastChar = Analyzer.computeLastChar(names, charset);
//        float[][] twoLastCharsWithoutOrder = Analyzer.computeTwoLastCharWithoutOrder(names, charset);
        float[][] twoLastCharsWithOrder = Analyzer.computeTwoLastCharWithOrder(names, charset);
        for (int line = 0; line < persons.length; line++) {
            Person person = persons[line];
            person.addInputs(charsFrequencyNormalized[line]);
//            person.addInputs(firstChar[line]);
//            person.addInputs(lastChar[line]);
//            person.addInputs(twoLastCharsWithoutOrder[line]);
            person.addInputs(twoLastCharsWithOrder[line]);
        }

        Splitter<Person> splitter = new Splitter<>();
        List<Person[]> bases = splitter.split(Person.class, persons, 0.2f);

        Person[] trainingBase = bases.get(0);
        Person[] testingBase = bases.get(1);

        Converter.toFile("/home/desktop/Documents/Git/fann/project/base/training_base.txt", trainingBase, ' ');
        Converter.toFile("/home/desktop/Documents/Git/fann/project/base/testing_base.txt", testingBase, ' ');
    }

    private static void test() {
        float result = Tester.test("/home/desktop/Documents/Git/fann/project/result.txt", 0.5f);
        System.out.println("%: " + result);
    }
}
