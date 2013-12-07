package fx.leyu.people.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConDB {

	static Connection con = null;
	public ConDB(){
	}
	
	public static Connection getConnection(){
		if(con==null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/network","fxleyu","fxyuer");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {	
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public static void close(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println("mysql");
		@SuppressWarnings("resource")
		int year = new Scanner(System.in).nextInt();
		Connection conn = ConDB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rc = st.executeQuery("select * from publication where Pubyear = "+year);
		while(rc.next()){
			System.out.println(rc.getString("Title")+" : "+rc.getString("Authors"));
		}
	}
}
