/*==========================================
 * MemberInsertController.java
 * - 사용자 정의 컨트롤러
 * - 회원 데이터 추가 액션 처리 클래스
 * - DAO 객체에 대한 의존성 주입 준비
 *   → setter 메소드 추가
============================================ */

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 『implements Controller』 혹은 『extends AbstractController』
//--서블릿에서 HttpServlet을 상속받은 서블릿 객체 역할
public class MemberInsertController implements Controller
{
	// dao 관련 속성 구성 → 인터페이스 형태
	private IMemberDAO dao;
		
	// setter 메소드 구성
	public void setDao(IMemberDAO dao)
	{
		this.dao = dao;
	}
		
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		// 이전 페이지로부터 넘어온 데이터 수신
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String telphone = request.getParameter("telphone");
		
		try
		{
			MemberDTO dto = new MemberDTO();
			dto.setName(name);
			dto.setTelphone(telphone);
			
			dao.add(dto);
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		//mav.setViewName("/WEB-INF/view/MemberList.jsp"); → 잘못된 것!! 
		//@redirect 해줘야한다. 그냥 insert 후에 다시 돌아와야하는것
		mav.setViewName("redirect:memberlist.action");
		//@ 다시 컨트롤러를 요청하도록 하는 것
		
		return mav;
	}
	
}
