package fx.leyu.people.DB;

import java.sql.*;
import java.util.*;
import fx.leyu.tools.UnionFind;

public class Select {
	Connection con;
	
	public Select(Connection con){
		this.con = con;
	}
	
	public void getTempYear(UnionFind f , int year ){
		String table = "temp_"+year;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select AuthorIds from "+table);
			int index = 1;
			while(rs.next()){
				System.out.println("运行速度太慢，加点输出 : 年份 "+year+ ", 第 "+ index +"条................");
				index++;
				String temp = rs.getString(1);
				temp = temp.replaceAll(",Abs", "");
				if(temp.contains(",")){
					String[] nums = temp.split(",");
					ArrayList<Integer> numList = new ArrayList<Integer>();
					for(String str : nums){
						try{
							int num = Integer.valueOf(str);
							if(num > 0 && !numList.contains(num)){
								numList.add(num);
							}
						}catch(Exception e){
							System.out.println("作者ID错误！！");
						}
					}
					if(numList.size()>1){
						int[] results = new int[numList.size()];
						int i=0;
						for(int r : numList){
							results[i] = r;
							i++;
						}
						int min = getMin(results);
						f.add(min, min);   //预防在把root改变
						for(int value : results){
							f.add(value, min);
						}
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(" 获取数据叠加错误！");
			e.printStackTrace();
		}
		
	}

	private int getMin(int[] nums) {
		int min = nums[0];
		for(int num : nums){
			if(num < min){
				min = num;
			}
		}
		return min;
	}
}