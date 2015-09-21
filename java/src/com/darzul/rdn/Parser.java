package com.darzul.rdn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by desktop on 9/20/15.
 */
public final class Parser {

    public static List<String[]> parse(final String fileName, final String splitChar) {
        List<String[]> result = new LinkedList<>();
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                result.add(line.split(splitChar));
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

        return result;
    }

    private Parser() {
    }
}
