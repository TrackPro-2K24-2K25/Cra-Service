package org.youcode.trackprocraservice.exception.Mission;

public class MissionException extends RuntimeException {

    public MissionException(String message) {
        super(message);
    }

    public MissionException(String message, Throwable cause) {
        super(message, cause);
    }

}
