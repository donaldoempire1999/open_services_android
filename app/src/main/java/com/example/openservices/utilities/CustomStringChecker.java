package com.example.openservices.utilities;

public class CustomStringChecker {

    public static boolean checkStringInput(String input, int minLength) {
        if (input != null && !input.isEmpty() && input.length() >= minLength)
            return true;
        return false;
    }

    public static boolean checkStringEmailInput(String input) {
        boolean result = false;
        if (input != null && !input.isEmpty() && input.length() >= 4)
            result = true;
        return result;
    }
}
