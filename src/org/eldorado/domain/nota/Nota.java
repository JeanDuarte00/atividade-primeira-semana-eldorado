package org.eldorado.domain.nota;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Logger;

public class Nota {

    private static Logger LOGGER;

    String chaveEmpresa;

    LocalDate dataNota;

    LocalDate dataEmissao;

    double valorNota;

    public Nota(String chaveEmpresa, LocalDate dataNota, LocalDate dataEmissao, double valorNota) {
        this.LOGGER = Logger.getLogger(this.getClass().getSimpleName());
        this.dataNota = dataNota;
        this.dataEmissao = dataEmissao;
        this.valorNota = valorNota;
    }

    public Nota(String chaveEmpresa, String dataReferenciaNota, String dataEmissao, String valorNota){
        this(
                chaveEmpresa,
                LocalDate.parse(dataReferenciaNota, DateTimeFormatter.ofPattern("d/M/yyyy")),
                LocalDate.parse(dataEmissao, DateTimeFormatter.ofPattern("d/M/yyyy")),
                parseValorParcela(valorNota)
        );
    }

    private static double parseValorParcela(String valorParcela) {
        Locale locale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        double valor = 0;
        try {
            valor = numberFormat.parse(valorParcela).doubleValue();
        } catch (ParseException e) {
            LOGGER.warning("Error ao fazer parse do valor da parcela");
        } finally {
            return valor;
        }
    }
}
