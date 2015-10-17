package com.ote;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Builder {

    public Job create(File file) throws Exception {

        JAXBContext jaxbContext = JAXBContext.newInstance(Jobs.class);
        Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
        Jobs jobs = (Jobs) jaxbMarshaller.unmarshal(file);
        return create(jobs);
    }

    public Job create(Jobs jobs) throws Exception {

        Map<String, Job> map = new HashMap<String, Job>(jobs.getDefinition().getJob().size());
        for (JobDefinitionType jobDef : jobs.getDefinition().getJob()) {
            Task task = (Task) Class.forName(jobDef.getTask()).newInstance();
            Job job = new Job(jobDef.getName(), task);
            map.put(job.getName(), job);
        }

        JobStructureType firstJobStruct = jobs.getStructure().getJob();
        Job firstJob = map.get(firstJobStruct.getRef());
        create(firstJob, firstJobStruct, map);
        return firstJob;
    }

    private void create(Job job, JobStructureType jobStructure, Map<String, Job> map) {

        for (JobStructureType jobStruct : jobStructure.getJob()) {
            Job currentJob = map.get(jobStruct.getRef());
            job.addNext(currentJob);
            create(currentJob, jobStruct, map);
        }
    }


}
