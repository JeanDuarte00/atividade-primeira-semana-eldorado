package org.eldorado.domain.faturamento;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Logger;

public class Parcela {

    private static Logger LOGGER;

    LocalDate dataParcela;

    double valorParcela;

    public Parcela(LocalDate dataParcela, double valorParcela) {
        this.LOGGER = Logger.getLogger(this.getClass().getSimpleName());
        this.dataParcela = dataParcela;
        this.valorParcela = valorParcela;
    }

    public Parcela(String dataParcela, String valorParcela) {
        this(
                LocalDate.parse(dataParcela, DateTimeFormatter.ofPattern("d/M/yyyy")),
                parseValorParcela(valorParcela)
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
