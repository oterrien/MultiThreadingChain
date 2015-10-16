package com.ote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public final class Job implements Nameable {

    private List<Job> previous = new ArrayList<Job>(10);
    private Task task;
    private String name;

    public Job(String name) {
        this.name = name;
    }

    public Job(String name, Task task) {
        this(name);
        this.task = task;
    }

    /**
     * Build the chain of jobs
     * @param job
     * @param otherJobs
     */
    public void addNext(Job job, Job... otherJobs) {
        job.previous.add(this);
        for (Job otherJob : otherJobs) {
            otherJob.previous.add(this);
        }
    }

    public String getName() {
        return name;
    }

    /**
     * Start the job
     * 1. Waits for all previous tasks are finished
     * 3. Pass to current task a map of previous tasks' result
     * 2. Start the current task
     * @param executorService
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void start(ExecutorService executorService) throws ExecutionException, InterruptedException {

        Map<String, Object> result = new HashMap<String, Object>(10);
        for (Job job : previous) {
            result.put(job.name, job.task.getOutput());
        }

        if (this.task != null) {
            this.task.setInput(result);
            this.task.start(executorService);
        }
    }
}

