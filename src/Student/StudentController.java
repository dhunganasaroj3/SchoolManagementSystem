package Student;
import Admin.AdminController;
import LoginApp.LoginController;
import dbUtil.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static LoginApp.option.Admin;

public class StudentController implements Initializable {
    @FXML
    private Label fName, firstNameResult;

    @FXML
    private Label userName;

    @FXML
    private Label roll_no;

    @FXML
    private Label gender;

    @FXML
    private Label email;

    @FXML
    private Label dob;

    @FXML
    private Label Class;

    @FXML
    private Label admission,subMarks1,subMarks2,subMarks3,subMarks4,subMarks5,subMarks6,subMarks7,tot,per,division,status;

    @FXML
    private Label dobPlace;

    @FXML
    private Label fatherName;

    @FXML
    private Label motherName;

    @FXML
    private Label pNumber;

    @FXML
    private Label lName;

    @FXML
    private Label lbl_id,marksResult;

    @FXML
    private ComboBox chooseterminal,chooseSubject;
    private DbConnection db;
    Connection connection;
    PreparedStatement pr;
    ResultSet rs;
    final ObservableList terminalResultList = FXCollections.observableArrayList();
    final ObservableList subjectResultMarksList = FXCollections.observableArrayList();
    final ObservableList subjectResultList = FXCollections.observableArrayList();
    private String id;
    private String ter;
    AdminController adminController;
    //function to extract the resultr database "marks" colomn data in subjectResultMarksList
    @FXML
   public void terminalAction(ActionEvent actionEvent) {
        id = lbl_id.getText();
        ter = chooseterminal.getValue().toString();
        String loadResultSql = "SELECT * FROM resultr WHERE student_id=? AND terminal=?";
        try {
            // String Resultsql = "SELECT * FROM resultr WHERE student_id=? AND terminal=?";
            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(loadResultSql);
            // String stringlbl_id=lbl_id.getId();
            pr.setString(1, id);
            pr.setString(2, ter);
            rs = pr.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    subjectResultMarksList.addAll(rs.getString("marks"));
//                    firstNameResult.setText(rs.getString("subject"));
//                    marksResult.setText(String.valueOf(rs.getInt("marks")));
                }

//                System.out.println(subjectResultMarksList.get(5));
//                System.out.println(subjectResultMarksList.get(6));
                }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoginController loginController = new LoginController();
        this.db = new DbConnection();
        //first,second,third and finall terminal load gare ko chu window load juuna saath.
        loadTerminal();
    }

    public void initData(String[] setData) {
        this.lbl_id.setText(setData[1]);
        this.fName.setText(setData[2]);
        this.lName.setText(setData[3]);
        this.roll_no.setText(setData[4]);
        this.userName.setText(setData[5]);
        this.gender.setText(setData[7]);
        this.email.setText(setData[8]);
        this.dob.setText(setData[9]);
        this.Class.setText(setData[10]);
        this.admission.setText(setData[11]);
        this.dobPlace.setText(setData[12]);
        this.fatherName.setText(setData[13]);
        this.motherName.setText(setData[14]);
        this.pNumber.setText(setData[15]);

    }
//terminal combobox ma terminals load garney code
    public void loadTerminal() {
        String terminalSQL = "SELECT * FROM terminal";
        try {
            Connection connection = null;
            try {
                connection = new DbConnection().getConnection();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            pr = connection.prepareStatement(terminalSQL);
            rs = pr.executeQuery();
            if(rs!=null){

                while (rs.next()) {
                    terminalResultList.addAll(rs.getString("terminal"));

                }
            }else adminController.alertMassage("data not found","","the terminal tha you choose is not Taken ");
            //System.out.println(terminalResultList.get(0));
            chooseterminal.setItems(terminalResultList);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
//function to display marks according to class 1-10
@FXML
    public void displayMarks(ActionEvent actionEvent) {
        switch ((this.Class.getText())) {
            case "1" : case "2": case "3": case "4": case "5":
                //Choose this conversion ony "String.valueOf(Object Obj);"
                subMarks1.setText(String.valueOf(subjectResultMarksList.get(0)));
                subMarks2.setText(String.valueOf(subjectResultMarksList.get(1)));
                subMarks3.setText(String.valueOf(subjectResultMarksList.get(2)));
                subMarks4.setText(String.valueOf(subjectResultMarksList.get(3)));
                subMarks5.setText(String.valueOf(subjectResultMarksList.get(4)));
                calculateResult("option1");
                break;

            case "6":case "7":case "8":case "9":case "10":
                subMarks1.setText(String.valueOf(subjectResultMarksList.get(0)));
                subMarks2.setText(String.valueOf(subjectResultMarksList.get(1)));
                subMarks3.setText(String.valueOf(subjectResultMarksList.get(2)));
                subMarks4.setText(String.valueOf(subjectResultMarksList.get(3)));
                subMarks5.setText(String.valueOf(subjectResultMarksList.get(4)));
                subMarks6.setText(String.valueOf(subjectResultMarksList.get(5)));
                calculateResult("option2");
                break;

        }
    }
    //function to calculate marks like total ,percentage ,division ,status , and if you have time add more.
    public void calculateResult(String var){
        if(var=="option1") {
            tot.setText(String.valueOf((Integer.parseInt((subMarks1.getText())) + Integer.parseInt((subMarks2.getText())) + Integer.parseInt((subMarks3.getText())) + Integer.parseInt((subMarks4.getText())) + Integer.parseInt((subMarks5.getText())))));
             //percentage, divisionand pass ra fail ko kaam bhaki cha.
        }
        if(var=="option2"){
            tot.setText(String.valueOf((Integer.parseInt((subMarks1.getText())) + Integer.parseInt((subMarks2.getText())) + Integer.parseInt((subMarks3.getText())) + Integer.parseInt((subMarks4.getText())) + Integer.parseInt((subMarks5.getText())) + Integer.parseInt((subMarks6.getText())))));
            //percentage, divisionand pass ra fail ko kaam bhaki cha
        }
    }




}
