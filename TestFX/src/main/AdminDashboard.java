package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboard extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        HBox wrapper = new HBox();

        BorderPane leftPane = new BorderPane();

        VBox aside = new VBox(50);
        aside.setPrefWidth(300);
        aside.setStyle("-fx-background-color: blue;" + "-fx-padding: 70px 10px");

        HBox asideContainer = new HBox(30);

        HBox titleContainer = new HBox(30);
        titleContainer.setAlignment(Pos.CENTER);

        Label title = new Label("Admin Panel");
        title.setId("title");

        //Add title on the container
        titleContainer.getChildren().add(title);

        HBox createEmpContainer = new HBox(30);
        createEmpContainer.setAlignment(Pos.CENTER);
        Button createEmpBtn = new Button("Create Employee");

        //Add create emp btn to create emp container
        createEmpContainer.getChildren().add(createEmpBtn);

        HBox createUserContainer = new HBox(30);
        createUserContainer.setAlignment(Pos.CENTER);
        Button createUserBtn = new Button("Create User");

        //Add create user btn to create user container
        createUserContainer.getChildren().add(createUserBtn);

        HBox createMedContainer = new HBox(30);
        createMedContainer.setAlignment(Pos.CENTER);
        Button createMedBtn = new Button("Create Medicine");

        //Add create med btn to create med container
        createMedContainer.getChildren().add(createMedBtn);

        HBox createOrderContainer = new HBox(30);
        createOrderContainer.setAlignment(Pos.CENTER);
        Button createOrderBtn = new Button("Make Order");

        //Add create order btn to create order container
        createOrderContainer.getChildren().add(createOrderBtn);

        aside.getChildren().addAll(titleContainer, createEmpContainer, createUserContainer, createMedContainer, createOrderContainer);

        leftPane.setLeft(aside);
        VBox main = new VBox(10);

        HBox top = new HBox(30);

        Label caption = new Label("Med Demo");

        top.getChildren().add(caption);

        main.getChildren().add(top);

        BorderPane rightPane = new BorderPane();

        rightPane.setRight(main);

        wrapper.getChildren().addAll(leftPane, rightPane);

        Scene app = new Scene(wrapper, 1200,700);
        stage.setTitle("Admin Dashboard");
        stage.setScene(app);
        stage.show();
    }
}
