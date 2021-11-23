package main;

import Database.DB;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Locale;

public class AdminDashboard extends Application {

    BorderPane rightPane = new BorderPane();
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
        Button createEmpBtn = new Button("View Employee");

        //AddEvent @On click
        createEmpBtn.setOnAction(e->{
//            rightPane.setRight(this.createEpmPane());
            rightPane.setRight(this.viewEmployee());
        });

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

        rightPane.setRight(main);

        wrapper.getChildren().addAll(leftPane, rightPane);

        Scene app = new Scene(wrapper, 1200,700);
        stage.setTitle("Admin Dashboard");
        stage.setScene(app);
        stage.show();
    }

    //view employee
    public VBox viewEmployee(){

        VBox wrapper = new VBox (10);

        VBox top = new VBox(10);
        HBox title = new HBox(10);
        title.setAlignment(Pos.BASELINE_CENTER);
        Label titleText = new Label("View Employee and Create Employee");
        title.getChildren().add(titleText);

        HBox createEmpContainer = new HBox(10);
        createEmpContainer.setAlignment(Pos.BASELINE_RIGHT);
        Button createEmpPane = new Button("Add Employee");
        createEmpContainer.getChildren().add(createEmpPane);
        createEmpPane.setOnAction(e->{
            rightPane.setRight(this.createEpmPane());
        });

        //add object to the top
        top.getChildren().addAll(title, createEmpContainer);



        //Table
        TableView<Employee> employeeTable = new TableView();
        employeeTable.setMinWidth(1000);

        TableColumn<Employee, String> empIdCol = new TableColumn<>("Emp ID");
        empIdCol.setCellValueFactory(new PropertyValueFactory<>("emp_id"));

        TableColumn<Employee, String> empFNameCol = new TableColumn<>("Emp fName");
        empFNameCol.setCellValueFactory(new PropertyValueFactory<>("emp_fName"));

        TableColumn<Employee, String> empLNameCol = new TableColumn<>("Emp lName");
        empLNameCol.setCellValueFactory(new PropertyValueFactory<>("emp_lName"));

        TableColumn<Employee, String> empInitCol = new TableColumn<>("Emp Initial");
        empInitCol.setCellValueFactory(new PropertyValueFactory<>("emp_init"));

        TableColumn<Employee, String> empGenderCol = new TableColumn<>("Emp Gender");
        empGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Employee, String> empContactCol = new TableColumn<>("Emp Contact");
        empContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));

        TableColumn<Employee, String> empHireDateCol = new TableColumn<>("Emp Hire Date");
        empHireDateCol.setCellValueFactory(new PropertyValueFactory<>("hire_date"));

        TableColumn<Employee, Double> empSalaryCol = new TableColumn<>("Emp Salary");
        empSalaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Employee, Integer> empDaysWorkCol = new TableColumn<>("Emp Days Work");
        empDaysWorkCol.setCellValueFactory(new PropertyValueFactory<>("days_work"));

        TableColumn<Employee, String> empIsActiveCol = new TableColumn<>("Emp Is Active");
        empIsActiveCol.setCellValueFactory(new PropertyValueFactory<>("is_active"));

        TableColumn<Employee, String> empTypeCol = new TableColumn<>("Emp Type");
        empTypeCol.setCellValueFactory(new PropertyValueFactory<>("emp_type"));



        //add table column to table view
        employeeTable.getColumns().addAll(empIdCol, empFNameCol, empLNameCol, empInitCol, empGenderCol,
                empContactCol, empHireDateCol, empSalaryCol, empDaysWorkCol, empIsActiveCol, empTypeCol);

        DB handler = new DB();
        ResultSet getEmp = handler.empList();

        try {
            while (getEmp.next()){
                Employee empList = new Employee(getEmp.getString("emp_id"), getEmp.getString("emp_fName"),getEmp.getString("emp_lName"),
                        getEmp.getString("emp_init"), getEmp.getString("gender"), getEmp.getString("contact"),
                        getEmp.getString("hire_date"), getEmp.getDouble("salary"), getEmp.getInt("days_work"),
                        getEmp.getNString("is_active"), getEmp.getString("emp_type"));
                //add to table
                employeeTable.getItems().add(empList);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        } catch(NullPointerException ex){
            System.out.println(ex.getMessage());
            System.out.println("No data....");
        }



        //Todo return Pane
        wrapper.getChildren().addAll(top, employeeTable);
        return wrapper;
    }

    public VBox createEpmPane(){

        VBox wrapper = new VBox(30);

        VBox top = new VBox(10);
        top.setAlignment(Pos.CENTER);
        HBox titleContainer = new HBox();
        titleContainer.setAlignment(Pos.CENTER);
        Label title = new Label("Create Employee");
        title.setStyle("-fx-text-fill: #000;" + "-fx-font-size: 20pt;" + "-fx-font-weight: bold;");

        titleContainer.getChildren().add(title);
        top.getChildren().add(titleContainer);

        VBox formContainer = new VBox(10);
        formContainer.setAlignment(Pos.CENTER);


        //Emp ID
        TextField empId = new TextField();
        empId.setPromptText("Enter Employee ID");
        empId.setStyle("");
        empId.setPrefWidth(300);
        empId.setPrefHeight(30);

        //Emp First Name
        TextField empFName = new TextField();
        empFName.setPromptText("Enter Employee First Name");
        empFName.setPrefWidth(300);
        empFName.setPrefHeight(30);

        //Emp Last Name
        TextField empLName = new TextField();
        empLName.setPromptText("Enter Employee Last Name");
        empLName.setPrefWidth(300);
        empLName.setPrefHeight(30);

        //Emp Initial
        TextField empInit = new TextField();
        empInit.setPromptText("Enter Employee Initial");
        empInit.setPrefWidth(300);
        empInit.setPrefHeight(30);

        //Emp Gender
        ComboBox<String> empGender = new ComboBox();
        empGender.setPromptText("Select Sex");
        empGender.setPrefWidth(300);
        empGender.setPrefHeight(30);
        empGender.getItems().add("M");
        empGender.getItems().add("F");

        //Emp Contact
        TextField empContact = new TextField();
        empContact.setPromptText("Enter Employee Contact");
        empContact.setPrefWidth(300);
        empContact.setPrefHeight(30);

        //Emp Hire Date
        DatePicker empHireDate = new DatePicker();
        empHireDate.setPromptText("Select Employee Hire Date");
        empHireDate.setPrefWidth(300);
        empHireDate.setPrefHeight(30);

        //Emp Salary
        TextField empSalary = new TextField();
        empSalary.setPromptText("Enter Employee Salary");
        empSalary.setPrefWidth(300);
        empSalary.setPrefHeight(30);

        //Emp Days Work
        ComboBox<Integer> empDaysWork = new ComboBox();
        empDaysWork.setPromptText("Select Days Work");
        empDaysWork.setPrefWidth(300);
        empDaysWork.setPrefHeight(30);
        empDaysWork.getItems().add(3);
        empDaysWork.getItems().add(5);
        empDaysWork.getItems().add(6);
        empDaysWork.getItems().add(7);

        //Emp Is Active
        ComboBox<String> empIsActive = new ComboBox();
        empIsActive.setPromptText("Select Is Active");
        empIsActive.setPrefWidth(300);
        empIsActive.setPrefHeight(30);
        empIsActive.getItems().add("isActive");

        //Emp Type
        ComboBox<String> empType = new ComboBox();
        empType.setPromptText("Select Employee Type");
        empType.setPrefWidth(300);
        empType.setPrefHeight(30);
        empType.getItems().add("admin");
        empType.getItems().add("pharmacist");
        empType.getItems().add("sales_person");
        empType.getItems().add("cleaner");

        //Save Btn
        Button saveBtn = new Button("Save Employee");
        saveBtn.setPrefWidth(300);
        saveBtn.setPrefHeight(30);

        //AddEvent @Submit
        saveBtn.setOnAction(e->{

            try {
                String emp_id = empId.getText().toLowerCase(Locale.ROOT).replaceFirst("\\s+","");
                String emp_fName = empFName.getText().toLowerCase(Locale.ROOT).replaceFirst("\\s+","");
                String emp_lName = empLName.getText().toLowerCase(Locale.ROOT).replaceFirst("\\s+","");
                String emp_init = empInit.getText().toLowerCase(Locale.ROOT).replaceFirst("\\s+"," ");
                String emp_gender = empGender.getSelectionModel().getSelectedItem();
                String epm_contact = empContact.getText().toLowerCase(Locale.ROOT).replaceFirst("\\s+"," ");
                LocalDate emp_hire_date = empHireDate.getValue();
                double emp_salary = Double.parseDouble(empSalary.getText());
                int emp_days_work = empDaysWork.getSelectionModel().getSelectedItem();
                String emp_is_active = empIsActive.getSelectionModel().getSelectedItem();
                String emp_type = empType.getSelectionModel().getSelectedItem();

                /*
                Validation
                check for all fields
                 */

                if(emp_id.length() != 0 && emp_fName.length() != 0 && emp_lName.length() != 0 &&
                    emp_gender != null && epm_contact.length() != 0 && emp_hire_date != null &&
                    emp_salary <= 0 && emp_days_work != 0)

                empId.setText("");
                empFName.setText("");
                empLName.setText("");
                empInit.setText("");
                empGender.getSelectionModel().getSelectedItem();
                empContact.setText("");
                empHireDate.getValue();
                empHireDate.setValue(emp_hire_date);
                empSalary.setText("");
                empDaysWork.getSelectionModel().getSelectedItem();
                empIsActive.getSelectionModel().getSelectedItem();
                empType.getSelectionModel().getSelectedItem();

                System.out.println("empID " + emp_id+ " \n" +
                        "emp fName " +emp_fName+ "\n" +
                        "emp lName " +emp_lName+ "\n" +
                        "emp init " +emp_init+ "\n" +
                        "gender " +emp_gender+ "\n" +
                        "concat " +epm_contact+ "\n" +
                        "hire date " +emp_hire_date+ "\n" +
                        "salary " +emp_salary+ "\n" +
                        "days work " +emp_days_work+ "\n" +
                        "is active " +emp_is_active+ "\n" +
                        "type " +emp_type);
            }
            catch(NumberFormatException ex){
                System.out.println(ex.getMessage());
                System.out.println("format");
            }
            catch(NullPointerException ex){
                System.out.println(ex.getMessage());
                System.out.println("null is ");
            }
            catch(InputMismatchException ex) {
                System.out.println(ex.getMessage());
            }
        });


        formContainer.getChildren().addAll(empId, empFName, empLName, empInit, empGender, empContact, empHireDate, empSalary, empDaysWork, empIsActive, empType, saveBtn);

        wrapper.getChildren().addAll(top, formContainer);

        return wrapper;

    }


}
