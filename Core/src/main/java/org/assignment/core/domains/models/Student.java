package org.assignment.core.domains.models;

import org.assignment.core.libs.validator.Required.Required;
import org.assignment.core.libs.validator.Validator;

import java.util.Objects;

public class Student extends Person {

    @Required
    private String studentId;

    public Student(
            String studentId,
            String name,
            String dob,
            String phoneNumber
    ) {
        super(
                name,
                dob,
                phoneNumber
        );
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student:" +
                ",\n\tname = '" + this.getName() + '\'' +
                ",\n\tdob = '" + this.getDob() + '\'' +
                ",\n\tphoneNumber = '" + this.getPhoneNumber() + '\'' +
                ",\n\tstudentId = '" + this.getStudentId() + '\'';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Student other = (Student) object;
        return
                Objects.equals(this.studentId, other.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, super.hashCode());
    }
}
