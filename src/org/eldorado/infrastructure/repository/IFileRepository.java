package org.eldorado.infrastructure.repository;

import java.util.List;
public interface IFileRepository<T> {
    void save(T data);
    T read();
    List<T> readAll();
}

