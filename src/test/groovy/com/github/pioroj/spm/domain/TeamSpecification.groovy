package com.github.pioroj.spm.domain

import com.github.pioroj.spm.domain.exception.InvalidEntityException
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

    @Unroll
    def 'should not create team with empty or blank name'() {
        when:
        new Team(name)

        then:
        thrown(InvalidEntityException)

        where:
        name << ['', ' ', null]
    }

}
