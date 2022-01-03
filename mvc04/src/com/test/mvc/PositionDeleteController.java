package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PositionDeleteController implements Controller
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
		else if (session.getAttribute("admin")==null)		
		{
			mav.setViewName("redirect:logout.action");
			return mav;
		}
		
		String positionId = request.getParameter("positionId");

		try
		{
			if(dao.delCheck(positionId) == 0)
				dao.remove(positionId);
			
			mav.setViewName("redirect:positionlist.action");
			
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}

}
