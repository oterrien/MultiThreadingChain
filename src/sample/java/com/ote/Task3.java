package com.ote;

import java.util.Random;

public class Task3 extends Task<String, String> {

    public String call() throws Exception {

        System.out.println("## " + this.getClass().getSimpleName() + " --> input value : " + this.input);
        int delay = new Random().nextInt(100) * 30;
        System.out.println("## " + this.getClass().getSimpleName()
                + " is going to sleep for " + delay + " ms");
        Thread.sleep(delay);
        return "Result : " + this.getClass().getSimpleName() + " end";
    }

}
