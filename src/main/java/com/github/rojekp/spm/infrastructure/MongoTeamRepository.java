package com.github.rojekp.spm.infrastructure;

import com.github.rojekp.spm.domain.team.Team;
import com.github.rojekp.spm.domain.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
@RequiredArgsConstructor
class MongoTeamRepository implements TeamRepository {

    private static final String TEAMS_COLLECTION = "teams";

    private final MongoTemplate mongoTemplate;

    @Override
    public boolean existsByName(String name) {
        return mongoTemplate.exists(query(where("_id").is(name)), TEAMS_COLLECTION);
    }

    @Override
    public void save(Team team) {
        mongoTemplate.save(team, TEAMS_COLLECTION);
    }

    @Override
    public Team findByName(String teamName) {
        return mongoTemplate.findById(teamName, Team.class, TEAMS_COLLECTION);
    }

    @Override
    public List<Team> findAll() {
        return mongoTemplate.findAll(Team.class, TEAMS_COLLECTION);
    }
}
