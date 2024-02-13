package com.wini.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.wini.model.Student;

public class DataAccess 

{
	public void delete(int id)
	{
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "admin");
			String qry = "delete from student where sno=?";
			ps=cn.prepareStatement(qry);
			
			ps.setInt(1, id);

			
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update(Student s)
	{
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "admin");
			String qry = "update student set ename=?, email=?, pw=?, mobile=? where sno=?";
			ps=cn.prepareStatement(qry);
			
			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setString(3, s.getPassword());
			ps.setLong(4, s.getMobile());
			ps.setInt(5, s.getId());

			
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Student getStudentByID(int id)
	{
		Student s = new Student();
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "admin");
			String qry = "select * from student where sno=?";
			ps=cn.prepareStatement(qry);
			
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				s.setId(id);
				s.setName(rs.getString("ename"));
				s.setEmail(rs.getString("email"));
				s.setPassword(rs.getString("password"));
				s.setMobile(rs.getLong("mobile"));
				
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}
	public static void insert(Student s)
	{
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "admin");
			String qry = "insert into student(ename,email,pw,mobile) values(?,?,?,?)";
			ps=cn.prepareStatement(qry);
			
			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setString(3, s.getPassword());
			ps.setLong(4, s.getMobile());

			
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Student> getAllStudents()
	{
		ArrayList<Student> al = new ArrayList<Student>();
		
		Connection cn = null;
		
		Statement stm = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring", "root", "admin");
			
			stm = cn.createStatement();
			ResultSet rs = stm.executeQuery("select * from student");
			
			while(rs.next())
			{	
				int i = rs.getInt("sno");
				String n = rs.getString("ename");
				String e = rs.getString("email");
				String p = rs.getString("pw");
				long m = rs.getLong("mobile");
				
				Student s = new Student(i,n,e,p,m);
				al.add(s);
				
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return al;
		
	}
}
