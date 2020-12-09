package com.github.pioroj.spm.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import static com.github.pioroj.spm.domain.exception.ErrorCode.EMPTY_TEAM_NAME;
import static com.github.pioroj.spm.domain.exception.ValidationCondition.when;

@Getter
public class Team {

    @Id
    private String name;
    private int currentlyImplementedProjects;

    public Team(String name) {
        validateName(name);
        this.name = name;
        this.currentlyImplementedProjects = 0;
    }

    private void validateName(String name) {
        when(name == null || name.isBlank())
                .thenInvalidEntity(EMPTY_TEAM_NAME, "Error creating team with empty name");
    }
}
