package org.eldorado.domain.faturamento;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Faturamento {

    UUID id;
    LocalDate dataFaturamento;
    List<Parcela> parcelaList;

    public Faturamento(LocalDate dataFaturamento, List<Parcela> parcelaList) {
        this.id = UUID.randomUUID();
        this.dataFaturamento = dataFaturamento;
        this.parcelaList = parcelaList;
    }
}
