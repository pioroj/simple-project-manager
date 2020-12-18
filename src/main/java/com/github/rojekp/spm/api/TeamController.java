package com.github.rojekp.spm.api;

import com.github.rojekp.spm.application.TeamService;
import com.github.rojekp.spm.application.dto.EmployeeDto;
import com.github.rojekp.spm.application.dto.ExistingTeamDto;
import com.github.rojekp.spm.application.dto.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createTeam(@RequestBody TeamDto teamDto) {
        teamService.createTeam(teamDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{teamName}/members")
    public void addMemberToTeam(@PathVariable String teamName, @RequestBody EmployeeDto employeeDto) {
        teamService.addMemberToTeam(teamName, employeeDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<ExistingTeamDto> getTeams() {
        return teamService.getTeams();
    }

}
