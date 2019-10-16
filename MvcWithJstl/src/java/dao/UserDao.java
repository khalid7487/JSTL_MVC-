/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author KHALID
 */
import java.sql.*;
import java.util.*;
import model.User;
import util.Database;
public class UserDao {
    private Connection connection;
    public UserDao(){
        connection=Database.getConnection();
    }
    public void checkUser(User user){
        try {
            PreparedStatement ps=connection.prepareStatement("select uname from users where uname = ?");
            ps.setString(1, user.getUname());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                updateUser(user);
            }else{
                addUser(user);
            }
        } catch (Exception e) {
            System.out.println("Error in check() -->" + e.getMessage());

        }
    }
    public void addUser(User user){
        try {
            PreparedStatement ps=connection.prepareStatement("insert into users(uname, password, email, registeredon) values (?, ?, ?, ? )");
            ps.setString(1, user.getUname());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setDate(4, new java.sql.Date(user.getRegisteredon().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(String userId){
        try {
            PreparedStatement ps=connection.prepareStatement("delete from users where uname=?");
            ps.setString(1, userId);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateUser(User user){
        try {
            PreparedStatement ps=connection.prepareStatement("update users set password=?, email=?, registeredon=?"
                    + "where uname=?");
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            ps.setDate(3, new java.sql.Date(user.getRegisteredon().getTime()));
            ps.setString(4, user.getUname());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<User> getAllUsers(){
        List<User> users=new ArrayList<User>();
        try {
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("select * from users");
            while(rs.next()){
                User user=new User();
                user.setUname(rs.getString("uname"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRegisteredon(rs.getDate("registeredon"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public User getUserById(String userId){
        User user=new User();
        try {
            PreparedStatement ps=connection.prepareStatement("select * from users where uname=?");
            ps.setString(1, userId);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                user.setUname(rs.getString("uname"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRegisteredon(rs.getDate("registeredon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
