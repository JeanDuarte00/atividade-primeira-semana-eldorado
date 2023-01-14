package org.eldorado.infrastructure.database.fileDatabase.impl;

import org.eldorado.domain.nota.Nota;
import org.eldorado.infrastructure.database.fileDatabase.FileDatabase;

import java.io.File;
import java.util.List;

public class NotaFileDatabase extends FileDatabase<Nota> {

    public NotaFileDatabase(File file) {
        super(file);
    }

    @Override
    public Nota read() {
        return null;
    }

    @Override
    public List<Nota> readAll(File file) {
        return null;
    }
}
