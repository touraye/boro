package models;

public class Employee {
    private String emp_id;
    private String emp_fName;
    private String emp_lName;
    private String emp_init;
    private String emp_gender;
    private String emp_contact;
    private String emp_hire_date;
    private double emp_salary;
    private int emp_days_work;
    private String emp_is_active;
    private String emp_type;

    public Employee(String emp_id, String emp_fName, String emp_lName, String emp_init, String emp_gender,
                    String emp_contact, String emp_hire_date, double emp_salary, int emp_days_work,
                    String emp_is_active, String emp_type) {
        this.emp_id = emp_id;
        this.emp_fName = emp_fName;
        this.emp_lName = emp_lName;
        this.emp_init = emp_init;
        this.emp_gender = emp_gender;
        this.emp_contact = emp_contact;
        this.emp_hire_date = emp_hire_date;
        this.emp_salary = emp_salary;
        this.emp_days_work = emp_days_work;
        this.emp_is_active = emp_is_active;
        this.emp_type = emp_type;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_fName() {
        return emp_fName;
    }

    public void setEmp_fName(String emp_fName) {
        this.emp_fName = emp_fName;
    }

    public String getEmp_lName() {
        return emp_lName;
    }

    public void setEmp_lName(String emp_lName) {
        this.emp_lName = emp_lName;
    }

    public String getEmp_init() {
        return emp_init;
    }

    public void setEmp_init(String emp_init) {
        this.emp_init = emp_init;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }

    public String getEmp_contact() {
        return emp_contact;
    }

    public void setEmp_contact(String emp_contact) {
        this.emp_contact = emp_contact;
    }

    public String getEmp_hire_date() {
        return emp_hire_date;
    }

    public void setEmp_hire_date(String emp_hire_date) {
        this.emp_hire_date = emp_hire_date;
    }

    public double getEmp_salary() {
        return emp_salary;
    }

    public void setEmp_salary(double emp_salary) {
        this.emp_salary = emp_salary;
    }

    public int getEmp_days_work() {
        return emp_days_work;
    }

    public void setEmp_days_work(int emp_days_work) {
        this.emp_days_work = emp_days_work;
    }

    public String getEmp_is_active() {
        return emp_is_active;
    }

    public void setEmp_is_active(String emp_is_active) {
        this.emp_is_active = emp_is_active;
    }

    public String getEmp_type() {
        return emp_type;
    }

    public void setEmp_type(String emp_type) {
        this.emp_type = emp_type;
    }
}
