package com.github.pioroj.spm.domain;

public interface TeamRepository {

    boolean existsByName(String name);

    void save(Team team);
}
