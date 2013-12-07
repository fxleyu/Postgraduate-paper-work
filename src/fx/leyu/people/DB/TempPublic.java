package fx.leyu.people.DB;

import fx.leyu.people.data.bean.*;

import java.sql.*;

public class TempPublic {

Connection con;
	
	public TempPublic(Connection con){
		this.con = con;
	}
	
	public void publication(int year){
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from publication where Pubyear = "+year+";");
			while(rs.next()){
				Publication yu = new Publication(rs.getInt(1));
				yu.title = rs.getNString(2);
				yu.startpage = rs.getInt(3);
				yu.endpage = rs.getInt(4);
				yu.pubyear = rs.getInt(5);
				yu.pages = rs.getInt(6);
				yu.authors = rs.getNString(7);
				yu.citedby = rs.getInt(8);
				yu.pubkey = rs.getNString(9);
				yu.jconfname = rs.getNString(10);
				yu.authorids = rs.getNString(11);
				yu.abs = rs.getNString(12);
				new Insert(ConDB.getConnection()).tempPublication(yu, year);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
