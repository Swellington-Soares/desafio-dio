package dev.swell.desafiodio.architecture.persistence.impl;

import dev.swell.desafiodio.architecture.persistence.JpaUtil;
import dev.swell.desafiodio.architecture.persistence.Repository;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;


public class RepositoryImpl<T, ID> implements Repository<T, ID> {

    private final Class<T> entityClass;
    private final String idFieldName;

    @SuppressWarnings("unchecked")
    public RepositoryImpl() throws Throwable {
        Type t = getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType parameterizedType) {
            entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
            if (entityClass.getAnnotation(Entity.class) == null)
                throw new InvalidParameterException("Class " + entityClass.getName() + " must have @Entity");
            idFieldName = Arrays.stream(entityClass.getDeclaredFields()).filter(
                    field -> field.isAnnotationPresent(Id.class)
            ).findFirst().orElseThrow(InvalidParameterException::new).getName();
        } else {
            throw new InvalidParameterException("Type of entity expected");
        }
    }

    @Override
    public T findById(ID id) {
        try (var em = JpaUtil.getEntityManager()) {
            return em.find(entityClass, id);
        }
    }

    @Override
    public T save(T entity) {
        try (var em = JpaUtil.getEntityManager()) {
            var tx = em.getTransaction();
            tx.begin();
            em.persist(entity);
            tx.commit();
            return entity;
        }
    }

    @Override
    public T update(T entity) {
        try (var em = JpaUtil.getEntityManager()) {
            var tx = em.getTransaction();
            tx.begin();
            T merged = em.merge(entity);
            tx.commit();
            return merged;
        }
    }

    @Override
    public void delete(T entity) {
        try (var em = JpaUtil.getEntityManager()) {
            var tx = em.getTransaction();
            tx.begin();
            T attached = em.merge(entity);
            em.remove(attached);
            tx.commit();
        }
    }

    @Override
    public List<T> findAll() {
        try (var em = JpaUtil.getEntityManager()) {
            return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        }
    }

    @Override
    public List<T> saveAll(T... entities) {
        var em = JpaUtil.getEntityManager();
        var tx = em.getTransaction();
        try {
            tx.begin();
            for  (T entity : entities) {
                em.persist(entity);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return Arrays.asList(entities);
    }
}
