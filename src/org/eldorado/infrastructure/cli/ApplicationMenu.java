package org.eldorado.infrastructure.cli;

import org.eldorado.usecase.empresa.validador.IValidador;

import java.time.Year;
import java.util.*;
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

            if(Objects.isNull(validOption)) {
                LOGGER.warning("Opcao escolhida e invalida. tente novamente.");
            } else {
                sair = menuOptions.get(userOption).equals(menuOptions.get("0"));
            }

            if(!sair){
                var anoFiltro = getFiltroPorAno();
                var resultado = execute(userOption, anoFiltro);
                System.out.println(resultado);
            }

        } while(!sair);

    }

    private Year getFiltroPorAno(){
        System.out.print("Filtar por qual ano?: ");
        var ano = this.scanner.nextLine();
        return Year.parse(ano);
    }

    private Optional<List> execute(String userOption, Year ano) {

        switch (userOption){
            case "1":
                return Optional.of(this.validador.buscarEmpresasEmConformidade(ano));
            case "2":
                return Optional.of(this.validador.buscarEmpresasEmNaoConformidade(ano));
            default:
                return Optional.empty();
        }
    }

}
