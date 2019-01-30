package Admin;
import com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException;
import dbUtil.DbConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import static java.lang.Integer.parseInt;
import java.awt.print.PageFormat;
import Admin.ReslutData;
public class AdminController implements Initializable {
    private int minute;
    private int hour;
    private int second;
    private int month;
    private int year;
    private int day;
    private String selectGender;
    String studentid1;
    String studentid2;
    String returnValue;
    int sn;
    int assignVar;
    public String validateSubject;
    public String validatestudentId;
    public String validateTerminal;
    public String   subjectSizeString;
    public String   subjectCountString;

    //    private int size;
//    private int count = 1;
    private final String pattern = "yyyy-dd-MM";
    final ObservableList subjectList = FXCollections.observableArrayList();
    final ObservableList student_IdList = FXCollections.observableArrayList();
    final ObservableList terminalList = FXCollections.observableArrayList();

    Connection connection;
    PreparedStatement pr = null;
    ResultSet rs = null;
    
    @FXML
    private Label time;
    @FXML
    private Label who,who1;
    @FXML
    private Label date;
    @FXML
    private TextField Class;
    @FXML
    private TextField firstname;
    @FXML
    private TextField studentId;
    @FXML
    private TextField lastname, DOBplace, fathername, mothername, parentnumber;
    @FXML
    private TextField email;
    @FXML
    private TextField searchfield;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField num;
    @FXML
    private TextField term,roll_no1,userName, password;
    @FXML
    private TextField Class1;

    @FXML
    private TextField firstname1;
    @FXML
    private TextField sid;
    @FXML
    private TextField symboleNumberSearch, symbolenumber;
    @FXML
    private TextField Id1;
    @FXML
    private TextField lastname1;
    @FXML
    private TextField sn1, subjectSearchField;
    @FXML
    private Tab manage_user;
    @FXML
    private Pane ResultVIew;

    @FXML
    private TextField m1,yrs,result_year;

    @FXML
    private Button Addresult;
    @FXML
    private Button Updateresult;
    @FXML
    private Button Findresult;
    @FXML
    private Button Findstudent;
    @FXML
    private Button searchbtn, btnAdd;
    @FXML
    private Button updatebtn;
    @FXML
    private Button deletebtn;
    @FXML
    private ComboBox selectClass, selectSubject, selectStudent_Id,selectDivision,selectTerminal;
    @FXML
    private RadioButton rb1, rb2;
    @FXML
    private TableView<StudentData> studentTable;
    @FXML
    private TableView<ReslutData> resultTable;
    @FXML
    public Label lbl_user,lbl_userFullname;
    @FXML
    private TableColumn<StudentData, String> studentidCol,usernameCol,firstNameCol,lastNameCol,genderCol,dobCol,ClassCol,DOB_placeCol,fatherCol,motherCol,parentNumberCol,idCol;

    @FXML
    private TableColumn<ReslutData, String> symbolnoColR,studentidColR,firstNameColR,lastnameColR,ClassColR,terminalColR,subjectColR,marksColr,rollnoColR,resultyearColR;

