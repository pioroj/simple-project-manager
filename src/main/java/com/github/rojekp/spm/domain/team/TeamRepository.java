package com.github.rojekp.spm.domain.team;

public interface TeamRepository {

    boolean existsByName(String name);

    void save(Team team);

    Team findByName(String teamName);
}
