package main;

import Database.DB;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Dashboard {
    private ArrayList<Integer> idList = new ArrayList<>();

    public Scene dashboard(){
        BorderPane dashboardLayout = new BorderPane();

        Button addUser = new Button("ADD USER");

        addUser.setOnAction(e->{
            dashboardLayout.setCenter(createForm());
        });
//        navigation
        Button viewUser = new Button("VIEW USERS");
        Button addNote = new Button("ADD NOTE");
        Button viewNote = new Button("NOTE LIST");
        HBox menu = new HBox(30);
        menu.getChildren().addAll(addUser,viewUser,addNote,viewNote);

        viewUser.setOnAction(e->{
//            dashboardLayout.setCenter(this.userList());
        });
//        TODO
        Label centerItem = new Label("CENTER ITEM . TO BE UPDATED LATER...");

        Label time = new Label("TIME....");
        Label copyRyt = new Label("@Copyright...");
        HBox footerLayout = new HBox();
        footerLayout.getChildren().addAll(time,copyRyt);

        dashboardLayout.setTop(menu);
        dashboardLayout.setCenter(centerItem);
        dashboardLayout.setBottom(footerLayout);

        Scene dashboardScene = new Scene(dashboardLayout,500,500);
        return dashboardScene;
    }
    public VBox createForm(){
        VBox appLayout = new VBox(30);
        appLayout.setAlignment(Pos.CENTER);

        HBox uNameLayout = new HBox(20);
        uNameLayout.setAlignment(Pos.CENTER);
        Label uNameLbl = new Label("User Name");
        TextField uNameFld  = new TextField();
        uNameLayout.getChildren().addAll(uNameLbl,uNameFld);


        HBox NameLayout = new HBox(20);
        NameLayout.setAlignment(Pos.CENTER);
        Label nameLbl = new Label("FULL Name");
        TextField nameFld  = new TextField();
        NameLayout.getChildren().addAll(nameLbl,nameFld);

        HBox passwordLayout = new HBox(20);
        passwordLayout.setAlignment(Pos.CENTER);
        Label passwordLbl = new Label("Password");
        PasswordField passwordFld  = new PasswordField();
        passwordLayout.getChildren().addAll(passwordLbl,passwordFld);

        HBox conPasswordLayout = new HBox(20);
        conPasswordLayout.setAlignment(Pos.CENTER);
        Label ConpasswordLbl = new Label("Confirm Password");
        PasswordField ConpasswordFld  = new PasswordField();
        conPasswordLayout.getChildren().addAll(ConpasswordLbl,ConpasswordFld);
        Label errMsg = new Label();
        Button saveUser = new Button("SAVE...");

//      ave user to the database
        saveUser.setOnAction(e->{
                    String fullName =uNameFld.getText();
                    String uName = nameFld.getText();
                    String pass = passwordFld.getText();
                    String conPass = ConpasswordFld.getText();
//                    check then length
                    if(fullName.length() != 0 && uName.length()
                            != 0 && pass.length() != 0
                            &&(pass.equals(conPass)) ){
                        DB udB = new DB();
//                        udB.addUser(uNameFld.getText(),nameFld.getText(),addressFld.getText(),passwordFld.getText());
                        udB.addUser(nameFld.getText(), uNameFld.getText(), passwordFld.getText());
                        uNameFld.setText("");
                       nameFld.setText("");
                         passwordFld.setText("");
                       ConpasswordFld.setText("");
                       errMsg.setText("Success... ");
                    }
                    else{
                        errMsg.setText("Sorry All Fields are required. " +
                                "\nAnd Make sure password and confirm Password are exactly the same...");
                    }

        });

        appLayout.getChildren().addAll(NameLayout,uNameLayout,passwordLayout,conPasswordLayout,saveUser,errMsg);
        return appLayout;
    }

//    view user
//    public TableView userList(){
////        fields (String full_name, String user_name)
//        TableView uList = new TableView();
//
//        TableColumn<User, Integer> user_id_col = new TableColumn<>("user id");
//        user_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        TableColumn <User, String> full_name_col = new TableColumn<>("full name");
//        full_name_col.setCellValueFactory( new PropertyValueFactory<>("full_name"));
//
//        TableColumn <User, String> user_name_col = new TableColumn<>("user name");
//        user_name_col.setCellValueFactory( new PropertyValueFactory<>("user_name"));
//
//        uList.getColumns().addAll(user_id_col, full_name_col, user_id_col);
//        UserDb handle = new UserDb();
//        ResultSet x = handle.userList();
//        try{
//            while(x.next()){
//                User = tmUser = new User(x.getInt("id"), x.getString("full_name"), x.getString("user_name"));
//                uList.getItems().add(tmUser);
//            }
//        } catch(SQLExecption ex){
//
//        }
//        return uList;
//    }

}
