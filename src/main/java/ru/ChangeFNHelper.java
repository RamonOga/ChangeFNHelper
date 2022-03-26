package ru;

public class ChangeFNHelper
{
    public static void main (String[] args) {

        System.out.println("===================================================");
        System.out.println("You are wellcomed by the program FN Change Helper!\n");

        Menu menu = new Menu();

        menu.mainMenu();

       /*//=========== For check.jar ===========
       ru.WorkWithDB runCheck = new ru.WorkWithDB();
        runCheck.open();
        runCheck.useData();
        runCheck.close();
       //======================================*/
    }
}