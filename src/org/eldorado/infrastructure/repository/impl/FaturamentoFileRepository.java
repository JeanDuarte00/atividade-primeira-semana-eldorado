package org.eldorado.infrastructure.repository.impl;

import org.eldorado.infrastructure.database.fileDatabase.IFileDatabase;
import org.eldorado.infrastructure.repository.FileRepository;

import java.util.List;

public class FaturamentoFileRepository extends FileRepository {

    public FaturamentoFileRepository(IFileDatabase database) {
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
