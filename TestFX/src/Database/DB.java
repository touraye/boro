package Database;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    private Statement stmt;
    private Connection con;

    public void MedDB(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/med","root","touraye7");
            this.stmt = this.con.createStatement();
            System.out.println("DB connection");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
//            System.out.println("Sorry There seemed to be a problem with your connection please check and fix to proceed.");
            System.exit(0);
        }
    }

    //login
    public boolean login(String userName, String password){
       try{
           String queryString = "select count(*) from user where user_name = '"+userName+"' and password = '"+password+"'";
           ResultSet result = stmt.executeQuery(queryString);
           int flag = -1;
           while(result.next()){
               flag = result.getInt(1);
           }
           if(flag == 0){
               return false;
           }
           return true;
       }catch(SQLException e){
           System.out.println(e.getMessage());
        return false;
       }
    }

//    MAIN METHOD
    public static void main(String args[]){
        DB d = new DB();
//        boolean flag = d.login(null,null);
        System.out.println(d+ "Status");

        String list = String.valueOf(d.userList());
        System.out.println("users " +list);
        ArrayList<String> empId = new ArrayList<String>();
        ArrayList<String> empContact = new ArrayList<String>();
        String curId = null;
        String curContact = null;

        try {
            ResultSet select = d.selectQuery("SELECT * FROM employee");
            while(select.next()){
                curId = String.valueOf(empId.add(select.getString("emp_id")));
                curContact = String.valueOf(empContact.add(select.getString("contact")));
//                            empId.add(curId);
//                            empContact.add(curContact);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        } catch(NullPointerException ex){
            System.out.println(ex.getMessage());
        }

        for(String p : empId){
            System.out.println(p);
        }


    }

//    Select query
    public ResultSet userList(){
        try{
            String queryString = "SELECT * FROM user";
            ResultSet rSet = this.stmt.executeQuery(queryString);
            return  rSet;
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void  addUser(String Full_name,String user_name, String password){
        try{
            String addUserQuery = "INSERT INTO user(full_name,user_name,password) values('"+Full_name+"','"+user_name+"','"+password+"')";
            this.stmt.execute(addUserQuery);
        }catch(SQLException x){
            System.out.println(x.getMessage());
        }

    }

//    view user
    public String viewUser(){
        try {
            String queryUser = "SELECT * FROM user";
            ResultSet result = this.stmt.executeQuery(queryUser);
            while (result.next()){
                String cur_f_name = result.getString("full_name");
                String cur_user_name = result.getString("user_name");
                return "full_name " +cur_f_name+ " user name " +cur_user_name;
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public String findUser(int id){
        // sdfsdf
        try{
            String findQuery = "SELECT * FROM user where id = "+id;

            ResultSet returnDataSet = this.stmt.executeQuery(findQuery);


            while(returnDataSet.next()){
                int curId = returnDataSet.getInt("id");
                String name = returnDataSet.getString("full_name");
                String user_name = returnDataSet.getString("user_name");
                String password = returnDataSet.getString("password");
                return "id: "+curId+" Name: "+name+ "user_name" +user_name+ "password" +password;
            }
            return null;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public void updateUser(int id,String name ,String address){

    }

    public boolean deleteUser(int id){
        if(this.findUser(id)== null ){
            return false;
        }
        String delQuery = "delete from users where id = "+id;
        try{
            this.stmt.execute(delQuery);
        }
        catch(Exception ex){

        }
        return true;
    }

    //Select Query
    public ResultSet selectQuery(String queryArg){
        ResultSet result = null;
        try {

            result = this.stmt.executeQuery(queryArg);
            System.out.println("result " +result);

        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
        return result;
    }
}