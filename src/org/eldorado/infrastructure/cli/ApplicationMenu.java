package org.eldorado.infrastructure.cli;

import java.util.*;
import java.util.logging.Logger;

public class ApplicationMenu {

    Logger LOGGER;
    Scanner scanner;

    Map<String, String> menuOptions;

    public ApplicationMenu(Scanner scanner){
        this.scanner = scanner;
        this.LOGGER = Logger.getLogger(this.getClass().getSimpleName());
        this.menuOptions = createMenu();
    }

    private Map<String, String> createMenu(){
        var menu = new HashMap<String, String>();
        menu.put("0","Fechar");
        menu.put("1","Empresas me conformidades");
        menu.put("2","Empresas em nao conformidades");
        return menu;
    }

    public void showMenu(){

        String validOption = null;
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

        } while(!sair);

    }

}
