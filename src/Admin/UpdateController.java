package Admin;

import dbUtil.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Admin.AdminController;
import java.net.URL;

import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class UpdateController implements Initializable {
    @FXML
    private ComboBox chooseClass,chooseTerminal;

    @FXML
    private ComboBox one;

    @FXML
    private TextField marks1,symbolnumber;

    @FXML
    private Button searchSubject;
    public int status;

    public String firstname,lastname,rollno,resultyear;

    @FXML
    private TextField enterId;
    AdminController adminController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final ObservableList selectClassForUpdateList = FXCollections.observableArrayList();
        final ObservableList selectTerminalForUpdateList = FXCollections.observableArrayList();
        this.chooseClass.getItems().addAll(1,2,3,4,5,6,7,8,9,10);

        String terminalSQL= "SELECT * FROM terminal";
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
                selectTerminalForUpdateList.addAll(rs.getString("terminal"));

            }
            chooseTerminal.setItems(selectTerminalForUpdateList);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    final ObservableList subjectListForUpdate = FXCollections.observableArrayList();
    Connection connection;
    PreparedStatement pr = null;
    ResultSet rs = null;

    @FXML
 public void selectClassForUpdate(ActionEvent event) {

        String InsertComboxSql = "SELECT * FROM `class` WHERE class=?";
        try {
            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(InsertComboxSql);
            pr.setString(1, chooseClass.getValue().toString());
            rs = pr.executeQuery();
            one.getItems().removeAll(one.getItems());

            while (rs.next()) {
                subjectListForUpdate.addAll(rs.getString("Subject"));
            }
            //size = subjectList.size();
            //System.out.println(size);
            one.setItems(subjectListForUpdate);
            one.getSelectionModel().selectNext();

            pr.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //alertMassage("Sorry, ", "", " symbole number and subject name is needed");
        }

    }

    @FXML
 public void searchSubject(ActionEvent event) {
        String InsertComboxSql = "SELECT * FROM `resultr` WHERE student_id=? AND terminal=? AND subject=?";
        try {
            connection = new DbConnection().getConnection();
            pr = connection.prepareStatement(InsertComboxSql);
            pr.setString(1, enterId.getText());
            pr.setString(2,chooseTerminal.getValue().toString());
            pr.setString(3,one.getValue().toString());
            rs = pr.executeQuery();
            if (rs !=null) {
                while (rs.next()) {
                     // subjectListForUpdate.addAll(rs.getString("Subject"));
                        //saveToUpdate(event,rs.getString("student_id"),rs.getString("terminal"),rs.getString("subject"),"UpdateQuery");
                        marks1.setText(String.valueOf(rs.getInt("marks")));
                        symbolnumber.setText(String.valueOf(rs.getInt("symbol_number")));
                        firstname=rs.getString("firstname");
                        lastname=rs.getString("lastname");
                        rollno=String.valueOf(rs.getInt("roll_no"));
                        resultyear=String.valueOf(rs.getInt("result_year"));
                        System.out.println(firstname+lastname+rollno+resultyear);
                    status=0;
                }
            }else{
                adminController.alertMassage("data not found ","","It seems that the student didn't " +
                        "appears in exam OR you forgot save the " +
                        "data if so then enter the marks for the " +
                        "subject press 'Save' OR search for another ");
                        status=1;
            }

                //size = subjectList.size();
            //System.out.println(size);
            //  one.setItems(subjectListForUpdate);
            //one.getSelectionModel().selectNext();

            pr.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //alertMassage("Sorry, ", "", " symbole number and subject name is needed");
        }




    }
    @FXML
    public void saveToUpdate(ActionEvent event) {
        //get variable to be changed
        if (status==0){
            String updateSql="UPDATE resultr SET subject='"+one.getValue().toString()+"',marks='"+ marks1.getText()+"',='"+ marks1.getText()+"' WHERE symbole_number='" + symbolnumber.getText() + "' AND subject='" + one.getValue().toString() + "' AND student_id='"+enterId.getText()+"'";
            try {
                connection = new DbConnection().getConnection();
                pr = connection.prepareStatement(updateSql);
                pr.executeUpdate();
                status=0;
                adminController.alertMassage("Successful", "", "Update Sucessfull");
            } catch (SQLSyntaxErrorException e) {
                adminController.alertMassage("NUll,Sorry", "file not found", "there is no data found for result table ,if this  " +
                        "pops-up repeatedly contact to your provider ");
            } catch (SQLException we) {
                we.printStackTrace();
            }
        }
        if (status==1){
            String InsertSql="INSERT INTO resultr (symbol_number,student_id,firstname,lastname,roll_no,terminal,class,subject,marks,result_year) VALUES(?,?,?,?,?,?,?,?,?,?)";
            try {
                chooseClass.getValue().toString();
                chooseTerminal.getValue().toString();
                one.getValue().toString();
                enterId.getText();
                marks1.getText();
                symbolnumber.getText();
                connection = new DbConnection().getConnection();
                pr = connection.prepareStatement(InsertSql);
                pr.setString(1,enterId.getText());
                pr.setString(2, symbolnumber.getText());
                pr.setString(3, firstname);
                pr.setString(4, lastname);
                pr.setInt(5, Integer.valueOf(rollno));
                pr.setString(6, chooseTerminal.getValue().toString());
                pr.setInt(7, Integer.valueOf(chooseClass.getValue().toString()));
                pr.setString(8, one.getValue().toString());
                pr.setInt(9, Integer.valueOf(marks1.getText()));
                pr.setInt(10, Integer.valueOf(resultyear));
                pr.execute();
                status=0;
            }catch(SQLException ee){
                ee.printStackTrace();

            }
        }


    }


}
