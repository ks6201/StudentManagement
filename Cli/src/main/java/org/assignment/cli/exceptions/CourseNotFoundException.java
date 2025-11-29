package org.assignment.cli.exceptions;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String message) {
        super("CourseNotFoundException: " + message);
    }
}
