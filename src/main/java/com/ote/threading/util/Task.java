package com.ote.threading.util;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by Olivier on 23/10/2015.
 */
public abstract class Task implements Callable {

    protected Object input;
    private Object result;
    private Future future;

    protected Task() {

    }

    /**
     * Waits if necessary for the computation to complete, and then retrieves its result.
     *
     * @return the future's result
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public final Object getOutput() throws ExecutionException, InterruptedException {

        if (result == null) {
            result = future.get();
        }
        return result;
    }

    /**
     * Submits a value-returning task for execution and set a Future representing the pending results of the task.
     *
     * @param executorService
     */
    public final void start(ExecutorService executorService) {

        future = executorService.submit(this);

    }

    /**
     * Redefine if input is required for the call method
     *
     * @param result
     */
    public void setInput(Map<String, Object> result) {
        return;
    }
}
