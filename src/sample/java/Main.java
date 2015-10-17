import com.ote.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // TODO : use JobDescription to build the Job's chain
        Job executor1 = new Job(JobName.JOB1.getName(), new Task1());
        Job executor2 = new Job(JobName.JOB2.getName(), new Task2());
        Job executor3 = new Job(JobName.JOB3.getName(), new Task3());
        Job executor4 = new Job(JobName.JOB4.getName(), new Task4());
        Job executor5 = new Job(JobName.JOB5.getName(), new Task5());
        Job end = new Job(JobName.END.getName());

        executor1.addNext(executor2, executor3);
        executor2.addNext(executor4);
        executor3.addNext(executor5);
        executor4.addNext(executor5);
        executor5.addNext(end);

        executor1.start(executorService);
        executor2.start(executorService);
        executor3.start(executorService);
        executor4.start(executorService);
        executor5.start(executorService);
        end.start(executorService);

        executorService.shutdown();
    }
}
