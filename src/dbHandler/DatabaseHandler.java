package dbHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public abstract class DatabaseHandler {
	protected Connection conn;
	protected ResultSet results;
	protected PreparedStatement ps;
	StringWriter sw = new StringWriter();
	PrintWriter out = new PrintWriter(sw);
	
	//Connecting to Database
	public DatabaseHandler() { /*String username, String password, String deptId*/
	//Load Driver
		String driver = "oracle.jdbc.OracleDriver";
	//Define connection url, username and pass
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String orclUser = "cis4160";
		String orclPass = "cis4160";
	//Establish connection
		try {
			Class.forName(driver).newInstance();
			this.conn = DriverManager.getConnection(url, orclUser, orclPass);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			out.println("Error Connecting to Database");;
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	//Method that creates statement and execute query
		public void executeQuery(String query) {
			
		}
		
	//Method that returns result set
		public ArrayList<String> getResultList(ResultSet rs){
			ArrayList<String> rsList = new ArrayList<>(); 
			String result = null;
		
			try {
				while(rs.next()){
					result = rs.getString(1);
					rsList.add(result);
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rsList;
		}
		
	//Method closes connection
		public void closeConnection() {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
