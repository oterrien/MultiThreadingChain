package com.ote.threading.sample;

/**
 * Created by Olivier on 23/10/2015.
 */
public enum JobName {

    JOB1("Job1"), JOB2("Job2"), JOB3("Job3"), JOB4("Job4"), JOB5("Job5");

    private String name;

    JobName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
