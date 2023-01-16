package org.eldorado.usecase.empresa.validador;

import org.eldorado.domain.empresa.Empresa;
import org.eldorado.domain.faturamento.Faturamento;
import org.eldorado.domain.nota.Nota;
import org.eldorado.infrastructure.repository.IFileRepository;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidadorEmpresa implements IValidador<Empresa>{

    List<Empresa> empresas;
    IFileRepository<Faturamento> faturamentoRepository;
    IFileRepository<Nota> notaRepository;

    public ValidadorEmpresa(IFileRepository<Faturamento> faturamentoRepository, IFileRepository<Nota> notaRepository) {
        this.faturamentoRepository = faturamentoRepository;
        this.notaRepository = notaRepository;
    }

    @Override
    public List<Empresa> buscarEmpresasEmConformidade(Year ano) {

        Map<String, List<Faturamento>> empresasFaturamento = getFaturamentoEmpresas(ano);

        Map<String, List<Nota>> empresasNotas = getNotaEmpresas(ano);

        var empresas =  mergeDadosEmpresas(empresasFaturamento, empresasNotas);

        return empresasEmConformidades(empresas);
    }

    @Override
    public List<Empresa> buscarEmpresasEmNaoConformidade(Year ano) {
        Map<String, List<Faturamento>> empresasFaturamento = getFaturamentoEmpresas(ano);

        Map<String, List<Nota>> empresasNotas = getNotaEmpresas(ano);

        var empresas =  mergeDadosEmpresas(empresasFaturamento, empresasNotas);

        return empresasEmNaoConformidades(empresas);
    }

    private List<Empresa> empresasEmConformidades(Set<Empresa> empresas) {
        List<Empresa> listaEmpresa = new ArrayList<>();
        if(Objects.isNull(empresas))
            return listaEmpresa;
        empresas.forEach(empresa -> {
            if(empresa.isEmConformidade())
                listaEmpresa.add(empresa);
        });
        return listaEmpresa;
    }

    private List<Empresa> empresasEmNaoConformidades(Set<Empresa> empresas) {
        List<Empresa> listaEmpresa = new ArrayList<>();
        if(Objects.isNull(empresas))
            return listaEmpresa;
        empresas.forEach(empresa -> {
            if(!empresa.isEmConformidade())
                listaEmpresa.add(empresa);
        });
        return listaEmpresa;
    }

    private  Map<String, List<Nota>> getNotaEmpresas(Year ano) {
        Stream<Nota> notas = notaRepository.readAll().stream();
        var empresasNotas = notas
                            .filter(nota -> nota.getDataNota().getYear() == ano.getValue())
                            .sorted(Comparator.comparing(Nota::getChaveEmpresa))
                            .collect(Collectors.groupingBy(Nota::getChaveEmpresa));
        return empresasNotas;
    }

    private Map<String, List<Faturamento>> getFaturamentoEmpresas(Year ano) {
        Stream<Faturamento> faturamentos = faturamentoRepository.readAll().stream();
        var empresasFaturamento = faturamentos
                                    .filter(faturamento -> faturamento.getDataFaturamento().getYear() == ano.getValue())
                                    .sorted(Comparator.comparing(Faturamento::getChaveEmpresa))
                                    .collect(Collectors.groupingBy(Faturamento::getChaveEmpresa));
        return empresasFaturamento;
    }

    private Set<Empresa> mergeDadosEmpresas(Map<String, List<Faturamento>> faturamentosEmpresa, Map<String, List<Nota>> notasEmpresa){
        Set<Empresa> empresaMap = new HashSet();

        faturamentosEmpresa.forEach((chaveEmpresa, faturamentos) -> {
            var empresa = new Empresa(chaveEmpresa).withFaturamentos(faturamentos);

            var notas = notasEmpresa.get(chaveEmpresa);
            if(Objects.nonNull(notas))
                empresa.withNotas(notas);

            empresaMap.add(empresa);
        });
        return empresaMap;
    }

}
