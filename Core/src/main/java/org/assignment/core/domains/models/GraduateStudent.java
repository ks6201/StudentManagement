package org.assignment.core.domains.models;

import org.assignment.core.libs.validator.Length.Length;
import org.assignment.core.libs.validator.Required.Required;

import java.util.Objects;

public class GraduateStudent extends Student {

    @Required
    @Length(min=5, max=100)
    private String thesisTitle;

    @Required
    @Length(min=3, max=100)
    private String thesisAdvisor;

    @Required
    @Length(min=5, max=100)
    private String researchArea;

    public GraduateStudent(
            String studentId,
            String name,
            String dob,
            String phoneNumber,
            String thesisTitle,
            String thesisAdvisor,
            String researchArea
    ) {
        super(studentId, name, dob, phoneNumber);
        this.thesisTitle = thesisTitle;
        this.thesisAdvisor = thesisAdvisor;
        this.researchArea = researchArea;
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    public String getThesisAdvisor() {
        return thesisAdvisor;
    }

    public void setThesisAdvisor(String thesisAdvisor) {
        this.thesisAdvisor = thesisAdvisor;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    @Override
    public String toString() {
        return "GraduateStudent:" +
                ",\n\tname = '" + this.getName() + '\'' +
                ",\n\tdob = '" + this.getDob() + '\'' +
                ",\n\tphoneNumber = '" + this.getPhoneNumber() + '\'' +
                ",\n\tstudentId = '" + this.getStudentId() + '\'' +
                ",\n\tthesisTitle = '" + this.getThesisTitle() + '\'' +
                ",\n\tthesisAdvisor = '" + this.getThesisAdvisor() + '\'' +
                ",\n\tresearchArea = '" + this.getResearchArea() + '\'';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        GraduateStudent other = (GraduateStudent) object;

        return Objects.equals(this.getStudentId(), other.getStudentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), thesisTitle, thesisAdvisor, researchArea);
    }
}
