import controller.AuthorController;
import database.ConfigDB;
import model.AuthorModel;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AuthorController objCoderController = new AuthorController();
        String option = "";
        do {

            option = JOptionPane.showInputDialog("""
                    MENU
                    1. List Authors
                    2. Insert Author
                    3. Update Author
                    4. Delete Author
                    6. Exit
                    
                    Choose an option: 
                    """);

            switch (option){
                case "1": //List All Coders
                    objCoderController.getAll();
                    break;

                case "2":
                    objCoderController.create();
                    break;

                case "3":
                    objCoderController.update();
                    break;

                case "4":
                    objCoderController.delete();
                    break;
            }

        }while (!option.equals("6"));
    }
}