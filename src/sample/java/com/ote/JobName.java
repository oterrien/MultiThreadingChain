package com.ote;

public enum JobName implements Nameable {

    JOB1("Job1"), JOB2("Job2"), JOB3("Job3"), JOB4("Job4"), JOB5("Job5"), END("JobEnd");

    private String name;

    JobName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
