package com.github.rojekp.spm.domain

import com.github.rojekp.spm.domain.exception.ErrorCode
import com.github.rojekp.spm.domain.exception.InvalidEntityException
import com.github.rojekp.spm.domain.project.Project
import com.github.rojekp.spm.domain.values.Status
import spock.lang.Specification

class ProjectSpecification extends Specification {

    def 'should create team with proper name'() {
        when:
        Project project = new Project('1', 'Project 1')

        then:
        project.getName() == 'Project 1'
        project.getStatus() == Status.TO_DO
        project.getIdentifier() != null
    }

    def 'should not create team with empty or blank name'() {
        when:
        new Project('1', name)


        then:
        InvalidEntityException e = thrown(InvalidEntityException)
        e.getErrorCode() == ErrorCode.EMPTY_PROJECT_NAME

        where:
        name << ['', ' ', null]
    }

    def 'should not create team with empty or blank id'() {
        when:
        new Project(id, 'Project 1')

        then:
        InvalidEntityException e = thrown(InvalidEntityException)
        e.getErrorCode() == ErrorCode.EMPTY_PROJECT_IDENTIFIER

        where:
        id << ['', ' ', null]
    }

}
