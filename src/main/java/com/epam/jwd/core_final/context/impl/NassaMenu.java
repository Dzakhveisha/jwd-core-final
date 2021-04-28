package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;

import java.util.Scanner;

public class NassaMenu implements ApplicationMenu {

    protected ApplicationContext applicationContext;
    protected final Scanner scan = new Scanner(System.in);

    public NassaMenu(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void printAvailableOptions() {
        System.out.println("\nYour possibilities: ");
        System.out.println(" 1 -> get/update Crew Members\n" +
                " 2 -> get/update Spaceships\n" +
                " 3 -> create/update/generate/save mission " +
                " 0 -> exit \n");
    }

    @Override
    public int handleUserInput() {
        int command = Integer.parseInt(scan.nextLine());
        NassaMenu menu = null;
        switch (command) {
            case 1:
                menu = new CrewMemberMenu(applicationContext);
                break;
            case 2:
                menu = new SpaceshipMenu(applicationContext);
                break;
            case 3:
                menu = new MissionsMenu(applicationContext);
                break;
            case 0:
                return 0;
            default:
                System.out.println("Command is not valid");
        }
        int result;
        if (menu != null) {
            do {
                menu.printAvailableOptions();
                result = menu.handleUserInput();
            } while (result != 0);
        }
        return command;
    }
}

