package com.ote;

import java.util.Map;
import java.util.Random;

public class Task4 extends Task<String, String> {

    public String call() throws Exception {

        System.out.println("## " + this.getClass().getSimpleName() + " --> input value : " + this.input);
        int delay = new Random().nextInt(100) * 30;
        System.out.println("## " + this.getClass().getSimpleName()
                + " is going to sleep for " + delay + " ms");
        Thread.sleep(delay);
        return "Result : " + this.getClass().getSimpleName() + " end";
    }

    @Override
    public void setInput(Map<String, Object> result) {

        input = (String) result.get(JobName.JOB2.getName());
    }
}
