package com.example.android.reportcard;

public class Grades {
    private String gradesValue;
    private String subject;


    public Grades() {
        this.gradesValue = "";
        this.subject = "";
    }

    public Grades(String subject, String gradesValue) {
        this.gradesValue = gradesValue;
        this.subject = subject;
    }

    public String getGrade()  { return this.gradesValue; }
    public String getSubject(){ return this.subject; }
}
