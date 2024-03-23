package model;

import database.CRUD;
import database.ConfigDB;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = (Book) object;

        try {
            String sql = "INSERT INTO books(title, publication_year, price, idAuthor) VALUES(?,?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objBook.getTitle());
            objPrepare.setInt(2, objBook.getPublication_year());
            objPrepare.setDouble(3, objBook.getPrice());
            objPrepare.setInt(4, objBook.getIdAuthor());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objBook.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Book inserted successfully");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding the book... " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objBook;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = (Book) object;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE books SET title = ?, publication_year = ?, price = ?, idAuthor = ? WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objBook.getTitle());
            objPrepare.setInt(2, objBook.getPublication_year());
            objPrepare.setDouble(3, objBook.getPrice());
            objPrepare.setInt(4, objBook.getIdAuthor());
            objPrepare.setInt(5, objBook.getId());

            int rowAffected = objPrepare.executeUpdate();

            if (rowAffected > 0){
                isUpdated= true;
                JOptionPane.showMessageDialog(null,"Updated Successfully :)");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error in the update... " + e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        Book objBook = (Book) object;

        boolean isDeleted = false;

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "DELETE FROM books WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objBook.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Deleted successfully");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return isDeleted;
    }

    @Override
    public List findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List listBooks = new ArrayList<>();


        try {
            String sql = "SELECT * FROM books ORDER BY books.id ASC;";

            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet) objPrepareStatement.executeQuery();

            while (objResult.next()) {
                Book objBook = new Book();

                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublication_year(objResult.getInt("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setIdAuthor(objResult.getInt("idAuthor"));

                listBooks.add(objBook);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading books... " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listBooks;
    }

    @Override
    public Object findById(int id) {

        Connection objConnection = ConfigDB.openConnection();
        Book objBook = null;

        try {
            String sql = "SELECT * FROM books WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublication_year(objResult.getInt("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setIdAuthor(objResult.getInt("idAuthor"));
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while searching the book... " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return objBook;
    }

    public List search(String term) {

        Connection objConnection = ConfigDB.openConnection();
        List bookList = new ArrayList<>();

        try {
            String sql = "SELECT b.id, b.title, b.publication_year, b.price, b.idAuthor FROM books b WHERE b.title LIKE ? OR b.id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"%" + term + "%");
            objPrepare.setString(2,term);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Book objBook = null;
                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublication_year(objResult.getInt("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setIdAuthor(objResult.getInt("idAuthor"));
                bookList.add(objBook);
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while searching the book... " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return bookList;
    }
}