    private DbConnection db;
    private ObservableList<StudentData> data;
    private ObservableList<ReslutData> Resultdata;
    int autoId;
    int subjectSize,subjectCount=1;
    public AdminController(){

        PageFormat pageFormat =new PageFormat();

    }
    public void initialize(URL url, ResourceBundle rb) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            hour = cal.get(Calendar.HOUR);
            //System.out.println(hour + ":" + (minute) + ":" + second);
            time.setText(hour + ":" + (minute) + ":" + second);
        }), new KeyFrame(Duration.seconds(1)));
        Calendar cal = new GregorianCalendar();
         month = cal.get(Calendar.MONTH);
         year = cal.get(Calendar.YEAR);
         day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(((month + 1) + "/" + day + "/" + year));
        String aa = (month + 1) + "/" + day + "/" + year;
        date.setText(aa);

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        yrs.setText(String.valueOf(year));
        result_year.setText(String.valueOf(year));

        //this.selectClass.setItems(FXCollections.observableArrayList(Admin.selectClass.values()));
        this.selectDivision.getItems().addAll("Admin","Teacher");
        this.selectClass.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        String terminalSQL= "SELECT * FROM terminal";

        this.db = new DbConnection();
        try {
            connection=null;
            try {
                connection = new DbConnection().getConnection();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            pr = connection.prepareStatement(terminalSQL);
            rs = pr.executeQuery();
            while (rs.next()){
                terminalList.addAll(rs.getString("terminal"));

            }
            selectTerminal.setItems(terminalList);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        LoadResultData();

    }



//    public void init(LoginController loginController){
//        login =loginController;
//    }

    @FXML
    private void radioSelect(ActionEvent event) {
        if (rb1.isSelected()) {
            selectGender = rb1.getText() + "\n";
        }
        if (rb2.isSelected()) {
            selectGender = rb2.getText() + "\n";
        }
    }

    @FXML
    private void btnLoad(ActionEvent event) throws SQLException {
        Load();
    }

    public void Load() {
        try {
            connection = DbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            rs = connection.createStatement().executeQuery("SELECT * FROM students");
            while (rs.next()) {
                this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),rs.getString(15),rs.getString(16)));
            }
        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
        this.idCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("s/n"));
        this.studentidCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("studentId"));
        this.firstNameCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("firstName"));
        this.lastNameCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("lastName"));

        //this.emailCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("email"));
        //  this.usernameCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("userName"));
        this.dobCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("DOB"));
        this.ClassCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("Classs"));
        this.genderCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("gender"));
        this.DOB_placeCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("DOB_place"));
        this.fatherCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("fatherName"));
        this.motherCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("motherName"));
        this.parentNumberCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("parentNumber"));
        this.studentTable.setItems(this.data);
    }


    public void LoadResultData() {
        try {
            connection = DbConnection.getConnection();
            this.Resultdata = FXCollections.observableArrayList();
            rs = connection.createStatement().executeQuery("SELECT * FROM resultr");
            while (rs.next()) {
                this.Resultdata.add(new ReslutData(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                        rs.getString(11)));
            }
        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
        this.symbolnoColR.setCellValueFactory(new PropertyValueFactory<ReslutData, String>("symbol_no"));
        this.studentidColR.setCellValueFactory(new PropertyValueFactory<ReslutData, String>("studentId"));
        this.firstNameColR.setCellValueFactory(new PropertyValueFactory<ReslutData, String>("firstName"));
        this.lastnameColR.setCellValueFactory(new PropertyValueFactory<ReslutData, String>("lastName"));

        //this.emailCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("email"));
        //  this.usernameCol.setCellValueFactory(new PropertyValueFactory<StudentData, String>("userName"));
        this.rollnoColR.setCellValueFactory(new PropertyValueFactory<ReslutData, String>("roll_No"));

        this.terminalColR.setCellValueFactory(new PropertyValueFactory<ReslutData, String>("terminal"));
        this.ClassColR.setCellValueFactory(new PropertyValueFactory<ReslutData, String>("CLASS"));
        this.subjectColR.setCellValueFactory(new PropertyValueFactory<ReslutData, String>("subject"));
        this.marksColr.setCellValueFactory(new PropertyValueFactory<ReslutData, String>("marks"));
        this.resultyearColR.setCellValueFactory(new PropertyValueFactory<ReslutData, String>("result_year"));
        this.resultTable.setItems(this.Resultdata);
    }


    public void alertMassage(String titleText, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setHeaderText(contentText);
        alert.showAndWait();
    }

    private boolean checkNull() {
        if ((studentId.getText() == "" || firstname.getText() == "" || email.getText() == "" || dob.getEditor()
                .getText() == "" || lastname.getText() == "" || selectGender == "" || DOBplace.getText() == "" || fathername
                .getText() == "" || mothername.getText() == "" || parentnumber.getText() == "")) {
            return true;
        } else return false;
    }

    @FXML
    private void btnAdd(ActionEvent event) throws ParseException {
        if (checkNull() == true) {
            alertMassage("Sorry,Fields Are empty", "", "All Fields are mandatory");
        } else {
            if( isInt(this.num.getText())== true && isInt(this.Class.getText())==true) {
                if( isString(this.studentId.getText())== true &&
                        isString(this.firstname.getText())==true&&
                        isString(this.lastname.getText())==true &&
                        isString(this.userName.getText())==true&&
                        isString(this.DOBplace.getText())==true&&
                        isString(this.fathername.getText())==true &&
                        isString(this.mothername.getText())==true&&
                        password.getText().matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}")&&
                        email.getText().matches("([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}")&&
                        parentnumber.getText().matches("(\\+)|(?:977)+(|\\-)(?:[0-9]{10})")){

                    String sqlInsert = "INSERT INTO `students`(`student_id`, `fname`, `lname`, `roll_no`, `username`, `password`, `gender`, `email`, `DOB`, `Class`, `Admission_year`, `DOB_place`, `father`, `mother`, `parentnumber`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        System.out.println(this.dob.getValue());
                        //int roll_num= Integer.parseInt(roll_string);

                        connection = new DbConnection().getConnection();
                        pr = connection.prepareStatement(sqlInsert);
                        pr.setString(1, this.studentId.getText());
                        pr.setString(2, this.firstname.getText());
                        pr.setString(3, this.lastname.getText());
                        pr.setInt(4, parseInt(this.num.getText()));
                        pr.setString(5, this.userName.getText());
                        pr.setString(6, this.password.getText());
                        pr.setString(7, this.selectGender);
                        pr.setString(8, this.email.getText());
                        pr.setString(9, this.dob.getValue().toString());
                        pr.setInt(10, parseInt(this.Class.getText()));
                        pr.setInt(11, parseInt(this.yrs.getText()));

                        pr.setString(12, this.DOBplace.getText());
                        pr.setString(13, this.fathername.getText());
                        pr.setString(14, this.mothername.getText());
                        pr.setString(15, this.parentnumber.getText());
                        pr.execute();


                        String sqlInsertIntoLogin = "INSERT INTO login(fullname,username,password,division)VALUES(?,?,?,?)";
                        //Connection connect = new DbConnection().getConnection();
                        pr = connection.prepareStatement(sqlInsertIntoLogin);
                        pr.setString(1, this.firstname.getText() + " " + this.lastname.getText());
                        pr.setString(2, this.userName.getText());
                        pr.setString(3, this.password.getText());
                        pr.setString(4, "student");
                        pr.execute();
                        btnLoad(event);
                        btnClear(event);
                        connection.close();
                        alertMassage("Congratulation","Successfull","Data Saved");
                        pr.close();
                    } catch (SQLIntegrityConstraintViolationException ce) {
                        alertMassage("Sorry,Duplicity ", "Student_ID,username,password field are Unique", "choose unique value for those student_id,password,username");

                    } catch (MySQLTransactionRollbackException mtre) {
                        alertMassage("ERROR, data wrong ", "some data you entered are wrong", "for example for String data " +
                                "you enter integer value or vice verse or you enter the more data for phone number !!! please check the fields.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else alertMassage("Incorrect data entered","","Please check the fields and enter the correct.And make sur that Phone number is of nepal");
            }else{ alertMassage("ERROR !!!!","","please check fields");}
        }
    }

    @FXML
    private void updatebtn(ActionEvent event) {
        String value0 = studentId.getText();
        String value1 = firstname.getText();
        String value2 = lastname.getText();
        String value3 = email.getText();
        String value4 = dob.getValue().toString();
        String value5 = Class.getText();
        String value6 = DOBplace.getText();
        String value7 = fathername.getText();
        String value8 = mothername.getText();
        String value9 = parentnumber.getText();
        String value10 = num.getText();
        String value11 = userName.getText();
        String value12 =password.getText();

        if (value0 != "" && value1 != "" && value2 != "" && value3 != "" && value3 != "" && value5 != "" && value4 !=
                "" && value5 != "" && value6 != "" && value7 != "" && value8 != "" && value9 != "") {

            if( isInt(this.num.getText())== true && isInt(this.Class.getText())==true) {
                if( isString(this.studentId.getText())== true &&
                        isString(this.firstname.getText())==true&&
                        isString(this.lastname.getText())==true &&
                        isString(this.userName.getText())==true&&
                        isString(this.DOBplace.getText())==true&&
                        isString(this.fathername.getText())==true &&
                        isString(this.mothername.getText())==true)
                {
                    if(    password.getText().matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}")&&
                           email.getText().matches("([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}")&&
                           parentnumber.getText().matches("(\\+)|(?:977)+(|\\-)(?:[0-9]{10})")){
                        String sql = "update students set student_id='" + value0 + "',fname='" + value1 + "',lname='" + value2 +
                                "',roll_no='" + value10 + "',username='" + value11 + "',password='" + value12 + "',gender='" + selectGender + "',email='" + value3 + "',DOB='" + value4 + "',class='" + value5 + "'," +
                                "DOB_place='" + value6 + "',father='" + value7 + "',mother='" + value8 + "',parentnumber='" +
                                value9 + "'where student_id='" + searchfield.getText() + "' ";
                        try {
                            connection = new DbConnection().getConnection();
                            pr = connection.prepareStatement(sql);
                            pr.execute();
                            alertMassage("Successful", "", "Update Sucessfull");
                        } catch (SQLSyntaxErrorException e) {
                            alertMassage("NUll,Sorry", "file not found", "there is no data found for student table ,if this  " +
                                    "pops-up repeatedly contact to your provider ");
                        } catch (SQLException we) {
                            we.printStackTrace();
                        }
                    }else{
                        alertMassage("ERROR!!!","password, email,phone number field or any of them are incorrect error","For example " +
                                "Binod123,email:binod123@gmail.com,pparenumber:9779803131111 or +977-9803131111"); }
                }else alertMassage("Incorrect data entered","please check fields ","some data that you enter is not String");
            }else{ alertMassage("ERROR !!!!","","please check fields");}
        }else alertMassage("NUll,Sorry", "", "fields are missing data");
        try {
            connection = new DbConnection().getConnection();
            String sql1 = "update resultr set student_id='" + value0 + "',fname='" + value1 + "',lname='" + value2 +
                    "'where student_id='" + value0 + "' ";
            pr = connection.prepareStatement(sql1);
            pr.execute();
            pr.close();
            connection.close();
        } catch (SQLSyntaxErrorException e) {
            alertMassage("NUll,Sorry", "file not found", "there is no data found for this student data in result " +
                    "table except for student table ,if this pops-up repeatedly contact to your provider ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchbtn(ActionEvent event) {
        try {
            String sql = "select * from students where student_id=? ";
            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(sql);
            pr.setString(1, searchfield.getText());
            rs = pr.executeQuery();
            if(isString(searchfield.getText())==true) {

                if (rs != null) {
                    while (rs.next()) {

                        studentId.setText(rs.getString("student_id"));
                        firstname.setText(rs.getString("fname"));
                        lastname.setText(rs.getString("lname"));
                        num.setText(rs.getString("roll_no"));
                        userName.setText("username");
                        password.setText("password");
                        if (rs.getString("gender") == "male") {
                            //value="male";
                            rb1.setSelected(false);
                            rb2.setSelected(true);
                        } else {
                            // value="female";
                            rb1.setSelected(true);
                            rb2.setSelected(false);
                        }
//                    DateStringConverter dateStringConverter= new DateStringConverter();
//                    date.setUserData(dateStringConverter.fromString(rs.getString("DOB")));
                        email.setText(rs.getString("email"));
                        Class.setText(rs.getString("class"));
                        DOBplace.setText(rs.getString("DOB_place"));
                        fathername.setText(rs.getString("father"));
                        mothername.setText(rs.getString("mother"));
                        parentnumber.setText(rs.getString("parentnumber"));
                        alertMassage("Sucessfull", "", "Data Found");
                        //dob.setValue(LocalDate.parse(rs.getString(5)));
                    }
                } else alertMassage("NUll,Sorry", "", "Data not Found");

                connection.close();
                pr.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void deletebtn(ActionEvent event) {
        String sql = "delete from students where  student_id=? ";
        String sql1 = "delete from resultr where student_id=? ";
        if (isString(searchfield.getText())==false)
            try {
                connection = new DbConnection().getConnection();
                pr = connection.prepareStatement(sql);
                pr.setString(1, studentId.getText());
                pr.execute();
                pr = connection.prepareStatement(sql1);
                pr.setString(1, studentId.getText());
                pr.execute();
                if (pr != null) {
                    alertMassage("Successful", "", "Data Deleted");
                }
            } catch (SQLSyntaxErrorException ex) {
                alertMassage("NUll,Sorry", "file not found", "there is no data found for this student data in 'resultr' " +
                        "table except for 'student' table ,if this" + " pops-up repeatedly contact to your provider ");
            } catch (Exception e) {
            }
    }

    @FXML
    private void btnClear(ActionEvent event) {
        this.firstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
        this.dob.setValue(null);
        this.password.setText("");
        this.num.setText("");
        this.userName.setText("");
        this.num.setText("");
        this.studentId.setText("");
        this.rb1.setSelected(true);
        this.rb1.setSelected(false);
        this.DOBplace.setText("");
        this.fathername.setText("");
        this.mothername.setText("");
        this.parentnumber.setText("");
    }


//    @FXML
//    private void Findstudent(ActionEvent event) {
//        try {
//            String sql = "select * from students where student_id=? ";
//            Connection connection = new DbConnection().getConnection();
//            PreparedStatement pr = connection.prepareStatement(sql);
//            pr.setString(1, sid.getText());
//            ResultSet rs = pr.executeQuery();
//            if (rs != null) {
//                while (rs.next()) {
//                    Id1.setText(String.valueOf(rs.getInt(2)));
//                    sid.setText(rs.getString(2));
//                    firstname1.setText(rs.getString(3));
//                    lastname1.setText(rs.getString(4));
//                    Class1.setText(rs.getString(8));
//                    alertMassage("Sucessfull", "", "Data Found");
//                    //dob.setValue(LocalDate.parse(rs.getString(5)));
//                }
//            } else alertMassage("NUll,Sorry", "", "Data not Found");
//            connection.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

    @FXML
    public void Addresult(ActionEvent event) {
        checkDuplicateSubject();
        String sqlInsertResult = "INSERT INTO resultr (student_id,symbol_number,firstname,lastname,roll_no,terminal,class,subject,marks,result_year) VALUES(?,?,?,?,?,?,?,?,?,?)";
        checkDuplicateSubject();
        System.out.println(validatestudentId);
        System.out.println(validateSubject);
        System.out.println(validateTerminal);
        try {
            if ( validateSubject.equals(selectSubject.getValue().toString()) && validatestudentId.equals(selectStudent_Id.getValue().toString()) && validateTerminal.equals(selectTerminal.getValue().toString())) {
                //selectSubject.getSelectionModel().selectNext();
                alertMassage("Sorry,Duplicity found", "", "Sorry a student a class doest take part in same subject in same terminal ");
            } else{
                subjectSizeString= String.valueOf(subjectSize);
                subjectCountString= String.valueOf(subjectSize);
                if (subjectSizeString.equals(subjectCountString) || subjectCount < subjectSize) {
                    System.out.println("if");
                    String resultterminal1 =  selectTerminal.getValue().toString();
                    int resultClass1 = Integer.parseInt(selectClass.getValue().toString());
                    String resultSubject1 =  selectSubject.getValue().toString();
                    System.out.println("above code if fine");
                    connection = new DbConnection().getConnection();
                    pr = connection.prepareStatement(sqlInsertResult);
                    pr.setString(1, this.selectStudent_Id.getValue().toString());
                    pr.setString(2, this.symbolenumber.getText());
                    pr.setString(3, this.firstname1.getText());
                    pr.setString(4, this.lastname1.getText());
                    pr.setInt(5, parseInt(this.roll_no1.getText()));
                    pr.setString(6, resultterminal1);
                    pr.setInt(7, resultClass1);
                    pr.setString(8, resultSubject1);
                    pr.setString(9, this.m1.getText());
                    pr.setInt(10, parseInt(this.yrs.getText()));
                    pr.execute();
                    selectSubject.getSelectionModel().selectNext();
                    subjectCount++;
                    connection.close();
                    pr.close();
                } else {
                    selectStudent_Id.getSelectionModel().selectNext();
                    subjectCount = 0;
                    InsertCombobox(event);
                    //System.out.println("else");
                    //String getLastId;
                    // tommorow from here start;
                    // subjectCount = 0;
                }
            }
        } catch (SQLIntegrityConstraintViolationException ce) {
            alertMassage("Sorry,Some Fields Are empty", "", "All Fields are mandatory,if this occur repeatedly " + "please click again your gender then click add/update");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

//    private void checkStudent() {
//        String checkSql ="SELECT * FROM students WHERE student_id=?";
//        try{
//            if(Id1.getText()!="") {
//                Connection connection = new DbConnection().getConnection();
//                PreparedStatement pr = connection.prepareStatement(checkSql);
//                pr.setString(1, this.Id1.getText());
//                pr.execute();
//                if (pr == null) {
//                    value = "1";
//                } else value = "";
//            }else{
//                Connection connection = new DbConnection().getConnection();
//                PreparedStatement pr = connection.prepareStatement(checkSql);
//                pr.setString(1, this.Id1.getText());
//                pr.execute();
//                if (pr == null) {
//                    value = "1";
//                } else value = "";
//            }
//        }catch(SQLException ex){
//            ex.printStackTrace();
//
//        }
//
//
// }

@FXML
private void Findresult(ActionEvent event) {

}

    @FXML
    private void Updateresult(ActionEvent event) {
//        // checkStudent();
//        if (symbolenumber.getText() != "" && subjectSearchField.getText() !="") {
//            String value0 = Id1.getText();
//            String value1 = symbolenumber.getText();
//            String value2 = firstname1.getText();
//            String value3 = lastname1.getText();
//            String value4 = term.getText();
//            String value5 = Class.getText();
//            String value6 = sn1.getText();
//            String value7 = m1.getText();
//            String value8 = num.getText();
//
//            if (value0 != "" && value1 != "" && value2 != "" && value3 != "" && value3 != "" && value5 != "" && value4 !=
//                    "" && value5 != "" && value6 != "" && value7 != "" && value8 != "") {
//                String sql = "update resultr set student_id='" + value0 + "',symbol_number='" + value1 + "',fristname='" + value2 +
//                        "',lastname='" + value3 + "',terminal='" + value4 + "',class='" + value5 + "',subject='" + value6 + "'," +
//                        "marks='" + value7 + "',tot_sub='" + value8 + "'where symbole_number='" + symboleNumberSearch.getText() + "' AND subject='" + subjectSearchField.getText() + "' ";
//                try {
//                    connection = new DbConnection().getConnection();
//                    pr = connection.prepareStatement(sql);
//                    pr.execute();
//                    alertMassage("Successful", "", "Update Sucessfull");
//                } catch (SQLSyntaxErrorException e) {
//                    alertMassage("NUll,Sorry", "file not found", "there is no data found for result table ,if this  " +
//                            "pops-up repeatedly contact to your provider ");
//                } catch (SQLException we) {
//                    we.printStackTrace();
//                }
//            } else alertMassage("NUll,Sorry", "", "fields are missing data");
//
//        } else if (symbolenumber.getText() == "" || subjectSearchField.getText() == "") {
//            alertMassage("Sorry, ", "", " symbole number and subject name is needed");
//
//        } else
//            alertMassage("Sorry, ", "", " check all fields properly for search purpose");


        try{
            Stage stage = (Stage)this.Updateresult.getScene().getWindow();
            stage.close();
            Stage updateStage =new Stage ();
            FXMLLoader updateLoader =new FXMLLoader();
            Parent root =(Pane)updateLoader.load(getClass().getResource("/Admin/UpdateFXML.fxml").openStream());
            Scene scene=new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Update Dashboard");
            updateStage.setResizable(false);
            updateStage.setMaximized(false);
            updateStage.show();
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }
    public void determiningWho(ActionEvent event,String usrFullName,String usrDiv) {
        if (usrDiv == "Teacher"){
            manage_user.setDisable(true);
            who1.setText("DIVISION:"+" "+usrDiv);
           who.setText(usrFullName);

        }if (usrDiv == "Admin"){
            manage_user.setDisable(false);
          who.setText(usrFullName);
            who1.setText("DIVISION:"+" "+usrDiv);
        }

    }

    @FXML
    //class combobox is hit first <1>
    public void checkStudentWithClass(ActionEvent event){

        String SQL="Select * FROM students WHERE Class=?";
        try {
            String ckeckClass=this.selectClass.getValue().toString();

            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(SQL);
            pr.setString(1,ckeckClass);
            rs=pr.executeQuery();
            if(rs.next()){
                //from here InsertCombobox is called.
                InsertCombobox(event);
                System.out.println("select terminal");
            }else{
                firstname1.setText("");
                lastname1.setText("");
                roll_no1.setText("");
                m1.setText("");
                ClearCombobox();
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    //<2>
    public void InsertCombobox(ActionEvent event) {
        String yyyy= (this.result_year.getText());
        System.out.println( this.selectClass.getValue().toString());
        // String InsertComboxSql = "SELECT * FORM class WHERE Class=?";
        switch ((this.selectClass.getValue()).toString()) {
            case "1":
                ClearCombobox();
                FillComboboxsubject("1");
                FillComboboxStudentsId("1",yyyy);
                FillInfo();
                //FillPersonelInfo();
                break;
            case "2":
                ClearCombobox();
                FillComboboxsubject("2");
                FillComboboxStudentsId("2",yyyy);
                //FillPersonelInfo();
                FillInfo();
                break;

            case "3":
                ClearCombobox();
                FillComboboxsubject("3");
                FillComboboxStudentsId("3",yyyy);
                FillInfo();
                //FillPersonelInfo();
                break;
            case "4":
                ClearCombobox();
                FillComboboxsubject("4");
                FillComboboxStudentsId("4",yyyy);
                FillInfo();
                //FillPersonelInfo();
                break;
            case "5":
                ClearCombobox();
                FillComboboxsubject("5");
                FillComboboxStudentsId("5",yyyy);
                FillInfo();
                //FillPersonelInfo();
                break;
            case "6":
                ClearCombobox();
                FillComboboxsubject("6");
                FillComboboxStudentsId("6",yyyy);
                FillInfo();
                //FillPersonelInfo();
                break;
            case "7":
                ClearCombobox();
                FillComboboxsubject("7");
                FillComboboxStudentsId("7",yyyy);
                FillInfo();
                //FillPersonelInfo();
                break;
            case "8":
                ClearCombobox();
                FillComboboxsubject("8");
                FillComboboxStudentsId("8",yyyy);
                FillInfo();
                //////////FillPersonelInfo();
                break;
            case "9":
                ClearCombobox();
                FillComboboxsubject("9");
                FillComboboxStudentsId("9",yyyy);
                FillInfo();
                //FillPersonelInfo();
                break;
            case "10":
                ClearCombobox();
                FillComboboxsubject("10");
                FillComboboxStudentsId("10",yyyy);
                FillInfo();
                //FillPersonelInfo();
                break;
        }
    }
    //<2.1>
    public void FillComboboxsubject(String Classvalue) {
        String InsertComboxSql = "SELECT * FROM `class` WHERE class=?";
        try {
            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(InsertComboxSql);
            pr.setString(1, Classvalue);
            rs = pr.executeQuery();
            while (rs.next()) {
                subjectList.addAll(rs.getString("Subject"));
            }
            //size = subjectList.size();
            //System.out.println(size);
            selectSubject.setItems(subjectList);
            selectSubject.getSelectionModel().selectNext();

            pr.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //alertMassage("Sorry, ", "", " symbole number and subject name is needed");
        }
    }

    public void ClearCombobox() {
        selectSubject.getItems().removeAll(selectSubject.getItems());
        selectStudent_Id.getItems().removeAll(selectStudent_Id.getItems());
        //selectTerminal.getItems().removeAll(selectTerminal.getItems());
    }
//<2.2>
    public void FillComboboxStudentsId(String Classvalue2,String yearStudent) {
        try {
            String sql = "SELECT * FROM students WHERE Class=? AND Admission_year=? ";
            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(sql);
            pr.setString(1, Classvalue2);
            pr.setString(2, yearStudent);
            rs = pr.executeQuery();
            while (rs.next()) {
                student_IdList.addAll(rs.getString("student_id"));
            }
            selectStudent_Id.setItems(student_IdList);
            FillInfo();
            selectStudent_Id.getSelectionModel().selectNext();
            pr.close();
            rs.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//    public void FillPersonelInfo() {
//        String SELECTSQL = "SELECT * FORM `students` WHERE `student_id`=?";
//        try {
//            Connection connection = new DbConnection().getConnection();
//            PreparedStatement pr = connection.prepareStatement(SELECTSQL);
//            String id= selectStudent_Id.getValue().toString();
//            pr.setString(1, id);
//          //  pr.setString(2, this.selectClass.getValue().toString());
////            pr.setInt(2, Integer.parseInt(this.result_year.getText()));
//
//            ResultSet rs = pr.executeQuery();
//            while (rs.next()) {
//                firstname1.setText(rs.getString("fname"));
//                lastname1.setText(rs.getString("lname"));
//                roll_no1.setText(String.valueOf(rs.getInt("roll_no")));
//
//            }
//            pr.close();
//            rs.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//<2.3>
    public void FillInfo() {
        String SELECTSQL = "SELECT * FROM students WHERE student_id=?";
        try {
            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(SELECTSQL);
            String id=(String)selectStudent_Id.getValue();
            pr.setString(1, id);
            rs = pr.executeQuery();
            if(rs !=null) {
                while (rs.next()) {
                    firstname1.setText(rs.getString("fname"));
                    lastname1.setText(rs.getString("lname"));
                    roll_no1.setText(String.valueOf(rs.getInt("roll_no")));
                }
//                roll_no1.setItems(roll_nodList);
//                roll_no.getSelectionModel().selectNext();

            }else alertMassage("NUll,Sorry", "", "Data not Found");
            pr.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    //calls when terminal is selected
    public void checkRemainingResult(ActionEvent event){
        try{
            //return the last id insert data from result.
            autoId=Count();
            //returns the student id from last id insert id.
            if (getDataFromResult(autoId) != null ){
                //returns the student id from student by using the id from result
                studentid1=getDataFromResult(autoId);
                studentid2 = getDataFromStudent(studentid1);
                System.out.println(studentid1 + " " + " " + studentid2);
                //checks the both id from result and student data and if match
                if (studentid1.equals(studentid2)) {
                    sn = sn + 1;
                    String sql3 = "SELECT * FROM students WHERE sn=?";
                    pr = connection.prepareStatement(sql3);
                    pr.setString(1, String.valueOf(sn));
                    rs = pr.executeQuery();
                    if (rs!=null) {
                        while (rs.next()) {
                            assignVar = Integer.parseInt((rs.getString("roll_no")));
                            System.out.println(assignVar + " check equals of studentid1 and studentid2");
                            studentid2 = rs.getString("student_id");
                            firstname1.setText(rs.getString("fname"));
                            lastname1.setText(rs.getString("lname"));
                            roll_no1.setText(String.valueOf(sn));
                            //sn = Integer.parseInt((resultSet12345.getString("sn")));
                            //System.out.println(resultSet12.getString("student_id"));
                            // String var1 =resultSet12.getString("student_id");
                            selectStudent_Id.getSelectionModel().selectNext();
                        }
                    }

                    //selectStudent_Id.getSelectionModel().selectNext();
//                    roll_no1.setText(String.valueOf(sn));
//                    firstname1.setText(rs.getString("fname"));
//                    lastname1.setText(rs.getString("lname"));
                }
            }else
                InsertCombobox(event);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //For getting last insert auto increment id in resultr
    public int Count(){
        try {
            String countSQL = "SELECT COUNT(*) FROM resultr WHERE Class=? AND terminal=?";
            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(countSQL);
            int selectclass = Integer.valueOf(selectClass.getValue().toString());
            String selectterminal = (String) selectTerminal.getValue();
            pr.setInt(1, selectclass);
            pr.setString(2, selectterminal);
            rs = pr.executeQuery();
            // ResultSet rs=pr.execute();
            if (rs.next()) {
                // System.out.println(resultSet.getString("count(*)"));
                autoId = Integer.parseInt(rs.getString("count(*)"));
                System.out.println(autoId);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return autoId;

    }
    //part of  checkRemainingResult() method call getting student last inserted result student id from resultr table
    public String getDataFromResult(int getId) {
        try {
            String sql1 = "SELECT * FROM resultr WHERE sn=?";
            pr = connection.prepareStatement(sql1);
            pr.setString(1, String.valueOf(getId));
            rs = pr.executeQuery();
            if (rs.next()) {
//                int var = Integer.parseInt((rs.getString("roll_no")));
//                System.out.println(var);
                //System.out.println(resultSet12.getString("student_id"));
                returnValue = rs.getString("student_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return returnValue;
    }
    //calls the this method for getting student id from student table
    public String getDataFromStudent(String getStudentId){
        try {
            String sql2="SELECT * FROM students WHERE student_id=?";
            pr= connection.prepareStatement(sql2);
            pr.setString(1,getStudentId);
            rs =pr.executeQuery();
            if (rs.next()) {
//                int var = Integer.parseInt((rs.getString("roll_no")));
//                System.out.println(var);
                //
                returnValue=rs.getString("student_id");
                sn = Integer.parseInt((rs.getString("sn")));
                //System.out.println(resultSet12.getString("student_id"));
                // String var1 =resultSet12.getString("student_id");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return returnValue;
    }
    //calls for checking to avoid  duplicate subjectwith marks while recording result
    public void checkDuplicateSubject(){
        try{
            String SqlQuery = "SELECT * FROM resultr WHERE sn=?";
            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(SqlQuery);
            pr.setString(1, String.valueOf(autoId));
            rs = pr.executeQuery();
            if(rs !=null) {
                while (rs.next()) {
                    validateSubject = String.valueOf(rs.getString("subject"));
                    validatestudentId = String.valueOf(rs.getString("student_id"));
                    validateTerminal = String.valueOf(rs.getString("terminal"));
                }
                // System.out.print(validateTerminal + "="+validatestudentId+ "="+validateSubject);
            }
        }catch(SQLException ee){
            ee.printStackTrace();
        }
    }
    //calls for generating the Student Id.
    @FXML
    public void GenerateId(ActionEvent event){
        String str =String.valueOf(year) ;
        String result = str.substring(2,str.length());

        switch (Class.getText()){
            case "1":
                if(validateGeneratedId() == true) {
                    studentId.setText(result + "ONE" + "0" + num.getText());
                }else{
                    warrning("1");
                }

                break;
            case "2":
                if(validateGeneratedId() == true) {

                } else {
                    studentId.setText(result + "TWO" + "0" + num.getText());
                    warrning("2");
                }break;
            case "3":
                if(validateGeneratedId() == true) {

                    studentId.setText(result + "THREE" + "0" + num.getText());
                }else
                    warrning("3");
                break;
            case "4":
                if(validateGeneratedId() == true) {

                    studentId.setText(result + "FOUR" + "0" + num.getText());
                }else   warrning("4");
                break;
            case "5":if(validateGeneratedId() == true) {

                studentId.setText(result + "FIVE" + "0" + num.getText());
            }else   warrning("5");
                break;
            case "6":
                if(validateGeneratedId() == true) {
                    studentId.setText(result + "SIX" + "0" + num.getText());
                }else warrning("6");
                break;
            case "7":
                if(validateGeneratedId() == true) {
                    studentId.setText(result + "SEVEN" + "0" + num.getText());
                }else warrning("8");
                break;
            case "8":
                if(validateGeneratedId() == true) {

                    studentId.setText(result + "EIGHT" + "0" + num.getText());
                }else warrning("8");
                break;
            case "9":if(validateGeneratedId() == true) {

                studentId.setText(result + "NINE" + "0" + num.getText());
            }else   warrning("9");
                break;
            case "10":
                if(validateGeneratedId() == true) {

                    studentId.setText(result + "TEN" + num.getText());
                }else   warrning("10");
                break;
            default:
                alertMassage("empty field","","please choose a class between 1-10 but in numbers");
        }

    }
    public boolean isInt(String Int){
        try{
        int i =Integer.parseInt(Int);
        return true;
    }catch(NumberFormatException nfe){
            alertMassage( "ERROR!!!!!","Please check fields "," given input is not integers");
    return  false;
        }

    }
    public boolean isString(String Int1){
        try{
            int i =Integer.parseInt(Int1);
            alertMassage( "ERROR!!!!!","Please check fields "," given input is not String");

            return false;
        }catch(NumberFormatException nfe){
            return  true;
        }

    }
    public boolean validateGeneratedId(){
String SQLCheck="SELECT * FROM students WHERE Class=? AND roll_no=?";
        try{
            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(SQLCheck);
            pr.setString(1,Class.getText() );
            pr.setString(2,num.getText() );
            rs = pr.executeQuery();
            if (rs != null){
               return false;
            }
        }catch(SQLException ee){
            ee.printStackTrace();
            return true;
        }

        return true;
    }
    public void warrning(String Class){
        alertMassage("Duplicate fount",Class+" class has already an"+num.getText(),"Please choose next roll_no");
    }

@FXML
    public void Print(ActionEvent actionEvent){
    System.out.println("ok");


   // Printer printer = Printer.getDefaultPrinter();
//    PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
//    double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
//    double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
    //node.getTransforms().add(new Scale(14, 14));

    PrinterJob job = PrinterJob.createPrinterJob();
    if (job != null) {
        boolean success = job.printPage(ResultVIew);
        if (success) {

            job.endJob();
        }
    }
}


//    @Override
//    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
//        return 0;
//    }
}

