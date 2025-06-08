package dev.swell.desafiodio.architecture.persistance;

import java.util.List;

public interface Repository<C, Id> {
    C findById(Id id);
    C save(C entity);
    C update(C entity);
    void delete(C entity);
    List<C> findAll();
    List<C> saveAll(C... entities);
}
