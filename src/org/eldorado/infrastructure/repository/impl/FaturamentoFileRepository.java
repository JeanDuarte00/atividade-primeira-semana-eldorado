package org.eldorado.infrastructure.repository.impl;

import org.eldorado.domain.faturamento.Faturamento;
import org.eldorado.infrastructure.database.fileDatabase.IFileDatabase;
import org.eldorado.infrastructure.repository.FileRepository;

import java.util.List;

public class FaturamentoFileRepository extends FileRepository<Faturamento> {

    public FaturamentoFileRepository(IFileDatabase database) {
        super(database);
    }

    @Override
    public Faturamento read() {
        return this.database.read();
    }

    @Override
    public List<Faturamento> readAll() {
        return this.database.readAll();
    }
}
