package org.eldorado.infrastructure.repository.impl;

import org.eldorado.infrastructure.database.fileDatabase.IFileDatabase;
import org.eldorado.infrastructure.repository.FileRepository;

import java.util.List;

public class NotaFileRepository extends FileRepository {

    public NotaFileRepository(IFileDatabase database) {
        super(database);
    }

    @Override
    public Object read() {
        return null;
    }

    @Override
    public List readAll() {
        return null;
    }
}
