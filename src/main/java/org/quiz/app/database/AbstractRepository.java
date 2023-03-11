package org.quiz.app.database;

public interface AbstractRepository<ID, T> {
    T findById(ID id);
    T save(T t);
}
