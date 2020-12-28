package com.github.rojekp.spm.domain.project;

import com.github.rojekp.spm.domain.service.UniqueIdentifierGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectFactory {

    private final UniqueIdentifierGenerator uniqueIdentifierGenerator;

    public Project createProjectDraft(String name) {
        String identifier = uniqueIdentifierGenerator.generateUniqueIdentifier();
        return new Project(identifier, name);
    }

}
