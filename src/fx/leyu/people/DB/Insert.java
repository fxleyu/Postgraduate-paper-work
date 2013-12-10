package fx.leyu.people.DB;

import java.sql.*;

import fx.leyu.people.data.bean.*;

public class Insert {
	
	Connection con;
	
	public Insert(Connection con){
		this.con = con;
	}
	
	public void people(People p){
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from people where Id ="+p.id);
			if(rs.next()){
				//System.out.println(" ?????????"+ rs.getString(1)+" #####################");
				return;
			}
			con.setAutoCommit(false);
			String sql = "insert into people  values (?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, p.id);
			ps.setString(2, p.name);
			ps.setString(3, p.alias);
			ps.setString(4, p.phone);
			ps.setString(5, p.fax);
			ps.setString(6, p.email);
			ps.setString(7, p.homepage);
			ps.setString(8, p.position);
			ps.setInt(9, p.hindex);
			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
	}
	
	public void publication(Publication p){ 
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from publication where Id ="+p.id);
			if(rs.next()){
				//System.out.println(" herer #####################");
				return;
			}
			con.setAutoCommit(false);
			String sql = "insert into publication  values (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, p.id);
			ps.setString(2, p.title);
			ps.setInt(3, p.startpage);
			ps.setInt(4, p.endpage);
			ps.setInt(5, p.pubyear);
			ps.setInt(6, p.pages);
			ps.setString(7, p.authors);
			ps.setInt(8, p.citedby);
			ps.setString(9, p.pubkey);
			ps.setString(10, p.jconfname);
			ps.setString(11, p.authorids);
			ps.setString(12, p.abs);
			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
	}
	
	public void tempPublication(Publication p, int year){ 
		try {
			con.setAutoCommit(false);
			String sql = "insert into temp_"+ year +" values (?,?,?,?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, p.id);
			ps.setString(2, p.title);
			ps.setInt(3, p.startpage);
			ps.setInt(4, p.endpage);
			ps.setInt(5, p.pubyear);
			ps.setInt(6, p.pages);
			ps.setString(7, p.authors);
			ps.setInt(8, p.citedby);
			ps.setString(9, p.pubkey);
			ps.setString(10, p.jconfname);
			ps.setString(11, p.authorids);
			ps.setString(12, p.abs);
			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
	}
}
