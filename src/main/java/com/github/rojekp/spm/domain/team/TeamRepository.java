package com.github.rojekp.spm.domain.team;

import java.util.List;

public interface TeamRepository {

    boolean existsByName(String name);

    void save(Team team);

    Team findByName(String teamName);

    List<Team> findAll();
}
