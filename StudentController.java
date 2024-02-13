package com.wini.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wini.dao.DataAccess;
import com.wini.model.Student;

@Controller
public class StudentController 
{
	@RequestMapping(value="CRUD", params="Register")
	public ModelAndView gotoRegisterPage()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register.jsp");
		return mv;
	}
	
	@RequestMapping(value="CRUD", params="Show")
	public ModelAndView showAllStudents()
	{
		ModelAndView mv = new ModelAndView();
		
		DataAccess da = new DataAccess();
		ArrayList<Student> al = da.getAllStudents();
		mv.addObject("list",al);
		
		mv.setViewName("display.jsp");
		
		return mv;
	}
	
	@RequestMapping("reg")
	public ModelAndView insertStudent(HttpServletRequest request)
	{
		String n = request.getParameter("name");
		String e = request.getParameter("email");
		String p = request.getParameter("password");
		long m = Long.parseLong(request.getParameter("mobile"));
		
		Student s = new Student(n, e, p, m);
		
		DataAccess.insert(s);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.jsp");
		mv.addObject("res", "inserted");
		return mv;
		
	}
	
	@RequestMapping("edit")
	public ModelAndView displayEditForm(int id)
	{
		ModelAndView mv = new ModelAndView();
		
		DataAccess da = new DataAccess();
		Student std = da.getStudentByID(id);
		mv.addObject("student", std);
		mv.setViewName("register.jsp");
		
		return mv;
	}
	
	@RequestMapping("update")
	public ModelAndView updateStudent(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		int i = Integer.parseInt(request.getParameter("id"));
		String n = request.getParameter("name");
		String e = request.getParameter("email");
		String p = request.getParameter("password");
		long m = Long.parseLong(request.getParameter("mobile"));
		Student s = new Student(i,n, e, p, m);
		
		DataAccess d = new DataAccess();
		d.update(s);
		
		ArrayList<Student> al = d.getAllStudents();
		mv.addObject("list",al);
		
		mv.setViewName("display.jsp");
		return mv;
		
		
	}
	
	@RequestMapping("delete")
	public ModelAndView deleteStudent(int id)
	{
		ModelAndView mv = new ModelAndView();
		
		
		DataAccess d = new DataAccess();
		d.delete(id);
		
		ArrayList<Student> al = d.getAllStudents();
		mv.addObject("list",al);
		
		mv.setViewName("display.jsp");
		return mv;
		
		
	}
	
	
}
