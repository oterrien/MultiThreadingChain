package com.ote;

import java.util.Map;
import java.util.Random;

public class Task5 extends Task<Task5.Input, String> {

    public String call() throws Exception {

        System.out.println("## " + this.getClass().getSimpleName()
                + " --> input value : " + this.input);
        int delay = new Random().nextInt(100) * 30;
        System.out.println("## " + this.getClass().getSimpleName()
                + " is going to sleep for " + delay + " ms");
        Thread.sleep(delay);
        return "Result : " + this.getClass().getSimpleName() + " end";
    }

    @Override
    public void setInput(Map<String, Object> result) {

        input = new Input();
        input.fromTask3 = (String) result.get(JobName.JOB3.getName());
        input.fromTask4 = (String) result.get(JobName.JOB4.getName());
    }

    public static class Input {

        private String fromTask4;
        private String fromTask3;

        @Override
        public String toString() {
            return "{" + JobName.JOB3.getName() + " : " + fromTask3 + ";" + " " + JobName.JOB4.getName() + " : " + fromTask4 + "}";
        }
    }
}
