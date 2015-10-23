package com.ote;

import java.util.Random;

/**
 * Created by Olivier on 23/10/2015.
 */
public class Task2 extends Task {

    public Object call() throws Exception {

        int delay = new Random().nextInt(100) * 30;
        System.out.println("## " + this.getClass().getSimpleName()
                + " is going to sleep for " + delay + " ms");
        Thread.sleep(delay);
        return "Result : " + this.getClass().getSimpleName() + " end";
    }
}
