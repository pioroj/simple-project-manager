package com.github.pioroj.spm.application

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.github.pioroj.spm.application.dto.TeamDto
import com.github.pioroj.spm.domain.TeamRepository
import com.github.pioroj.spm.domain.exception.EntityAlreadyExistsException
import spock.lang.Specification

class TeamServiceSpecification extends Specification {

    @Subject
    private TeamService teamService

    @Collaborator
    private TeamRepository teamRepository = Mock()

    def 'should create team with proper name'() {
        given:
        TeamDto teamDto = new TeamDto()
        teamDto.setName('Team 1')

        when:
        teamService.createTeam(teamDto)

        then:
        1 * teamRepository.existsByName('Team 1') >> false
        1 * teamRepository.save(_)
    }

    def 'should not create team with same name'() {
        given:
        TeamDto teamDto = new TeamDto()
        teamDto.setName('Team 1')

        when:
        teamService.createTeam(teamDto)

        then:
        1 * teamRepository.existsByName('Team 1') >> true
        thrown(EntityAlreadyExistsException)
        0 * teamRepository.save(_)
    }

}
