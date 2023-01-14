package org.eldorado;

import org.eldorado.infrastructure.cli.ApplicationMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationMenu menu = new ApplicationMenu(scanner);

        menu.showMenu();

    }
}