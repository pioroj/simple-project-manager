package com.github.rojekp.spm.application;

import com.github.rojekp.spm.application.dto.EmployeeDto;
import com.github.rojekp.spm.application.dto.ExistingTeamDto;
import com.github.rojekp.spm.application.dto.TeamDto;
import com.github.rojekp.spm.domain.team.Team;
import com.github.rojekp.spm.domain.team.TeamRepository;
import com.github.rojekp.spm.domain.values.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.rojekp.spm.application.dto.DtoMapper.mapToEmployee;
import static com.github.rojekp.spm.application.dto.DtoMapper.mapToExistingTeams;
import static com.github.rojekp.spm.domain.exception.ErrorCode.NONEXISTENT_TEAM;
import static com.github.rojekp.spm.domain.exception.ErrorCode.TEAM_ALREADY_EXISTS;
import static com.github.rojekp.spm.domain.exception.ValidationCondition.when;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public void createTeam(TeamDto teamDto) {
        log.info("Creating team with name: " + teamDto.getName());
        Team team = new Team(teamDto.getName());
        when(teamRepository.existsByName(team.getName()))
                .thenEntityAlreadyExists(TEAM_ALREADY_EXISTS, "Error creating team with name " + team.getName() + ".");
        teamRepository.save(team);
    }

    public void addMemberToTeam(String teamName, EmployeeDto employeeDto) {
        log.info("Adding employee to team: " + teamName);
        Team team = teamRepository.findByName(teamName);
        when(team == null)
                .thenMissingEntity(NONEXISTENT_TEAM, "Error adding employee to team " + teamName + ".");
        Employee employee = mapToEmployee(employeeDto);
        team.addMember(employee);
        teamRepository.save(team);
    }

    public List<ExistingTeamDto> getTeams() {
        List<Team> teams = teamRepository.findAll();
        return mapToExistingTeams(teams);
    }
}
