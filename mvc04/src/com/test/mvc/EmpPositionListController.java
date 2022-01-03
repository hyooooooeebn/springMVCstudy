package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EmpPositionListController implements Controller
{
	private IPositionDAO dao;

	public void setDao(IPositionDAO dao)
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

		ArrayList<Position> positionList = new ArrayList<Position>();
		
		try
		{
			positionList = dao.list();
			mav.addObject("positionList", positionList);
			mav.setViewName("/WEB-INF/view/EmpPositionList.jsp");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;

	}
}
