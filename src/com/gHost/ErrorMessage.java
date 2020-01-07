package com.gHost;

import com.gHost.enums.AlertMsg;

public class ErrorMessage {

    ErrorMessage() {

    }

    public String getMessage(AlertMsg alertMsg) {

        String messages = null;

        switch (alertMsg) {
            case INVALID: messages = "Wrong input format"; break;
            case NO_INPUT: messages = "No input specified"; break;
            case INCORRECT_TYPE: messages = "Incorrect line type supplied"; break;
            case INVALID_ROMAN_CHARS: messages = "Illegal character specified in roman number"; break;
            case INVALID_ROMAN_STRING: messages = "wrong format number"; break;
            case NO_IDEA: messages = "I have no idea what you are talking about"; break;
            default: break;
        }

        return messages;
    }

    /**
     * Display error message while input type requirement didn't match
     */
    void printMessage(AlertMsg alertMsg) {
        String message = getMessage(alertMsg);

        if (message != null) {
            System.out.println(message);
        }
    }
}
