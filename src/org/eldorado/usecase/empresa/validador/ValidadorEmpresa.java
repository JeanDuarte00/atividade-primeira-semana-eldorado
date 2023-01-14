package org.eldorado.usecase.empresa.validador;

import org.eldorado.domain.empresa.Empresa;
import org.eldorado.infrastructure.repository.IFileRepository;

import java.util.List;

public class ValidadorEmpresa implements IValidador<Empresa>{

    IFileRepository<Empresa> faturamentoRepository;
    IFileRepository<Empresa> notaRepository;

    public ValidadorEmpresa(IFileRepository<Empresa> faturamentoRepository, IFileRepository<Empresa> notaRepository) {
        this.faturamentoRepository = faturamentoRepository;
        this.notaRepository = notaRepository;
    }

    @Override
    public List<Empresa> buscarEmpresasEmConformidade() {
        return faturamentoRepository.readAll();
    }

    @Override
    public List<Empresa> buscarEmpresasEmNaoConformidade() {
        return null;
    }
}
