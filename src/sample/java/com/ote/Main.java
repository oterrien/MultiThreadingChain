package com.ote;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Olivier on 23/10/2015.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        long time = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {

            Job job = createJobFromXML();
            job.setParameter("Job1Param");
            job.start(executorService);
        } finally {
            executorService.shutdown();
            System.out.println(System.currentTimeMillis() - time + " ms");
        }
    }

    private static Job createJobFromXML() throws Exception {

        return ChainBuilder.getInstance().create(ClassLoader.getSystemResourceAsStream("JobDescription.xml"));
    }

    private static Job createJobManually() {

        Job job1 = new Job(JobName.JOB1.getName(), new Task1());
        Job job2 = new Job(JobName.JOB2.getName(), new Task2());
        Job job3 = new Job(JobName.JOB3.getName(), new Task3());
        Job job4 = new Job(JobName.JOB4.getName(), new Task4());
        Job job5 = new Job(JobName.JOB5.getName(), new Task5());

        job1.addNext(job2);
        job1.addNext(job3);
        job2.addNext(job4);
        job3.addNext(job5);
        job4.addNext(job5);

        return job1;
    }
}
