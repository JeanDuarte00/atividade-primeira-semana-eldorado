package org.eldorado.usecase.empresa.validador;

import org.eldorado.domain.empresa.Empresa;

import java.util.List;

public class ValidadorEmpresa implements IValidador<Empresa>{
    @Override
    public List<Empresa> buscarEmpresasEmConformidade() {
        return null;
    }

    @Override
    public List<Empresa> buscarEmpresasEmNaoConformidade() {
        return null;
    }
}
