package dev.swell.desafiodio.architecture.persistance.impl;

import dev.swell.desafiodio.architecture.persistance.Repository;
import dev.swell.desafiodio.entity.BaseEntity;

import java.util.List;

public class RepositoryImpl <C extends BaseEntity, Id> implements Repository<C, Id> {

    @Override
    public C findById(Id id) {
        return null;
    }

    @Override
    public C save(C entity) {
        return null;
    }

    @Override
    public C update(C entity) {
        return null;
    }

    @Override
    public void delete(C entity) {

    }

    @Override
    public List<C> findAll() {
        return List.of();
    }

    @Override
    public List<C> saveAll(C... entities) {
        return List.of();
    }
}
