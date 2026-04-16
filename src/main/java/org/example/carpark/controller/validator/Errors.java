package org.example.carpark.controller.validator;

import java.util.HashMap;
import java.util.Map;

public class Errors {
    private final Map<String, String> messages = new HashMap<>();
    private boolean hasErrors = false;

    public void addMessage(String attribute, String message) {
        messages.put(attribute, message);
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public boolean hasError(){
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

}