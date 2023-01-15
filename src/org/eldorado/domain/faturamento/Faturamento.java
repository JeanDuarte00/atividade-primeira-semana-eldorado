package org.eldorado.domain.faturamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Faturamento {

    String chaveEmpresa;
    LocalDate dataFaturamento;
    List<Parcela> parcelaList;

    public Faturamento(String chaveEmpresa, LocalDate dataFaturamento, List<Parcela> parcelaList) {
        this.chaveEmpresa = chaveEmpresa;
        this.dataFaturamento = dataFaturamento;
        this.parcelaList = parcelaList;
    }

    public Faturamento(String chaveEmpresa, String dataFaturamento, List<Parcela> parcelaList){
        this(chaveEmpresa, LocalDate.parse(dataFaturamento, DateTimeFormatter.ofPattern("d/M/yyyy")), parcelaList);
    }

    public double getValorTotalParcelas(){
        return this.parcelaList.stream().mapToDouble(parcela -> parcela.getValorParcela()).sum();
    }

    public String getChaveEmpresa() {
        return chaveEmpresa;
    }

    public LocalDate getDataFaturamento() {
        return dataFaturamento;
    }

    public List<Parcela> getParcelaList() {
        return parcelaList;
    }

    @Override
    public String toString() {
        return "{" +
                " dataFaturamento=" + dataFaturamento +
                ", parcelaList=" + parcelaList +
                '}';
    }
}
