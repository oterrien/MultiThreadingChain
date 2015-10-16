package com.ote.impl;

import com.ote.Task;

import java.util.Random;

public class Task2 extends Task<Object, String> {

    public String call() throws Exception {
        try {
            System.out.println("\tStart of " + this.getClass().getSimpleName());
            int delay = new Random().nextInt(100) * 30;
            System.out.println(this.getClass().getSimpleName() + " is going to sleep for " + delay + " ms");
            Thread.sleep(delay);
            return "Result : " + this.getClass().getSimpleName() + " end";
        } finally {
            System.out.println("\tEnd of " + this.getClass().getSimpleName());
        }
    }
}
