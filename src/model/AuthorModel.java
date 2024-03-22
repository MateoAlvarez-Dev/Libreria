package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = (Author) object;

        try {
            String sql = "INSERT INTO authors(name, nationality) VALUES(?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objAuthor.getName());
            objPrepare.setString(2, objAuthor.getNationality());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objAuthor.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Author inserted successfully");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding the author... " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objAuthor;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = (Author) object;
        boolean isUpdated = false;

        try {
            String sql  = "UPDATE authors SET name = ?, nationality = ? WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objAuthor.getName());
            objPrepare.setString(2,objAuthor.getNationality());
            objPrepare.setInt(3, objAuthor.getId());

            int rowAffected  = objPrepare.executeUpdate();

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
        Author objAuthor = (Author) object;

        boolean isDeleted = false;

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "DELETE FROM authors WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objAuthor.getId());

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
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listAuthors = new ArrayList<>();


        try {
            String sql = "SELECT * FROM authors ORDER BY authors.id ASC;";

            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet) objPrepareStatement.executeQuery();

            while (objResult.next()) {
                Author objAuthor = new Author();

                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));

                listAuthors.add(objAuthor);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading authors... " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return listAuthors;
    }

    @Override
    public Object findById(int id) {

        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = null;

        try {
            String sql  = "SELECT * FROM authors WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objAuthor = new Author();
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));
            }

        }catch (Exception e)  {
            JOptionPane.showMessageDialog(null, "Error while searching the author... " + e.getMessage());
        }

        ConfigDB.closeConnection();

        return objAuthor;
    }
}