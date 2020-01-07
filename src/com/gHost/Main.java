package com.gHost;

import com.gHost.enums.AlertMsg;
import com.gHost.enums.Type;

import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static WordCombinations wordCombinations = new WordCombinations();
    private static ErrorMessage errorMessage = new ErrorMessage();

    private static HashMap<String, String> containInput = new HashMap<>();
    private static HashMap<String, String> literals = new HashMap<>();

    private static ArrayList<String> output = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(" ** Welcome to Galaxy Merchant ** Type your command bellow (*blank new line to finish the input) : ");
        ArrayList<String> output = readLine();

        for (String s:  output) {
            System.out.println(s);
        }
    }

    private static ArrayList<String> readLine() {
        String line;
        int count = 0;
        AlertMsg error = null;

        while(scanner.hasNextLine() && (line = scanner.nextLine()).length() > 0 ) {
            error = validateLine(line);

            if (error == AlertMsg.NO_IDEA) {
                output.add(errorMessage.getMessage(error));
            } else {
                errorMessage.printMessage(error);
            }

            count++;
        }

        if (count == 0) {
            error = AlertMsg.NO_INPUT;
            errorMessage.printMessage(error);
        }

        return output;

    }

    private static AlertMsg validateLine(String line) {
        AlertMsg error = AlertMsg.SUCCESS;

        Type lineType = wordCombinations.getLineType(line);

        switch(lineType) {
            case INPUT_IS: questionInput(line); break;
            case INPUT_HOW_MUCH: questionInputHowMuch(line); break;
            case INPUT_CREDIT: questionInputCredit(line); break;
            case INPUT_HOW_MANY: questionInputHowMany(line); break;
            default : error = AlertMsg.NO_IDEA; break;
        }

        return error;
    }

    private static void questionInput(String line) {
        String[] split = line.trim().split("\\s+");
        try {
            containInput.put(split[0], split[2]);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            errorMessage.printMessage(AlertMsg.INCORRECT_TYPE);
            System.out.println(e.getMessage());
        }
    }

    private static void questionInputHowMuch(String line) {
        try {
            String format = line.split("\\sis\\s")[1].trim();
            format = format.replace("?","").trim();

            String[] keys = format.split("\\s+");

            StringBuilder romanResult = new StringBuilder();
            String completeResult = null;
            boolean error = false;

            for(String key : keys) {
                String romanValue = containInput.get(key);
                if(romanValue==null) {
                    completeResult = errorMessage.getMessage(AlertMsg.NO_IDEA);
                    error = true;
                    break;
                }
                romanResult.append(romanValue);
            }

            if(!error) {
                romanResult = Optional.ofNullable(RomanNumbers.romanToInteger(romanResult.toString())).map(StringBuilder::new).orElse(null);
                completeResult = format + " is "+romanResult;
            }

            output.add(completeResult);

        } catch(Exception e) {
            errorMessage.printMessage(AlertMsg.INCORRECT_TYPE);
            System.out.println(e.getMessage());
        }
    }

    private static void questionInputCredit(String line) {
        try {
            String format = line.replaceAll("(is\\s+)|([c|C]redits\\s*)","").trim();
            String[] keys = format.split("\\s");

            String toBeComputed = keys[keys.length - 2];
            float value = Float.parseFloat(keys[keys.length - 1]);

            StringBuilder roman= new StringBuilder();

            for(int i=0;i<keys.length-2;i++) {
                roman.append(containInput.get(keys[i]));
            }

            int romanNumber = Integer.parseInt(RomanNumbers.romanToInteger(roman.toString()));
            float credit = value/romanNumber;
            literals.put(toBeComputed, credit+"");
        } catch(Exception e) {
            errorMessage.printMessage(AlertMsg.INCORRECT_TYPE);
            System.out.println(e.getMessage());

        }
    }

    private static void questionInputHowMany(String line) {
        try {
            String format = line.split("(\\sis\\s)")[1];
            format = format.replace("?","").trim();
            String[] keys = format.split("\\s");

            boolean found = false;
            StringBuilder roman = new StringBuilder();
            String outputResult = null;
            Stack<Float> value = new Stack<>();

            for(String key : keys) {
                found = false;

                String romanValue = containInput.get(key);
                if(romanValue != null) {
                    roman.append(romanValue);
                    found = true;
                }

                String computedValue = literals.get(key);
                if(!found && computedValue!=null) {
                    value.push(Float.parseFloat(computedValue));
                    found = true;
                }

                if(!found) {
                    outputResult = errorMessage.getMessage(AlertMsg.NO_IDEA);
                    break;
                }
            }

            if(found) {
                float res = 1;
                for (Float val : value) res *= val;

                int finalResult = (int) res;
                if(roman.length() > 0)
                    finalResult = (int)(Integer.parseInt(RomanNumbers.romanToInteger(roman.toString()))*res);
                outputResult = format +" is "+ finalResult +" Credits";
            }

            output.add(outputResult);

        } catch(Exception e) {
            errorMessage.printMessage(AlertMsg.INCORRECT_TYPE);
            System.out.println(e.getMessage());
        }
    }
}
