package com.github.pioroj.spm.api;

import com.github.pioroj.spm.BasicE2EConfiguration;
import com.github.pioroj.spm.application.dto.TeamDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    private static Stream<Arguments> teamNameProvider() {
        return Stream.of(
          Arguments.arguments(""),
          Arguments.arguments("   ")
        );
    }
}
