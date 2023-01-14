package org.eldorado.infrastructure.database.fileDatabase.impl;

import org.eldorado.domain.faturamento.Faturamento;
import org.eldorado.domain.faturamento.Parcela;
import org.eldorado.infrastructure.database.fileDatabase.FileDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FaturamentoFileDatabase extends FileDatabase<Faturamento> {

    Logger LOGGER;
    List dataFromDisk;
    public FaturamentoFileDatabase(File file) {
        super(file);
        this.LOGGER = Logger.getLogger(this.getClass().getSimpleName());
        this.dataFromDisk = new ArrayList<Faturamento>();
    }

    @Override
    public Faturamento read() {
        return null;
    }

    @Override
    public List<Faturamento> readAll() {
        if(this.dataFromDisk.size() > 0)
            return this.dataFromDisk;

        BufferedReader reader = new BufferedReader(this.fileReader);
        Stream<String> dataAsString = reader.lines();

        var list = new ArrayList<Faturamento>();
        dataAsString.parallel().forEach(data -> {
            var object = parseStringToObject(data);
            if(object.isPresent())
                list.add(object.get());
        });
        this.dataFromDisk = list;
        return this.dataFromDisk;
    }

    private Optional<Faturamento> parseStringToObject(String data) {
        List parcelas = new LinkedList<Parcela>();
        String dataFaturamento = null;
        var valuesAsArray = data.split(";",9);
        var props = Arrays.stream(valuesAsArray).collect(Collectors.toList());
        var chaveEmpresa = props.get(0);

        try{
            dataFaturamento = "1/"+props.get(1)+"/"+props.get(2);

            parcelas.add(new Parcela(props.get(3), props.get(4)));
            parcelas.add(new Parcela(props.get(5), props.get(6)));
            parcelas.add(new Parcela(props.get(7), props.get(8)));
            return Optional.of(new Faturamento(chaveEmpresa, dataFaturamento, parcelas));
        } catch (IndexOutOfBoundsException exception){
            LOGGER.warning(exception.getMessage() + "\nInvalid input from file: " + chaveEmpresa);
            return Optional.empty();
        }
    }
}
