package com.github.rojekp.spm.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExistingTeamDto {

    private String name;
    private int currentlyImplementedProject;
    private boolean busy;
    private List<EmployeeDto> members;

}
