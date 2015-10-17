package com.ote;

import java.util.Map;
import java.util.Random;

public class Task4 extends Task<String, String> {

    public String call() throws Exception {
        try {
            System.out.println("\tStart of " + this.getClass().getSimpleName());
            System.out.println("Input " + this.input);
            int delay = new Random().nextInt(100) * 30;
            System.out.println(this.getClass().getSimpleName() + " is going to sleep for " + delay + " ms");
            Thread.sleep(delay);
            return "Result : " + this.getClass().getSimpleName() + " end";
        } finally {
            System.out.println("\tEnd of " + this.getClass().getSimpleName());
        }
    }

    @Override
    public void setInput(Map<String, Object> result) {

        input = (String) result.get(JobName.JOB2.getName());
    }
}
