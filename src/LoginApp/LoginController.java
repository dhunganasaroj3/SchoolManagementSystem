package LoginApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Admin.AdminController;
import Student.StudentController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import dbUtil.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import Student.StudentController;

public class LoginController implements Initializable {
	@FXML
private Label dbstatus;
	@FXML
private TextField username;
    @FXML
private PasswordField password;
    @FXML
private ComboBox<option> combobox;
    @FXML
private Button loginButton;
    @FXML
private Label loginStatus;
    private AdminController admin;
    public String fullname;
	public String usr;
	public String pass;
	public String divs;
	Connection connection;
	PreparedStatement pr = null;
	ResultSet rs = null;
public String [] sent=new String[17];
public int i=1;
	public  LoginController(){
		try {
			connection = DbConnection.getConnection();

		}catch(SQLException ex){
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (this.connection == null) {
			System.exit(1);
		}
		}
	public boolean isDatabaseConnected()
	{
		return this.connection != null;
	}

	public void initialize (URL url, ResourceBundle rb) {
		if (isDatabaseConnected()) {
			this.dbstatus.setText("DataBase Is 'Connected' ");
		}else {this.dbstatus.setText("DataBase Is Not 'Connected' ");}
		this.combobox.setItems(FXCollections.observableArrayList(option.values()));
		//lbl_user.setText();
	}
	@FXML
	public void Login(ActionEvent event)throws IOException
	{
		try
		{
			usr=username.getText();
			pass=password.getText();
			String div = ((option)this.combobox.getValue()).toString();
			String sql = "SELECT * FROM login where username =? and password =? and division=?";
			pr = this.connection.prepareStatement(sql);
			pr.setString(1, usr);
			pr.setString(2, pass);
			pr.setString(3, div);
			rs = pr.executeQuery();
			if (rs.next()) {
				Stage stage = (Stage)this.loginButton.getScene().getWindow();
				stage.close();

				switch (((option)this.combobox.getValue()).toString())
				{
					case "Admin":
						dataAssign(username.getText(),password.getText());
						adminlogin(event);
						break;
					case "Student":
						dataPass(username.getText(),password.getText());
						studentLogin(event);
						break;
					case "Teacher":
						dataAssign(username.getText(),password.getText());
						adminlogin(event);
						break;
				}
			}else{
				alertMassage("Login Error","","\"the username password and the division that you " +
						"provide doesn't match in your database ");
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}catch (NullPointerException ex){
//			ex.printStackTrace();
			alertMassage("Login Error","fields are missing","\"check all fields!!!!! ");
		}
	}
	public void alertMassage (String titleText,String headerText,String contentText){
		Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(titleText);
		alert.setHeaderText(headerText);
		alert.setHeaderText(contentText);
		alert.showAndWait();
	}
	public void studentLogin(ActionEvent event)throws IOException{
		try{

			//Stage studentSatge =new Stage();
			FXMLLoader studentLoader =new FXMLLoader();
			studentLoader.setLocation(getClass().getResource("/Student/StudentFXML.fxml"));
			Parent studentroot = studentLoader.load();
			Scene scene =new Scene (studentroot);
			StudentController controller = studentLoader.getController();
			controller.initData(sent);
			Stage studentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			studentStage.setScene(scene);
			studentStage.setTitle("Student Dashboard");
			studentStage.setResizable(false);
			studentStage.setMaximized(false);
			studentStage.show();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public void adminlogin(ActionEvent actionEvent){
		try{
			//Stage adminStage =new Stage ();
			FXMLLoader adminLoader =new FXMLLoader();
			adminLoader.setLocation(getClass().getResource("/Admin/Admin.fxml"));
			Parent adminroot =adminLoader.load();
			Scene scene=new Scene(adminroot);
			AdminController admincontroller =adminLoader.getController();
			admincontroller.determiningWho(actionEvent,fullname,divs);
			Stage adminStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
			adminStage.setScene(scene);
			adminStage.setTitle("Admin Dashboard");
			adminStage.setResizable(false);
			adminStage.setMaximized(false);
			adminStage.show();

		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public void dataPass(String userName,String passWord){
		try	{
			String sql ="SELECT * FROM students WHERE username=? AND password=?";
			pr=this.connection.prepareStatement(sql);
			pr.setString(1,userName);
			pr.setString(2,passWord);
			rs=pr.executeQuery();
			while(rs.next()){
				for (int i=2;i<=16;i++){
					sent[this.i] =rs.getString(i);
					this.i++;
				}
			}
		}catch(SQLException ee){
			ee.printStackTrace();
		}

	}
	public void dataAssign(String usrnm,String psw){
		try{
			String sql ="SELECT * FROM login WHERE username=? AND password=?";
			pr=this.connection.prepareStatement(sql);
			pr.setString(1,usrnm);
			pr.setString(2,psw);
			rs=pr.executeQuery();
			if (rs!=null) {
				while (rs.next()) {
					fullname = rs.getString("fullname");
					divs = rs.getString("division");
				}
			}
		}catch(SQLException ee){
			ee.printStackTrace();
		}

	}

}
