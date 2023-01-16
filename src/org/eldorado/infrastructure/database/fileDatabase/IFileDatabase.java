package org.eldorado.infrastructure.database.fileDatabase;

import java.util.List;

public interface IFileDatabase<T> {
    void save(T data);
    T read();
    List<T> readAll();
}
