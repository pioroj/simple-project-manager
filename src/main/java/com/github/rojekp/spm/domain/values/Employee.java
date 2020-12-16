package com.github.rojekp.spm.domain.values;

import lombok.Value;

import static com.github.rojekp.spm.domain.values.JobPosition.INVALID;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Value
public class Employee {

    String firstName;
    String lastName;
    JobPosition jobPosition;

    public boolean hasNoFirstName() {
        return isBlank(firstName);
    }

    public boolean hasNoLastName() {
        return isBlank(lastName);
    }

    public boolean hasInvalidJobPosition() {
        return jobPosition == INVALID;
    }
}
