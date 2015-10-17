package com.ote;
import java.util.Random;

import com.ote.Task;

public class Task2 extends Task<Object, String> {

	public String call() throws Exception {

		int delay = new Random().nextInt(100) * 30;
		System.out.println("## " +this.getClass().getSimpleName()
				+ " is going to sleep for " + delay + " ms");
		Thread.sleep(delay);
		return "Result : " + this.getClass().getSimpleName() + " end";
	}
}
