package org.eldorado.usecase.empresa.validador;

import java.util.List;

public interface IValidador<T> {

    List<T> buscarEmpresasEmConformidade();

    List<T> buscarEmpresasEmNaoConformidade();

}
