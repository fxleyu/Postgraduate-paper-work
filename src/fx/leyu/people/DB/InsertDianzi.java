package fx.leyu.people.DB;

import java.sql.*;
import java.util.*;

import cn.csu.dianzi.bean.Edge;

public class InsertDianzi {
	
	Connection con = null;
	
	public InsertDianzi(Connection con){
		this.con = con;
	}
	
	public void dataStructrue(ArrayList<String[]> data){
		try {
			con.setAutoCommit(false);
			String sql = "insert into data values (?, ?, ?, ?)";
			int index = 0;
			for(String[] d : data){
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, ++index);
				pst.setString(2, d[0].trim());
				pst.setInt(3, Integer.valueOf(d[1].trim()));
				pst.setInt(4, getClasses(Integer.valueOf(d[1].trim())));
				pst.executeUpdate();
				con.commit();
				System.out.println(index+"。。。。。。。。。。。。。"+ d[1]);
			}
		} catch (SQLException e) {
			System.out.println("数据库操作失败");
			e.printStackTrace();
		}
		
	}
	
	public void author(ArrayList<String[]> data){
		try {
			con.setAutoCommit(false);
			String sql = "insert into author values (?, ?)";
			for(String[] d : data){
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, Integer.valueOf(d[1]));
				pst.setString(2, d[0].trim());
				pst.executeUpdate();
				con.commit();
			}
		} catch (SQLException e) {
			System.out.println("数据库操作失败");
			e.printStackTrace();
		}
	}
	
	private int getClasses(int year) {
		int result = -1;
		if(year > 1992){
			result = year - 1993;
		}
		return result;
	}

	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void contents(ArrayList<String[]> data) {
		try{
			con.setAutoCommit(false);
			String sql = "insert into contents values (?, ?, ?, ?)";
			int index = 0;
			for(String[] d : data){
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, ++index);
				pst.setString(2, d[0]);
				pst.setString(3, d[1]);
				pst.setInt(4, Integer.valueOf(d[2]));
				pst.executeUpdate();
				con.commit();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public void datas(ArrayList<String[]> data) {
		try{
			String sql = "insert into datas values (?, ?, ?)";
			con.setAutoCommit(false);
			int index = 0;
			for(String[] d : data){
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, d[0].trim());
				pst.setString(2, d[1].trim());
				pst.setInt(3, Integer.valueOf(d[2].trim()));
				pst.executeUpdate();
				con.commit();
				System.out.println(++index);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void edge(ArrayList<Edge> edge) {
		try{
			Statement st = con.createStatement();
			int index = 0;
			for(Edge e : edge){
				String sql = "insert into edge(Source, Target, Weight, classes) values ( " 
							+ e.source +", " + e.target + ", " + e.weight + ", " + e.classes+ ")";
				st.executeUpdate(sql);
				System.out.println("这是类别为"+e.classes+"的插入数目为："+(++index));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
