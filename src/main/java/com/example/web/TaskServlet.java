package com.example.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.TaskService;
import com.example.service.TaskServiceImpl;


public class TaskServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
			String name = request.getParameter("name");
			String date = request.getParameter("date");
			String description = request.getParameter("description");
			String personName = request.getParameter("personName");
			//TODO : Change with Spring DI
			TaskService taskService = new TaskServiceImpl();
			taskService.saveTask(name, description, date, personName);
			
			RequestDispatcher view = request.getRequestDispatcher("success.jsp");
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		try {
			//TODO : Change with Spring DI
			TaskService taskService = new TaskServiceImpl();
			List tasks = taskService.getAllTasks();
			request.setAttribute("tasks", tasks);
			RequestDispatcher view = request.getRequestDispatcher("viewTasks.jsp");
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
