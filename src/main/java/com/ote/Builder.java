package com.ote;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class Builder {

    private static final Builder INSTANCE = new Builder();
    private final Unmarshaller JaxbUnmarshaller;

    private Builder() {
        try {
            JAXBContext JaxbContext = JAXBContext.newInstance(ChainDescription.class);
            JaxbUnmarshaller = JaxbContext.createUnmarshaller();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Builder getInstance() {
        return INSTANCE;
    }

    public Job create(File file) throws Exception {

        try (InputStream in = new FileInputStream(file)) {
            return create(in);
        }
    }

    public Job create(InputStream in) throws Exception {

        JAXBElement<ChainDescription> chain = JaxbUnmarshaller.unmarshal(new StreamSource(in), ChainDescription.class);
        return create(chain.getValue());
    }

    private Job create(ChainDescription chain) throws Exception {

        Map<String, Job> map = new HashMap<String, Job>(chain.getDeclaration().getJob().size());
        for (JobDefinition jobDef : chain.getDeclaration().getJob()) {
            Task task = (Task) Class.forName(jobDef.getTask()).newInstance();
            Job job = new Job(jobDef.getName(), task);
            map.put(job.getName(), job);
        }

        JobStructure firstJobStruct = chain.getStructure().getJob();
        Job firstJob = map.get(firstJobStruct.getRef());
        create(firstJob, firstJobStruct, map);
        return firstJob;
    }

    private void create(Job job, JobStructure jobStructure, Map<String, Job> map) {

        for (JobStructure jobStruct : jobStructure.getJob()) {
            Job currentJob = map.get(jobStruct.getRef());
            job.addNext(currentJob);
            create(currentJob, jobStruct, map);
        }
    }


}
