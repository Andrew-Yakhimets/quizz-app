package org.quiz.app.database;

public interface Repository<ID, T> {
    T findById(ID id);
    T save(T t);
}
