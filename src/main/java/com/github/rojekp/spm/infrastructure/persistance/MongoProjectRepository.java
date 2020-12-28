package com.github.rojekp.spm.infrastructure.persistance;

import com.github.rojekp.spm.domain.project.Project;
import com.github.rojekp.spm.domain.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MongoProjectRepository implements ProjectRepository {

    private static final String PROJECTS_COLLECTION = "projects";

    private final MongoTemplate mongoTemplate;

    @Override
    public void save(Project project) {
        mongoTemplate.save(project, PROJECTS_COLLECTION);
    }

}
