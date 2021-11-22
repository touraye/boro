package models;
import java.sql.*;
public class User {
    private int id;
    private String full_name;
    private String user_name;

    public User(int id, java.lang.String full_name, java.lang.String user_name) {
        this.id = id;
        this.full_name = full_name;
        this.user_name = user_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getFull_name() {
        return full_name;
    }

    public void setFull_name(java.lang.String full_name) {
        this.full_name = full_name;
    }

    public java.lang.String getUser_name() {
        return user_name;
    }

    public void setUser_name(java.lang.String user_name) {
        this.user_name = user_name;
    }
}
