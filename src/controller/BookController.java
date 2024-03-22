package controller;

import entity.Book;
import model.BookModel;

import javax.swing.*;
import java.util.List;

public class BookController {

    BookModel objBookModel;

    public BookController() {
        this.objBookModel = new BookModel();
    }

    public void delete() {
        String listBookString = this.getAll(this.objBookModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBookString + "Enter the ID of the book to delete: "));
        Book objBook = (Book) this.objBookModel.findById(idDelete);

        if (objBook == null){
            JOptionPane.showMessageDialog(null,"Book not found.");
        }else {
            confirm = JOptionPane.showConfirmDialog(null,"Are your sure want to delete the book: "+ objBook.getTitle());

            if (confirm == 0){
                this.objBookModel.delete(objBook);
            }
        }
    }

    public void getAll() {

        String list = this.getAll(this.objBookModel.findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public String getAll(List listObject){
        String list = "Book List\n";

        for (Object obj : listObject) {
            Book objBook = (Book) obj;
            list += objBook.getId() + " - " + objBook.getTitle() + " - " + objBook.getPublication_year() + " - " + objBook.getPrice() + " - " + objBook.getIdAuthor() + "\n";
        }
        return list;
    }

    public void create() {
        Book objBook = new Book();

        String title = JOptionPane.showInputDialog("Insert title: ");
        int publication_year = Integer.parseInt(JOptionPane.showInputDialog("Insert publication year: "));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Insert price: "));
        int idAuthor = Integer.parseInt(JOptionPane.showInputDialog("Insert the id of the Author: "));

        objBook.setTitle(title);
        objBook.setPublication_year(publication_year);
        objBook.setPrice(price);
        objBook.setIdAuthor(idAuthor);

        objBook = (Book) this.objBookModel.insert(objBook);

        JOptionPane.showMessageDialog(null, objBook.getId() + " - " + objBook.getTitle() + " - " + objBook.getPublication_year() + " - " + objBook.getPrice() + " - " + objBook.getIdAuthor());

    }

    public void update(){
        String listBooks = this.getAll(this.objBookModel.findAll());

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBooks +" Enter the ID of the Book to edit"));

        Book objBook = (Book) this.objBookModel.findById(idUpdate);

        if (objBook == null){
            JOptionPane.showMessageDialog(null, "Book not found.");
        }else {

            String title = JOptionPane.showInputDialog(null, "Insert new title: ", objBook.getTitle());
            int publication_year = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert new publication year: ", objBook.getPublication_year()));
            double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Insert new price: ", objBook.getPrice()));
            int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the new id of the Author: ", objBook.getIdAuthor()));

            objBook.setTitle(title);
            objBook.setPublication_year(publication_year);
            objBook.setPrice(price);
            objBook.setIdAuthor(idAuthor);

            this.objBookModel.update(objBook);
        }
    }
}