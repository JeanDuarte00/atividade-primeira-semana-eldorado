package org.eldorado;

import org.eldorado.infrastructure.cli.ApplicationMenu;
import org.eldorado.infrastructure.database.fileDatabase.IFileDatabase;
import org.eldorado.infrastructure.database.fileDatabase.impl.FaturamentoFileDatabase;
import org.eldorado.infrastructure.database.fileDatabase.impl.NotaFileDatabase;
import org.eldorado.infrastructure.repository.IFileRepository;
import org.eldorado.infrastructure.repository.impl.FaturamentoFileRepository;
import org.eldorado.infrastructure.repository.impl.NotaFileRepository;
import org.eldorado.usecase.empresa.validador.IValidador;
import org.eldorado.usecase.empresa.validador.ValidadorEmpresa;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File fileFaturamento = new File("./database.txt");
        File fileNota = new File("./database.txt");

        IFileDatabase databaseFaturamento = new FaturamentoFileDatabase(fileFaturamento);
        IFileDatabase databaseNota = new NotaFileDatabase(fileNota);
        IFileRepository repositoryFaturamento = new FaturamentoFileRepository(databaseFaturamento);
        IFileRepository repositoryNota = new NotaFileRepository(databaseNota);

        IValidador validador = new ValidadorEmpresa(repositoryFaturamento, repositoryNota);
        ApplicationMenu menu = new ApplicationMenu(scanner, validador);

        menu.showMenu();
    }
}