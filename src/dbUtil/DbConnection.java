
package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "rockerbinod");

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();

		}
		return null;
	}

}
