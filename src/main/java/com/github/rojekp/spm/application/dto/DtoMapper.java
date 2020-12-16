package com.github.rojekp.spm.application.dto;

import com.github.rojekp.spm.domain.values.Employee;
import com.github.rojekp.spm.domain.values.JobPosition;
import io.vavr.control.Try;

public class DtoMapper {

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        JobPosition jobPosition = mapToJobPosition(employeeDto.getJobPosition());
        return new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), jobPosition);
    }

    private static JobPosition mapToJobPosition(String jobPosition) {
        return Try.of(() -> JobPosition.valueOf(jobPosition))
                .getOrElse(JobPosition.INVALID);
    }

}
