package org.assignment.core.domains.models;

import org.assignment.core.libs.validator.Length.Length;
import org.assignment.core.libs.validator.Pattern.Pattern;
import org.assignment.core.libs.validator.Required.Required;
import org.assignment.core.libs.validator.Validator;

import java.util.Objects;

public class Person {

    @Required
    @Length(min = 1, max = 100)
    private String name;

    @Required
    @Pattern(value = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")
    private String dob;

    @Required
    @Length(min = 10, max = 10)
    private String phoneNumber;

    public Person(String name, String dob, String phoneNumber) {
        this.dob = dob;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void validate() {
        Validator.validate(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person:" +
                ",\n\tname = '" + name + '\'' +
                ",\n\tdob = '" + dob + '\'' +
                ",\n\tphoneNumber = '" + phoneNumber + '\'';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Person other = (Person) object;

        return
                Objects.equals(this.name, other.name) &&
                        Objects.equals(this.dob, other.dob) &&
                        Objects.equals(this.phoneNumber, other.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dob, phoneNumber);
    }
}
