package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DepartmentUpdateFormController implements Controller
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
		else if (session.getAttribute("admin")==null)	
		{
			mav.setViewName("redirect:logout.action");
			return mav;
		}
		
		String departmentId = request.getParameter("departmentId");
		Department department = new Department();
		
		try
		{
			department = dao.searchId(departmentId);
			
			mav.addObject("department", department);
			mav.setViewName("/WEB-INF/view/DepartmentUpdateForm.jsp");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		return mav;
	}

}
