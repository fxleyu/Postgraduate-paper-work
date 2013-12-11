package fx.leyu.people.DB;

import java.sql.*;
import java.util.*;

import cn.csu.dianzi.bean.*;

public class SelectDianzi {
	private Connection con = null;
	
	public SelectDianzi(Connection con){
		this.con = con;
	}
	
	public ArrayList<Data> data(int classes){
		ArrayList<Data> result = new ArrayList<Data>();
		String sql = "select authors, year, classes from data where classes = " + classes;
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int index = 0;
			while(rs.next()){
				System.out.println("预测会很慢，加点标记语句。处理数目为：" + (++index));
				Data d = new Data();
				d.authors = rs.getString(1);
				d.year = rs.getInt(2);
				d.classes = rs.getInt(3);
				result.add(d);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
}
