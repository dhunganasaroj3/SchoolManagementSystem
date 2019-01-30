package Admin;

import javafx.beans.property.*;


public class StudentData {
   //private final StringProperty Id;
    private final StringProperty studentId;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty roll_No;
    private final StringProperty userName;
    private final StringProperty password;
    private final StringProperty gender;
    private final StringProperty email;
    private final StringProperty DOB;
    private final StringProperty Classs;
    private final StringProperty DOB_place;
    private final StringProperty fatherName;
    private final StringProperty motherName;
    private final StringProperty parentNumber;
    private final StringProperty yrs,id;
    public StudentData(String id,String studentId, String firstname, String lastname,String roll_No,String userName,String password,String gender, String email, String dob, String Classs,String yrs, String DOB_place, String fatherName, String motherName, String parentNumber){
        this.id =new SimpleStringProperty(id);
        this.studentId =new SimpleStringProperty(studentId);
        this.firstName =new SimpleStringProperty(firstname);
        this.lastName = new SimpleStringProperty(lastname);
        this.roll_No = new SimpleStringProperty(roll_No);
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        this.gender =new SimpleStringProperty(gender);
        this.email = new SimpleStringProperty(email);
        this.DOB =new SimpleStringProperty(dob);
        this.Classs = new SimpleStringProperty(Classs);
        this.DOB_place =new SimpleStringProperty(DOB_place);
        this.fatherName =new SimpleStringProperty(fatherName);
        this.motherName =new SimpleStringProperty(motherName);
        this.parentNumber =new SimpleStringProperty(parentNumber);
        this.yrs =new SimpleStringProperty(yrs);
    }
   //ID
   public String id() {
       return id.get();
   }
    public StringProperty IdProperty() {
        return id;
    }
    public void setId(String id) {
        this.id.set(id);
    }
//  //CLASS
    public String getClasss() {
        return Classs.get();
    }
    public StringProperty ClasssProperty() {
        return Classs;
    }
    public void setClasss(String Classs) {
        this.Classs.set(Classs);
    }
//FIRSTNAME
    public String getFirstName() {
        return firstName.get();
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
//LASTNAME
    public String getLastName() {
        return lastName.get();
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
//EMAIL
    public String getEmail() {
        return email.get();
    }
    public StringProperty emailProperty() {
        return email;
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
//DOB
    public String getDOB() {
        return DOB.get();
    }
    public StringProperty DOBProperty() {
        return DOB;
    }
    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }
//DOB_place
    public String getDOB_place() {
        return DOB_place.get();
    }
    public StringProperty DOB_placeProperty() {
        return DOB_place;
    }
    public void setDOB_place(String DOB_place) {
        this.DOB_place.set(DOB_place);
    }
//fathername
    public String getFatherName() {
        return fatherName.get();
    }
    public StringProperty fatherNameProperty() {
        return fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName.set(fatherName);
    }
//mothername
    public String getMotherName() {
        return motherName.get();
    }
    public StringProperty motherNameProperty() {
        return motherName;
    }
    public void setMotherName(String motherName) {this.motherName.set(motherName); }
//parentsnumber
    public String getParentNumber() {return parentNumber.get();}
    public StringProperty parentNumberProperty() {
        return parentNumber;
    }
    public void setParentNumber(String parentNumber) {
        this.parentNumber.set(parentNumber);
    }
//studentid
    public String getStudentId() {
        return studentId.get();
    }
    public StringProperty studentIdProperty() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }
//gender
    public String getGender() {
        return gender.get();
    }
    public StringProperty genderProperty() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender.set(gender);
    }
//roll_No
    public String getRoll_No() {
        return roll_No.get();
    }
    public StringProperty roll_NOProperty() {
        return roll_No;
    }
    public void setRoll_No(String roll_no) {
        this.roll_No.set(roll_no);
    }
//username
    public String getUserName() {
        return userName.get();
    }
    public StringProperty UserNameProperty() {
        return userName;
    }
    public void setUserName(String userName) {
        this.gender.set(userName);
    }
//password
    public String getPassword() {
        return password.get();
    }
    public StringProperty passwordProperty() { return password;   }
    public void setPassword(String password) {this.gender.set(password); }
//years
    public String getYrs() {return yrs.get(); }
    public StringProperty yrsProperty() {return yrs; }
    public void setyrs(String yrs) {
        this.yrs.set(yrs);
    }
}
