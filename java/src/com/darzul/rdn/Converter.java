package com.darzul.rdn;

import com.darzul.rdn.model.AnnEntity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by DarzuL on 9/20/15.
 */
public class Converter {

    public static void toFile(String fileName, AnnEntity[] entities, char separator) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName));

            int nbData = entities.length;
            int nbInput = entities[0].getInputs().size();
            int nbOutput = entities[0].getOutputs().split(" ").length;
            bw.write(String.valueOf(nbData) + ' ' + String.valueOf(nbInput) + ' ' + String.valueOf(nbOutput) + '\n');
            for (AnnEntity entity : entities) {
                bw.write(toString(entity.getInputs(), separator) + '\n');
                bw.write(entity.getOutputs() + '\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String toString(List<Float> inputs, char separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (float input : inputs) {
            stringBuilder //
                    .append(String.valueOf(input)) //
                    .append(separator);
        }
        return stringBuilder //
                .deleteCharAt(stringBuilder.length() - 1) //
                .toString();
    }

    private Converter() {
    }
}
