package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PositionUpdateController implements Controller
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
		
		Position position = new Position();

		try
		{
			position.setPositionId(request.getParameter("positionId"));
			position.setPositionName(request.getParameter("positionName"));
			position.setMinBasicPay(Integer.parseInt(request.getParameter("minBasicPay")));
			
			
			dao.modify(position);
			
			mav.setViewName("redirect:positionlist.action");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;

	}

}
