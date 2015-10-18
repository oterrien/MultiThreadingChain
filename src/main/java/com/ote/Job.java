package com.ote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public final class Job implements Nameable {

    private List<Job> previousJobs = new ArrayList<Job>(10);
    private List<Job> nextJobs = new ArrayList<Job>(10);
    private String name;

	/*
     * private Callable<TOut> task; private Future<TOut> taskResult;
	 */

    private Task task;

    private volatile boolean isStarted;

    private Object parameter;

    private Job(String name){
        this.name = name;
    }

    public Job(String name, Task task) {
        this(name);
        this.task = task;
    }

    /**
     * Build the chain of jobs
     *
     * @param job
     */
    public void addNext(Job job) {

        nextJobs.add(job);
        job.previousJobs.add(this);
    }

    public String getName() {
        return name;
    }

    public synchronized boolean isStarted() {
        return isStarted;
    }

    public synchronized void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    /**
     * Start the job 1. Waits for all previous tasks are finished 3. Pass to
     * current task a map of previous tasks' result 2. Start the current task
     *
     * @param executorService
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void start(final ExecutorService executorService) throws Exception {

        if (!isStarted()) {

            System.out.println("# Start Job " + name);

            Map<String, Object> result = new HashMap<String, Object>(10);
            result.put(this.name, parameter);
            for (Job previousJob : previousJobs) {
                result.put(previousJob.name, previousJob.task.getOutput());
            }

            System.out.println("# Start Task for " + name + " --> " + this.task.getClass().getSimpleName());
            this.task.setInput(result);
            this.task.start(executorService);

            setStarted(true);

            startNext(executorService);
        }
    }

    private void startNext(final ExecutorService executorService)
            throws Exception {

        if (nextJobs.size() > 0) {
            List<Callable<Object>> nextJob = new ArrayList<Callable<Object>>(
                    nextJobs.size());

            for (Job next : nextJobs) {
                if (next.previousJobs.stream().allMatch(x -> x.isStarted())) {
                    nextJob.add(() -> {
                        next.start(executorService);
                        return 1;
                    });
                }
            }

            for (Future<Object> future : executorService.invokeAll(nextJob)) {
                future.get();
            }
        }
    }
}
