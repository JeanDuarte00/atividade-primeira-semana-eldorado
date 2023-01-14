package org.eldorado.infrastructure.database.fileDatabase.impl;

import org.eldorado.domain.faturamento.Faturamento;
import org.eldorado.infrastructure.database.fileDatabase.FileDatabase;

import java.io.File;
import java.util.List;

public class FaturamentoFileDatabase extends FileDatabase<Faturamento> {

    public FaturamentoFileDatabase(File file) {
        super(file);
    }

    @Override
    public Faturamento read() {
        return null;
    }

    @Override
    public List<Faturamento> readAll(File file) {
        return null;
    }
}
