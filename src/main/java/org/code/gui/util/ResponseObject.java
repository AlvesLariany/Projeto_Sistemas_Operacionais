package org.code.gui.util;

public class ResponseObject {
    private boolean state;
    private String message;

    public ResponseObject(boolean state, String message) {
        this.state = state;
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
