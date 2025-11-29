package org.assignment.infra.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @Column(name = "student_id", nullable = false, updatable = false)
    private String studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private String dob;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_graduate")
    private boolean graduate;

    @Column(name = "thesis_title")
    private String thesisTitle;

    @Column(name = "thesis_advisor")
    private String thesisAdvisor;

    @Column(name = "research_area")
    private String researchArea;

    @Column(name = "is_graduated")
    private boolean isGraduated;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String id) {
        this.studentId = id;
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

    public boolean getIsGraduated() { return isGraduated; }
    public void setIsGraduated(boolean graduated) { isGraduated = graduated; }

    public boolean isGraduate() { return graduate; }
    public void setGraduate(boolean graduate) { this.graduate = graduate; }

    public String getThesisTitle() { return thesisTitle; }
    public void setThesisTitle(String thesisTitle) { this.thesisTitle = thesisTitle; }

    public String getThesisAdvisor() { return thesisAdvisor; }
    public void setThesisAdvisor(String thesisAdvisor) { this.thesisAdvisor = thesisAdvisor; }

    public String getResearchArea() { return researchArea; }
    public void setResearchArea(String researchArea) { this.researchArea = researchArea; }
}