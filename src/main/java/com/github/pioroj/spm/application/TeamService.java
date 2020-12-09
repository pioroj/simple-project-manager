package com.github.pioroj.spm.application;

import com.github.pioroj.spm.application.dto.TeamDto;
import com.github.pioroj.spm.domain.Team;
import com.github.pioroj.spm.domain.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.github.pioroj.spm.domain.exception.ErrorCode.TEAM_ALREADY_EXISTS;
import static com.github.pioroj.spm.domain.exception.ValidationCondition.when;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public void createTeam(TeamDto teamDto) {
        Team team = new Team(teamDto.getName());
        when(teamRepository.existsByName(team.getName()))
                .thenEntityAlreadyExists(TEAM_ALREADY_EXISTS, "Error creating team with name " + team.getName() + ".");
        teamRepository.save(team);
    }
}
