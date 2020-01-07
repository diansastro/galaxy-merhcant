package com.gHost;

import com.gHost.enums.Alert;

public class ErrorMessage {

    ErrorMessage() {

    }

    private String getMessage(Alert alert) {

        String messages = null;

        switch (alert) {
            case INVALID: messages = "Wrong input format"; break;
            case NO_INPUT: messages = "No input specified"; break;
            case INCORRECT_TYPE: messages = "Incorrect line type supplied"; break;
            case INVALID_ROMAN_CHARS: messages = "Illegal character specified in roman number"; break;
            case INVALID_ROMAN_STRING: messages = "wrong RomanNumbers number"; break;
            case NO_IDEA: messages = "I have no idea what you are talking about"; break;
            default: break;
        }

        return messages;
    }

    /**
     * Display error message while input type requirement didn't match
     */
    void printMessage(Alert alert) {
        String message = getMessage(alert);

        if (message != null) {
            System.out.println(message);
        }
    }
}
