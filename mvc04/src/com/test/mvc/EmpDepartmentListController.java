package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EmpDepartmentListController implements Controller
{
	private IDepartmentDAO dao;
	
	public void setDao(IDepartmentDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		if (session.getAttribute("name")==null)		
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
		}
		
		ArrayList<Department> departmentList = new ArrayList<Department>();
		
		try
		{
			departmentList = dao.list();
			
			mav.addObject("departmentList", departmentList);
			mav.setViewName("/WEB-INF/view/EmpDepartmentList.jsp");
			
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;

	}
}
