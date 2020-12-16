package com.github.rojekp.spm.application

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.github.rojekp.spm.application.dto.EmployeeDto
import com.github.rojekp.spm.domain.exception.MissingEntityException
import com.github.rojekp.spm.domain.team.Team
import com.github.rojekp.spm.domain.team.TeamRepository
import com.github.rojekp.spm.application.dto.TeamDto
import com.github.rojekp.spm.domain.exception.EntityAlreadyExistsException
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

    def 'should add new member to the existing team'() {
        given:
        String teamName = 'Team 1'

        when:
        teamService.addMemberToTeam(teamName, prepareEmployeeDto('John', 'Doe', 'DEVELOPER'))

        then:
        1 * teamRepository.findByName(teamName) >> new Team(teamName)
        1 * teamRepository.save(_)
    }

    def 'should not add new member to non existing team'() {
        given:
        String teamName = 'Team 1'

        when:
        teamService.addMemberToTeam(teamName, prepareEmployeeDto('John', 'Doe', 'DEVELOPER'))

        then:
        1 * teamRepository.findByName(teamName) >> null
        thrown(MissingEntityException)
        0 * teamRepository.save(_)
    }

    private static EmployeeDto prepareEmployeeDto(String firstName, String lastName, String jobPosition) {
        EmployeeDto employeeDto = new EmployeeDto()
        employeeDto.setFirstName(firstName)
        employeeDto.setLastName(lastName)
        employeeDto.setJobPosition(jobPosition)
        return employeeDto
    }
}
