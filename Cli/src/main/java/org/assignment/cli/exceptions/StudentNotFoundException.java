package org.assignment.cli.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(
            String message
    ) {
        super("StudentNotFound: " + message);
    }
}
