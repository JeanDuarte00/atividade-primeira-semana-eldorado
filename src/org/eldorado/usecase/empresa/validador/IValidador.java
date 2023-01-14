package org.eldorado.usecase.empresa.validador;

import org.eldorado.domain.empresa.Empresa;

import java.util.List;

public interface IValidador<T> {

    List<T> buscarEmpresasEmConformidade();

    List<T> buscarEmpresasEmNaoConformidade();

}
