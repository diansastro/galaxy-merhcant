package com.gHost;

import com.gHost.enums.Type;

public class WordCombinations {

    public class line {
        private Type type;
        private String pattern;

        line(Type type, String pattern) {
            this.type = type;
            this.pattern = pattern;
        }

        String getPattern() {
            return this.pattern;
        }

        Type getType() {
            return this.type;
        }
    }

    private line[] lines;

    WordCombinations() {
        this.lines = new line[4];
        String patternInputIs = "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$";
        this.lines[0] = new line(Type.INPUT_IS, patternInputIs);
        String patternInputCredit = "^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$";
        this.lines[1] = new line(Type.INPUT_CREDIT, patternInputCredit);
        String patternInputHowMuch = "^how much is (([A-Za-z\\s])+)\\?$";
        this.lines[2] = new line(Type.INPUT_HOW_MUCH, patternInputHowMuch);
        String patternHowMany = "^how many [c|C]redits is (([A-Za-z\\s])+)\\?$";
        this.lines[3] = new line(Type.INPUT_HOW_MANY, patternHowMany);
    }

    Type getLineType(String line) {
        line = line.trim();
        Type result = Type.INPUT_NOT_MATCH;

        boolean match = false;
        for (int i=0; i<lines.length && !match; i++) {
            if (line.matches(lines[i].getPattern())) {
                match = true;
                result = lines[i].getType();
            }
        }

        return result;

    }
}
