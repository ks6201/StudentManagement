package org.assignment.cli.exceptions;

public class EnrollmentNotFoundException extends RuntimeException {

    public EnrollmentNotFoundException(
            String message
    ) {
        super("EnrollmentNotFoundException: " + message);
    }
}
