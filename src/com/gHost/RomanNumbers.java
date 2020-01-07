package com.gHost;

import com.gHost.enums.AlertMsg;
import com.gHost.enums.Roman;

public class RomanNumbers {

    private static final ErrorMessage errorMsg = new ErrorMessage();

    private static int romanValue(char value) {
        int val = -1;

        switch (value) {
            case 'I' : val = Roman.I.getValue(); break;
            case 'V' : val = Roman.V.getValue(); break;
            case 'X' : val = Roman.X.getValue(); break;
            case 'L' : val = Roman.L.getValue(); break;
            case 'C' : val = Roman.C.getValue(); break;
            case 'D' : val = Roman.D.getValue(); break;
            case 'M' : val = Roman.M.getValue(); break;
        }

        return val;
    }

    public static String romanToInteger(String romanValue) {
        String results = "";

        if (romanValidator(romanValue) == 1) {
            results = convert(romanValue);
        } else {
            results = RomanNumbers.errorMsg.getMessage(AlertMsg.INVALID_ROMAN_STRING);
        }

        return results;
    }

    private static int romanValidator(String value) {
        int results = 0;

        String validator = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        if (value.matches(validator)) {
            results = 1;
        }

        return results;
    }

    private static String convert(String value) {
        int decimal = 0;
        int lastNumber = 0;

        for(int i = value.length()-1; i>=0; i--) {
            char c = value.charAt(i);
            decimal = checkRoman(romanValue(c), lastNumber, decimal);
            lastNumber = romanValue(c);
        }

        return decimal+"";
    }

    private static int checkRoman(int TotalDecimal, int LastRomanLetter, int LastDecimal){

        if (LastRomanLetter > TotalDecimal) {
            return LastDecimal - TotalDecimal;
        } else {
            return LastDecimal + TotalDecimal;
        }

    }

}
