package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Entry extends Application {
        public void start(Stage pStage){
                VBox appLayout = new VBox(30);
                appLayout.setAlignment(Pos.CENTER);

                HBox f_name_layout = new HBox(20);
                appLayout.setAlignment(Pos.CENTER);
                f_name_layout.setAlignment(Pos.CENTER);
                Label full_name_lbl = new Label("Full Name");
                TextField full_name_fld = new TextField();
                f_name_layout.getChildren().addAll(full_name_lbl,full_name_fld);
//                full_name_lbl.setPromptText("Full Name");

                HBox u_name_layout = new HBox(20);
                u_name_layout.setAlignment(Pos.CENTER);
                Label user_name_lbl = new Label("User Name");
                TextField user_name_fld = new TextField();
                u_name_layout.getChildren().addAll(user_name_lbl, user_name_fld);

                HBox password_layout = new HBox(20);
                password_layout.setAlignment(Pos.CENTER);
                Label passwordLbl = new Label("Password");
                PasswordField passwordFld = new PasswordField();
                password_layout.getChildren().addAll(passwordLbl, passwordFld);

                Button loginBtn = new Button();
                loginBtn.setText("ADD USER");

                Label err_msg = new Label();

                loginBtn.setOnAction(e->{
                        String fullName = full_name_fld.getText();
                        String userName = user_name_fld.getText();
                        String password = passwordFld.getText();
//                        check for all fields
                        if(fullName.length() != 0 && userName.length() != 0 && password.length() != 0){

                                full_name_fld.setText("");
                                user_name_fld.setText("");
                                passwordFld.setText("");


                                System.out.println("UName: "+fullName+ "\nuserName" +userName+ "\n Password" +password);
                        } else {
                                err_msg.setText("All fields required");
                        }
//                        clear the error msg
//                        err_msg.setText("");

                });

                appLayout.getChildren().addAll(f_name_layout, u_name_layout, password_layout, loginBtn, err_msg);
                Scene mainScene = new Scene(appLayout,500,500);
                pStage.setTitle("ADD USER");
                pStage.setScene(mainScene);
                pStage.show();
        }
        public static void main(String args[]){
                launch(args);
        }
}
