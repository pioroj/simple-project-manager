package com.github.rojekp.spm.application.dto;

import com.github.rojekp.spm.domain.team.Team;
import com.github.rojekp.spm.domain.values.Employee;
import com.github.rojekp.spm.domain.values.JobPosition;
import io.vavr.control.Try;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        JobPosition jobPosition = mapToJobPosition(employeeDto.getJobPosition());
        return new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), jobPosition);
    }

    public static List<ExistingTeamDto> mapToExistingTeams(List<Team> teams) {
        return teams.stream()
                .map(DtoMapper::mapToExistingTeamDto)
                .collect(Collectors.toList());
    }

    private static ExistingTeamDto mapToExistingTeamDto(Team team) {
        ExistingTeamDto existingTeamDto = new ExistingTeamDto();
        existingTeamDto.setName(team.getName());
        existingTeamDto.setBusy(team.isBusy());
        existingTeamDto.setCurrentlyImplementedProject(team.getCurrentlyImplementedProjects());
        existingTeamDto.setMembers(team.getMembers().stream()
                .map(DtoMapper::mapToEmployeeDto)
                .collect(Collectors.toList()));
        return existingTeamDto;
    }

    private static EmployeeDto mapToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setJobPosition(employee.getJobPosition().name());
        return employeeDto;
    }

    private static JobPosition mapToJobPosition(String jobPosition) {
        return Try.of(() -> JobPosition.valueOf(jobPosition))
                .getOrElse(JobPosition.INVALID);
    }

}
