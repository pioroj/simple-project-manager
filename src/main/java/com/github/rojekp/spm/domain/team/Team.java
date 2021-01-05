package com.github.rojekp.spm.domain.team;

import com.github.rojekp.spm.domain.values.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import static com.github.rojekp.spm.domain.exception.ErrorCode.*;
import static com.github.rojekp.spm.domain.exception.ValidationCondition.when;

@Getter
@NoArgsConstructor
public class Team {

    private static final int BUSY_TEAM_THRESHOLD = 3;

    @Id
    private String name;
    private int currentlyImplementedProjects;
    private List<Employee> members;

    public Team(String name) {
        validateName(name);
        this.name = name;
        this.currentlyImplementedProjects = 0;
        members = new ArrayList<>();
    }

    public void addMember(Employee employee) {
        validateMember(employee, "Error adding member to " + name + " team.");
        members.add(employee);
    }

    public boolean isBusy() {
        return currentlyImplementedProjects > BUSY_TEAM_THRESHOLD;
    }

    private void validateMember(Employee employee, String message) {
        when(employee.hasNoFirstName())
                .thenInvalidEntity(EMPTY_MEMBER_FIRST_NAME, message);
        when(employee.hasNoLastName())
                .thenInvalidEntity(EMPTY_MEMBER_LAST_NAME, message);
        when(employee.hasInvalidJobPosition())
                .thenInvalidEntity(INVALID_MEMBER_JOB_POSITION, message);
    }

    private void validateName(String name) {
        when(name == null || name.isBlank())
                .thenInvalidEntity(EMPTY_TEAM_NAME, "Error creating team with empty name");
    }
}
