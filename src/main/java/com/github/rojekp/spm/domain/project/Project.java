package com.github.rojekp.spm.domain.project;

import com.github.rojekp.spm.domain.values.Status;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import static com.github.rojekp.spm.domain.exception.ErrorCode.EMPTY_PROJECT_IDENTIFIER;
import static com.github.rojekp.spm.domain.exception.ErrorCode.EMPTY_PROJECT_NAME;
import static com.github.rojekp.spm.domain.exception.ValidationCondition.when;
import static com.github.rojekp.spm.domain.values.Status.TO_DO;

@Getter
public class Project {

    @Id
    private final String identifier;
    private final String name;
    private final Status status;

    public Project(String identifier, String name) {
        validateName(name);
        validateIdentifier(identifier);
        this.identifier = identifier;
        this.name = name;
        this.status = TO_DO;
    }

    private void validateName(String name) {
        when(name == null || name.isBlank())
                .thenInvalidEntity(EMPTY_PROJECT_NAME, "Error creating team with empty name");
    }

    private void validateIdentifier(String identifier) {
        when(identifier == null || identifier.isBlank())
                .thenInvalidEntity(EMPTY_PROJECT_IDENTIFIER, "Error creating team with empty identifier");
    }

}
