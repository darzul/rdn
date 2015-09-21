package com.darzul.rdn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by DarzuL on 9/20/15.
 */
public final class Tester {

    public static float test(String resultFile, float threshHold) {
        int error = 0;
        int success = 0;

        BufferedReader br = null;
        try {
            String realLine;
            String calcLine;
            br = new BufferedReader(new FileReader(resultFile));
            while ((realLine = br.readLine()) != null) {
                calcLine = br.readLine();


                float realResult = Float.valueOf(realLine);
                float calcResult = Float.valueOf(calcLine);
                calcResult = calcResult > threshHold ? 1 : 0;

                if (realResult == calcResult) {
                    success++;
                } else {
                    error++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return (float) success / (error + success) * 100;
    }

    private Tester() {
    }
}
