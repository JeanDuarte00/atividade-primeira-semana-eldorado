package org.eldorado;

import org.eldorado.infrastructure.cli.ApplicationMenu;
import org.eldorado.infrastructure.database.fileDatabase.IFileDatabase;
import org.eldorado.infrastructure.repository.IFileRepository;
import org.eldorado.usecase.empresa.validador.IValidador;
import org.eldorado.usecase.empresa.validador.ValidadorEmpresa;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File("./database.txt");
        IFileDatabase database;
        IFileRepository repository;
        IValidador validador = new ValidadorEmpresa();
        ApplicationMenu menu = new ApplicationMenu(scanner, validador);

        menu.showMenu();
    }
}