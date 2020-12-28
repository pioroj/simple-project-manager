package com.github.rojekp.spm.api;

import com.github.rojekp.spm.BasicE2EConfiguration;
import com.github.rojekp.spm.application.dto.EmployeeDto;
import com.github.rojekp.spm.application.dto.ExistingTeamDto;
import com.github.rojekp.spm.application.dto.TeamDto;
import com.github.rojekp.spm.domain.team.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class TeamControllerTest extends BasicE2EConfiguration {

    private static final String TEAM_NAME = "Team 100";
    private static final String TEAMS_COLLECTION = "teams";

    @Test
    void should_create_a_team() {
        TeamDto teamDto = new TeamDto();
        teamDto.setName(TEAM_NAME);

        ResponseEntity<?> responseEntity = post("/api/teams", teamDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(mongoTemplate.exists(query(where("_id").is(TEAM_NAME)), TEAMS_COLLECTION));
    }

    @Test
    void should_throw_exception_when_add_two_same_teams() {
        TeamDto teamDto = new TeamDto();
        teamDto.setName(TEAM_NAME);
        post("/api/teams", teamDto);

        ResponseEntity<?> responseEntity = post("/api/teams", teamDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ParameterizedTest(name = "Should not create a team with name {0}")
    @MethodSource("teamNameProvider")
    void should_not_create_an_unnamed_team(String name) {
        TeamDto teamDto = new TeamDto();
        teamDto.setName(name);

        ResponseEntity<?> responseEntity = post("/api/teams", teamDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    void should_add_member_to_a_team() {
        prepareTeamWithName(TEAM_NAME);
        EmployeeDto employeeDto = prepareEmployeeDto("John", "Doe", "DEVELOPER");

        ResponseEntity<?> responseEntity = post("/api/teams/" + TEAM_NAME + "/members", employeeDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(mongoTemplate.findById(TEAM_NAME, Team.class, TEAMS_COLLECTION).getMembers().size()).isEqualTo(1);
    }

    @Test
    void should_get_teams() {
        prepareTeamWithName(TEAM_NAME);
        addMemberToTeam(TEAM_NAME);

        ResponseEntity<List<ExistingTeamDto>> responseEntity = get("/api/teams/", new ParameterizedTypeReference<>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get(0).getName()).isEqualTo(TEAM_NAME);
        assertThat(responseEntity.getBody().get(0).isBusy()).isFalse();
        assertThat(responseEntity.getBody().get(0).getCurrentlyImplementedProject()).isEqualTo(0);
        assertThat(responseEntity.getBody().get(0).getMembers().get(0).getFirstName()).isEqualTo("John");
        assertThat(responseEntity.getBody().get(0).getMembers().get(0).getLastName()).isEqualTo("Doe");
        assertThat(responseEntity.getBody().get(0).getMembers().get(0).getJobPosition()).isEqualTo("DEVELOPER");
        assertThat(mongoTemplate.findAll(Team.class, TEAMS_COLLECTION).size()).isEqualTo(1);
    }

    private void addMemberToTeam(String team) {
        EmployeeDto employeeDto = prepareEmployeeDto("John", "Doe", "DEVELOPER");

        post("/api/teams/" + team + "/members", employeeDto);
    }

    private void prepareTeamWithName(String teamName) {
        TeamDto teamDto = new TeamDto();
        teamDto.setName(teamName);

        post("/api/teams", teamDto);
    }

    private static EmployeeDto prepareEmployeeDto(String firstName, String lastName, String jobPosition) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(firstName);
        employeeDto.setLastName(lastName);
        employeeDto.setJobPosition(jobPosition);
        return employeeDto;
    }

    private static Stream<Arguments> teamNameProvider() {
        return Stream.of(
          Arguments.arguments(""),
          Arguments.arguments("   ")
        );
    }
}
