import controller.AuthorController;
import controller.BookController;
import database.ConfigDB;
import model.AuthorModel;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AuthorController objAuthorController = new AuthorController();
        BookController objBookController = new BookController();
        String option = "";
        do {

            option = JOptionPane.showInputDialog("""
                    MENU
                    1. Authors Menu
                    2. Books Menu
                    
                    Choose an option: 
                    """);

            switch (option){
                case "1":
                    String authorOption;
                    authorOption = JOptionPane.showInputDialog("""
                    MENU
                    1. List Authors
                    2. Insert Author
                    3. Update Author
                    4. Delete Author
                    
                    Choose an option: 
                    """);

                    switch (authorOption){
                        case "1":
                            objAuthorController.getAll();
                            break;

                        case "2":
                            objAuthorController.create();
                            break;

                        case "3":
                            objAuthorController.update();
                            break;

                        case "4":
                            objAuthorController.delete();
                            break;
                    }
                    break;

                case "2":
                    String bookOption;
                    bookOption = JOptionPane.showInputDialog("""
                    MENU
                    1. List Books
                    2. Insert Book
                    3. Update Book
                    4. Delete Book
                    
                    Choose an option: 
                    """);

                    switch (bookOption){
                        case "1":
                            objBookController.getAll();
                            break;

                        case "2":
                            objBookController.create();
                            break;

                        case "3":
                            objBookController.update();
                            break;

                        case "4":
                            objBookController.delete();
                            break;
                    }
                    break;
            }

        }while (!option.equals("6"));
    }
}