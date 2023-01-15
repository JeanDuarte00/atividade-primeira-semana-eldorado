package org.eldorado.usecase.empresa.validador;

import java.time.Year;
import java.util.List;

public interface IValidador<T> {

    List<T> buscarEmpresasEmConformidade(Year ano);

    List<T> buscarEmpresasEmNaoConformidade(Year ano);

}
