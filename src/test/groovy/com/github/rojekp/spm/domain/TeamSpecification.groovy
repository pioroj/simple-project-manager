package com.github.rojekp.spm.domain

import com.github.rojekp.spm.domain.exception.InvalidEntityException
import com.github.rojekp.spm.domain.team.Team
import com.github.rojekp.spm.domain.values.Employee
import com.github.rojekp.spm.domain.values.JobPosition
import spock.lang.Specification
import spock.lang.Unroll

class TeamSpecification extends Specification {

    def 'should create team with proper name'() {
        when:
        Team team = new Team('Team 1')

        then:
        team.getName() == 'Team 1'
        team.getCurrentlyImplementedProjects() == 0
    }

    def 'should not create team with empty or blank name'() {
        when:
        new Team(name)

        then:
        thrown(InvalidEntityException)

        where:
        name << ['', ' ', null]
    }

    @Unroll
    def 'should add a new member with #jobPosition job position to a team'() {
        given:
        Team team = new Team('Team 1')
        Employee employee = new Employee('John', 'Doe', jobPosition as JobPosition)

        when:
        team.addMember(employee)

        then:
        team.getMembers().size() == 1

        where:
        jobPosition << ['DEVELOPER', 'SCRUM_MASTER', 'PRODUCT_OWNER']
    }

    def 'should not add a new member with invalid job position'() {
        given:
        Team team = new Team('Team 1')
        Employee employee = new Employee('John', 'Doe', JobPosition.INVALID)

        when:
        team.addMember(employee)

        then:
        thrown(InvalidEntityException)
    }

    def 'should not add a new member with no first name'() {
        given:
        Team team = new Team('Team 1')
        Employee employee = new Employee(name, 'Doe', JobPosition.DEVELOPER)

        when:
        team.addMember(employee)

        then:
        thrown(InvalidEntityException)

        where:
        name << ['', ' ', null]
    }

    def 'should not add a new member with no last name'() {
        given:
        Team team = new Team('Team 1')
        Employee employee = new Employee('John', lastName, JobPosition.DEVELOPER)

        when:
        team.addMember(employee)

        then:
        thrown(InvalidEntityException)

        where:
        lastName << ['', ' ', null]
    }

}
