package com.ote.impl;

import com.ote.Task;

import java.util.Map;
import java.util.Random;

public class Task5 extends Task<Task5.Input, String> {

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

        input = new Task5.Input();
        input.fromTask3 = (String) result.get(JobName.JOB3.getName());
        input.fromTask4 = (String) result.get(JobName.JOB4.getName());
    }

    public static class Input {

        private String fromTask4;
        private String fromTask3;

        @Override
        public String toString() {
            return "fromTask3 : " + fromTask3 + ";" + " fromTask4 : " + fromTask4;
        }
    }
}
