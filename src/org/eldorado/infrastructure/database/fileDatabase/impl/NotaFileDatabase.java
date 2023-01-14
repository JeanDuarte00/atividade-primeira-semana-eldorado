package org.eldorado.infrastructure.database.fileDatabase.impl;

import org.eldorado.domain.faturamento.Parcela;
import org.eldorado.domain.nota.Nota;
import org.eldorado.infrastructure.database.fileDatabase.FileDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NotaFileDatabase extends FileDatabase<Nota> {

    Logger LOGGER;
    public NotaFileDatabase(File file) {
        super(file);
        this.LOGGER = Logger.getLogger(this.getClass().getSimpleName());
    }

    @Override
    public Nota read() {
        return null;
    }

    @Override
    public List<Nota> readAll() {
        BufferedReader reader = new BufferedReader(this.fileReader);
        Stream<String> dataAsString = reader.lines();

        var list = new ArrayList<Nota>();
        dataAsString.forEach(data -> {
            var object = parseStringToObject(data);
            if(object.isPresent())
                list.add(object.get());
        });

        return list;
    }

    private Optional<Nota> parseStringToObject(String data) {
        List parcelas = new LinkedList<Parcela>();
        String dataReferenciaNota = null;
        String dataEmissao = null;
        String valorNota = "";
        var valuesAsArray = data.split(";");
        var props = Arrays.stream(valuesAsArray).collect(Collectors.toList());
        var chaveEmpresa = props.get(0);

        try{
            dataReferenciaNota = "1/"+props.get(1)+"/"+props.get(2);
            valorNota = props.get(3);
            dataEmissao = props.get(4);
            return Optional.of(new Nota(dataReferenciaNota, dataEmissao, valorNota));
        } catch (Exception exception){
            LOGGER.warning(exception.getMessage() + "\nInvalid input from file: " + chaveEmpresa);
            return Optional.empty();
        }
    }
}
