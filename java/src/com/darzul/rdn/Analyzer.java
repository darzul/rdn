package com.darzul.rdn;

/**
 * Created by DarzuL on 9/20/15.
 */
public final class Analyzer {

    /**
     * @param texts   Texts to analyze
     * @param charset chars to include in analyze
     * @return Frequency of each chars in charset separate by space
     */
    public static int[][] computeCharsFrequency(String[] texts, char[] charset) {
        int[][] results = new int[texts.length][charset.length];

        for (int line = 0; line < texts.length; line++) {
            String currentText = texts[line];
            if (currentText.length() == 0) {
                continue;
            }

            for (int charIndex = 0; charIndex < currentText.length(); charIndex++) {
                char currentChar = currentText.charAt(charIndex);

                Integer positionInCharset = getCharPositionInCharset(charset, currentChar);
                if (positionInCharset != null) {
                    results[line][positionInCharset]++;
                }
            }
        }
        return results;
    }

    public static float[][] computeFirstChar(String[] texts, char[] charset) {
        float[][] results = new float[texts.length][charset.length];

        for (int line = 0; line < texts.length; line++) {
            String currentText = texts[line];
            if (currentText.length() == 0) {
                continue;
            }

            Integer positionInCharset = getCharPositionInCharset(charset, currentText.charAt(0));
            if (positionInCharset != null) {
                results[line][positionInCharset]++;
            }
        }
        return results;
    }

    public static float[][] computeLastChar(String[] texts, char[] charset) {
        float[][] results = new float[texts.length][charset.length];

        for (int line = 0; line < texts.length; line++) {
            String currentText = texts[line];
            if (currentText.length() == 0) {
                continue;
            }

            Integer positionInCharset = getCharPositionInCharset(charset, currentText.charAt(currentText.length() - 1));
            if (positionInCharset != null) {
                results[line][positionInCharset]++;
            }
        }
        return results;
    }

    public static float[][] computeTwoLastCharWithoutOrder(String[] texts, char[] charset) {
        float[][] results = new float[texts.length][charset.length];

        for (int line = 0; line < texts.length; line++) {
            String currentText = texts[line];
            int currentTextLen = currentText.length();
            if (currentTextLen == 0) {
                continue;
            }

            if (currentTextLen < 1) {
                continue;
            }
            Integer positionInCharset = getCharPositionInCharset(charset, currentText.charAt(currentText.length() - 1));
            if (positionInCharset != null) {
                results[line][positionInCharset]++;
            }

            if (currentTextLen < 2) {
                continue;
            }
            positionInCharset = getCharPositionInCharset(charset, currentText.charAt(currentText.length() - 2));
            if (positionInCharset != null) {
                results[line][positionInCharset]++;
            }
        }
        return results;
    }

    public static float[][] computeTwoLastCharWithOrder(String[] texts, char[] charset) {
        float[][] results = new float[texts.length][charset.length * charset.length];

        for (int line = 0; line < texts.length; line++) {
            String currentText = texts[line];
            int currentTextLen = currentText.length();
            if (currentTextLen < 2) {
                continue;
            }

            Integer positionInCharsetForLastChar = getCharPositionInCharset(charset, currentText.charAt(currentText.length() - 1));
            Integer positionInCharsetForPrevLastChar = getCharPositionInCharset(charset, currentText.charAt(currentText.length() - 2));
            if (positionInCharsetForLastChar != null && positionInCharsetForPrevLastChar != null) {
                results[line][(positionInCharsetForPrevLastChar * 26) + positionInCharsetForLastChar]++;
            }
        }
        return results;
    }

    /**
     * @param charset charset
     * @param c       char to check
     * @return The char position in charset or null if the char isn't in
     */
    private static Integer getCharPositionInCharset(char[] charset, char c) {
        for (int position = 0; position < charset.length; position++) {
            char currentChar = charset[position];
            if (currentChar == c) {
                return position;
            }
        }

        return null;
    }

    public static float[][] normalize(int[][] data, int min, int max) {
        float[][] result = new float[data.length][data[0].length];
        final int diff = max - min;

        for (int line = 0; line < data.length; line++) {
            int[] row = data[line];
            int higherValue = getHigherValue(row);

            for (int position = 0; position < row.length; position++) {
                int value = row[position];
                result[line][position] = ((float) value / higherValue) * diff + min;
            }
        }

        return result;
    }

    private static int getHigherValue(int[] values) {
        int higherValue = 0;
        for (int value : values) {
            if (value > higherValue) {
                higherValue = value;
            }
        }

        return higherValue;
    }

    private Analyzer() {
    }
}
