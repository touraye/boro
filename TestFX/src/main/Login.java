package main;

import Database.DB;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {


    @Override
    public void start(Stage stage) throws Exception {

//        BorderPane wrapper = new BorderPane();
        HBox wrapper = new HBox();

        VBox aside = new VBox(50);

        aside.setPrefWidth(300);
        aside.setStyle("-fx-background-color: blue;" + "-fx-padding: 70px 10px");
        HBox asideContainer = new HBox(30);

        HBox logoContainer = new HBox(10);
        logoContainer.setAlignment(Pos.CENTER);

        Label logo = new Label("Med Demo App");
        logo.setId("logo");
        logo.setStyle("-fx-text-fill: white;" + "-fx-font-size: 20pt;" + "-fx-font-weight: bold;");

        HBox adminContainer = new HBox(10);
        adminContainer.setAlignment(Pos.CENTER);
        Button adminBtn = new Button("Login As Admin");

//        adminBtn.setStyle("-fx-padding: 10px;" + "-fx-font-weight: bold;");

        HBox pharmacistContainer = new HBox(10);
        pharmacistContainer.setAlignment(Pos.CENTER);
        Button pharmacistBtn = new Button("Login As Pharmacist");

        HBox salesPersonContainer = new HBox(10);
        salesPersonContainer.setAlignment(Pos.CENTER);
        Button salesPersonBtn = new Button("Login As Sales Person");

        //Main section
        VBox main = new VBox(60);
        main.setStyle("-fx-padding: 50px 70px");
        HBox mainContainer = new HBox(20);

        HBox nameContainer = new HBox(10);

        nameContainer.setAlignment(Pos.CENTER);
        Label name = new Label("Med Org");
        name.setStyle("-fx-text-fill: #000;" + "-fx-font-size: 20pt;" + "-fx-font-weight: bold;");

        HBox usernameLayout = new HBox(40);
        Label username = new Label("User Name:");
        TextField usernameFld = new TextField();
        username.setStyle("-fx-text-fill: #333;" + "-fx-font-size: 15pt;");
        usernameFld.setPrefWidth(200);
        usernameFld.setPrefHeight(30);
        usernameFld.promptTextProperty().set("Enter User Name");

        HBox passwordLayout = new HBox(10);
        Label password = new Label("Password:         ");
        PasswordField passwordFld = new PasswordField();
        passwordFld.promptTextProperty().setValue("Enter Password");
        password.setStyle("-fx-text-fill: #333;" + "-fx-font-size: 15pt;");
        passwordFld.setPrefWidth(200);
        passwordFld.setPrefHeight(30);

        HBox btnLayout = new HBox(10);
        btnLayout.setStyle("-fx-padding: 5 145");
        Button loginBtn = new Button();
        loginBtn.setText("LOGIN");
        loginBtn.setPrefWidth(150);
        loginBtn.setPrefHeight(25);
        loginBtn.setStyle("-fx-text-fill: white;" + "-fx-font-size: 13pt;" + "-fx-background-color: blue;");

        //Database connection
        DB dbCon = new DB();
        dbCon.MedDB();

        loginBtn.setOnAction(e->{
            String uName = usernameFld.getText();
            String uPassword = passwordFld.getText();

            /*
             Validations:
             - check for all fields
             - check for match for both nName and uPassword
             */

            if(uName.length() == 0 || uPassword.length() == 0){
                System.out.println("All fields are required");
            } else {
                boolean flag = dbCon.login(uName, uPassword);
                if(flag){
                    usernameFld.clear();
                    passwordFld.clear();

                    //Login and switch to Admin dashboard
                    AdminDashboard admin = new AdminDashboard();
                    try {
                        admin.start(stage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("No such user exist. check your credentials");
                    Error error = new Error();


                }
            }

        });


        //Add logo to the logo container
        logoContainer.getChildren().add(logo);

        //Add adminBtn to the admin container
        adminContainer.getChildren().add(adminBtn);

        //Add pharmacist btn to the pharmacist container
        pharmacistContainer.getChildren().add(pharmacistBtn);

        //Add sales person btn to the sales person container
        salesPersonContainer.getChildren().add(salesPersonBtn);

        //Add nodes to aside container
//        asideContainer.getChildren().addAll(logoContainer, adminContainer, pharmacistContainer, salesPersonContainer);
        //Add aside container to aside
        aside.getChildren().addAll(logoContainer, adminContainer, pharmacistContainer, salesPersonContainer);

        //Add loginBtn to btn layout
        btnLayout.getChildren().add(loginBtn);

        //Add name to the name container
        nameContainer.getChildren().add(name);

        //Add username to username layout
        usernameLayout.getChildren().addAll(username, usernameFld);

        //Add password to password layout
        passwordLayout.getChildren().addAll(password, passwordFld);

        //Add nodes to main container
//        mainContainer.getChildren().addAll(nameContainer, usernameLayout, passwordLayout, btnLayout);
        main.getChildren().addAll(nameContainer, usernameLayout, passwordLayout, btnLayout);

        //Add aside and main node to wrapper
        wrapper.getChildren().addAll(aside, main);

        Scene scene = new Scene(wrapper, 1200, 720);
//        scene.getStylesheets().add("styles.css");
        stage.setTitle("Med Demo");
        stage.setScene(scene);
        stage.show();

    }

//    public void start(Stage inputStage){
//        VBox appLayout = new VBox(30);
//        appLayout.setAlignment(Pos.CENTER);
//
//        HBox uNameLayout = new HBox(20);
//        uNameLayout.setAlignment(Pos.CENTER);
//        Label uNameLbl = new Label("User Name");
//        TextField uNameFld  = new TextField();
//        uNameLayout.getChildren().addAll(uNameLbl,uNameFld);
//
//        HBox passwordLayout = new HBox(20);
//        passwordLayout.setAlignment(Pos.CENTER);
//        Label passwordLbl = new Label("Password");
//        PasswordField passwordFld  = new PasswordField();
//        passwordLayout.getChildren().addAll(passwordLbl,passwordFld);
//
//        Label errMsg = new Label();
//        Button login = new Button("LOGIN...");
//        login.setOnAction(er ->{
//            boolean flag = this.login(uNameFld.getText(), passwordFld.getText());
//            if(flag){
//                Dashboard db = new Dashboard();
//                inputStage.setScene(db.dashboard());
//                System.out.println("Logged in");
//
//            }
//            else{
//                System.out.println(flag);
//               errMsg.setText("SORRY NO SUCH USER IN THE SYSTEM... \nPLEASE CHECK YOUR CREDENTIALS");
//            }
//        });
//
////      state of the APP
//        appLayout.getChildren().addAll(uNameLayout,passwordLayout,login,errMsg);
//
//        Scene appScene = new Scene(appLayout,500,500);
//        inputStage.setScene(appScene);
//        inputStage.setTitle("TODO APP...");
//        inputStage.show();
//
//    }

    public boolean login(String userName, String password){
        DB ud = new DB();
        System.out.println("user name " +userName+ " password " +password);
        return ud.login(userName,password);
    }

    public static void main(String args[]){
        launch(args);
    }
}
