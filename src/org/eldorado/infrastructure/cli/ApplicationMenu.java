package org.eldorado.infrastructure.cli;

import org.eldorado.usecase.empresa.validador.IValidador;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class ApplicationMenu {

    Logger LOGGER;
    Scanner scanner;
    IValidador validador;
    Map<String, String> menuOptions;

    public ApplicationMenu(Scanner scanner, IValidador validador){
        this.scanner = scanner;
        this.LOGGER = Logger.getLogger(this.getClass().getSimpleName());
        this.menuOptions = createMenu();
        this.validador = validador;
    }

    private Map<String, String> createMenu(){
        var menu = new HashMap<String, String>();
        menu.put("0","Fechar");
        menu.put("1","Empresas em conformidades");
        menu.put("2","Empresas em nao conformidades");
        return menu;
    }

    public void showMenu(){

        String validOption;
        var userOption = "0";
        var sair = false;
        do {
            System.out.println("==========================================================");
            menuOptions.forEach((key, value) -> {
                System.out.println(key + " -> " + value);
            });
            System.out.println("==========================================================");
            System.out.print(": ");
            userOption = this.scanner.nextLine();
            validOption = menuOptions.get(userOption);

            if(Objects.isNull(validOption))
                LOGGER.warning("Opcao escolhida e invalida. tente novamente.");
            else
                sair = menuOptions.get(userOption).equals(menuOptions.get("0"));

            execute(userOption, sair);

        } while(!sair);

    }

    private void execute(String userOption, boolean sair) {
        if(!sair){
            switch (userOption){
                case "1":
                    this.validador.buscarEmpresasEmConformidade();
                    break;
                case "2":
                    this.validador.buscarEmpresasEmNaoConformidade();
                    break;
            }
        }
    }

}
