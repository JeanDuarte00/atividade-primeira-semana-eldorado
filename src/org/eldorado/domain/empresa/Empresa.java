package org.eldorado.domain.empresa;

import org.eldorado.domain.faturamento.Faturamento;
import org.eldorado.domain.nota.Nota;

import java.util.List;
import java.util.Objects;

public class Empresa {

    String chave;

    List<Faturamento> faturamentos;

    List<Nota> notas;

    public Empresa(String chave){
        this.chave = chave;
    }

    public Empresa(String chave, List<Faturamento> faturamentos, List<Nota> notas) {
        this.chave = chave;
        this.faturamentos = faturamentos;
        this.notas = notas;
    }

    public Empresa withNotas(List<Nota> notas){
        this.notas = notas;
        return this;
    }

    public Empresa withFaturamentos(List<Faturamento> faturamentos){
        this.faturamentos = faturamentos;
        return this;
    }

    public double getTotalFaturamento(){
        if(Objects.isNull(this.faturamentos))
            return 0;
        return this.faturamentos.stream().mapToDouble(faturamento -> faturamento.getValorTotalParcelas()).sum();
    }

    public double getTotalNotas(){
        if(Objects.isNull(this.notas))
            return 0;
        return this.notas.stream().mapToDouble(nota -> nota.getValorNota()).sum();
    }

    public boolean isEmConformidade(){
        return this.getTotalNotas() == getTotalFaturamento();
    }

    @Override
    public String toString() {
        return "{" +
                "chave='" + chave + '\'' +
                ", faturamentos=" + faturamentos +
                ", notas=" + notas +
                '}';
    }
}
