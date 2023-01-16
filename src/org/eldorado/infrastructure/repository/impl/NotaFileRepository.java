package org.eldorado.infrastructure.repository.impl;

import org.eldorado.domain.nota.Nota;
import org.eldorado.infrastructure.database.fileDatabase.IFileDatabase;
import org.eldorado.infrastructure.repository.FileRepository;

import java.util.List;

public class NotaFileRepository extends FileRepository<Nota> {

    public NotaFileRepository(IFileDatabase database) {
        super(database);
    }

    @Override
    public Nota read() {
        return this.database.read();
    }

    @Override
    public List<Nota> readAll() {
        return this.database.readAll();
    }
}
