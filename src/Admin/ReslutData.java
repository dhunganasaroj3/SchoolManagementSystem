package Admin;

import javafx.beans.property.*;


public class ReslutData {

    private final StringProperty symbole_no;
    private final StringProperty studentId;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty roll_No;
    private final StringProperty CLASS;
    private final StringProperty marks;
    private final StringProperty terminal;
    private final StringProperty subject;
    private final StringProperty result_year;

    public ReslutData(String symbole_no ,String studentId, String firstname, String lastname,String roll_No,String terminal, String CLASS,String subject,String marks,String result_year){
        this.symbole_no =new SimpleStringProperty(symbole_no);
        this.studentId =new SimpleStringProperty(studentId);
        this.firstName =new SimpleStringProperty(firstname);
        this.lastName = new SimpleStringProperty(lastname);
        this.roll_No = new SimpleStringProperty(roll_No);
        this.CLASS = new SimpleStringProperty(CLASS);
        this.marks =new SimpleStringProperty(marks);
        this.terminal =new SimpleStringProperty(terminal);
        this.subject =new SimpleStringProperty(subject);
        this.result_year =new SimpleStringProperty(result_year);
    }
    //SYMBOLE
    public String symbole_no() {
        return symbole_no.get();
    }
    public StringProperty symbole_noProperty() {
        return symbole_no;
    }
    public void setsymbole_no(String symbole_no) {
        this.symbole_no.set(symbole_no);
    }
    //  //CLASS
    public String getCLASS() {
        return CLASS.get();
    }
    public StringProperty CLASSProperty() {
        return CLASS;
    }
    public void setCLASS(String CLASS) {
        this.CLASS.set(CLASS);
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
    //marks
    public String getmarks() {
        return marks.get();
    }
    public StringProperty marksProperty() {
        return marks;
    }
    public void setMarks(String marks) {
        this.marks.set(marks);}
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
    //terminal
    public String getterminal() {
        return terminal.get();
    }
    public StringProperty terminalProperty() {
        return terminal;
    }
    public void setterminal(String terminal) {
        this.terminal.set(terminal);
    }
    //subject
    public String getsubject() {
        return subject.get();
    }
    public StringProperty subjectProperty() {
        return subject;
    }
    public void setsubject(String subject) {
        this.subject.set(subject);
    }
    //result_year
    public String getresult_year() {
        return result_year.get();
    }
    public StringProperty result_yearProperty() {
        return result_year;
    }
    public void setresult_year(String result_year) {
        this.result_year.set(result_year);
    }
}
