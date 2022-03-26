package ru;

public class Menu {

    static void mainMenu() {
        WorkWithDB run = new WorkWithDB();
        UserInterfice inter = new UserInterfice();
        run.open();

        System.out.println("Main menu. You will select a menu item.\n");
        System.out.println("1. Show DataBase.");
        System.out.println("2. Add a new client.");
        System.out.println("3. Delete client form id.");
        System.out.println("4. Search of change FN.");
        System.out.println("5. Search for a client in the database.");
        System.out.println("6. About program.");
        System.out.println("7. Exit.\n");

        int input = inter.scanIntData();

        if (input == 1) {
            run.lookDB();
        }
        if (input == 2) {
            String name, fn;
            System.out.println("Enter organization name: ");
            name = inter.scanStringData();
            System.out.println("Enter date of exchange FN: ");
            fn = inter.scanStringData();
            run.addData(name, fn);

        }
        if (input == 3) {
            System.out.println("Enter id organization to delete: ");
            int id = inter.scanIntData();
            run.deldata(id);
        }
        if (input == 4) {
            run.useData();
        }
        if (input == 5) {
            System.out.println("Enter a text to search for: ");
            String look = inter.scanStringData();
            run.lookingFor(look);
        }
        if (input == 6) {
            String message = "";
            message += "================================================\n";
            message += "This shit is written by Markelov Roman.\n";
            message += "FN Change Helper Version 0.8.5b build on 05.08.2020\n";
            message += "================================================";
            System.out.println(message);
        }
        if (input == 7) {
            System.out.println("Goodbye! I hope my program was useful for you!");
            run.close();
        }

        else {
            Menu.mainMenu();
        }
    }
}
