package org.eldorado.domain.faturamento;

import java.time.LocalDate;
import java.util.UUID;

public class Parcela {

    UUID id;
    LocalDate dataParcela;
    double valorParcela;

    public Parcela(LocalDate dataParcela, double valorParcela) {
        this.id = UUID.randomUUID();
        this.dataParcela = dataParcela;
        this.valorParcela = valorParcela;
    }
}
