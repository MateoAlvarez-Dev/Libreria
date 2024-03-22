package controller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;
import java.util.List;

public class AuthorController {

    AuthorModel objAuthorModel;

    public AuthorController() {
        this.objAuthorModel = new AuthorModel();
    }

    public void delete() {
        String listCoderString = this.getAll(this.objAuthorModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCoderString + "Enter the ID of the author to delete"));
        Author objAuthor = (Author) this.objAuthorModel.findById(idDelete);

        if (objAuthor == null){
            JOptionPane.showMessageDialog(null,"Author not found.");
        }else {
            confirm = JOptionPane.showConfirmDialog(null,"Are your sure want to delete the author: \n"+ objAuthor.getName());

            if (confirm == 0){
                this.objAuthorModel.delete(objAuthor);
            }
        }
    }

    //Método para listar todos los coder
    public void getAll() {

        String list = this.getAll(this.objAuthorModel.findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public String getAll(List<Object> listObject){
        String list = "Author List \n";

        for (Object obj : listObject) {
            Author objAuthor = (Author) obj;
            list += objAuthor.getId() + " - " + objAuthor.getName() + " - " + objAuthor.getNationality() + "\n";
        }
        return list;
    }

    public void create() {
        Author objAuthor = new Author();

        String name = JOptionPane.showInputDialog("Insert name: ");
        String nationality = JOptionPane.showInputDialog("Insert nationality: ");

        objAuthor.setName(name);
        objAuthor.setNationality(nationality);

        objAuthor = (Author) this.objAuthorModel.insert(objAuthor);

        JOptionPane.showMessageDialog(null, objAuthor.getId() + " - " + objAuthor.getName() + " - " + objAuthor.getNationality());


    }

    public void update(){
        String listCoders = this.getAll(this.objAuthorModel.findAll());

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCoders +"\nEnter the ID of the Author to edit"));

        Author objAuthor = (Author) this.objAuthorModel.findById(idUpdate);

        if (objAuthor == null){
            JOptionPane.showMessageDialog(null, "Author not found.");
        }else {
            String name = JOptionPane.showInputDialog(null,"Enter new name",objAuthor.getName());
            String nationality = JOptionPane.showInputDialog(null,"Enter new nationality",objAuthor.getNationality());

            objAuthor.setName(name);
            objAuthor.setNationality(nationality);

            this.objAuthorModel.update(objAuthor);
        }
    }
}