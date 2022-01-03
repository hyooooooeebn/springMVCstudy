/*===================================================
   EmployeeInsertController.java
   - 사용자 정의 컨트롤러
   - 직원 데이터 입력 액션 수행 (DAO 필요)
   - 이후 employeelist.action 다시 요청하도록 안내
   - DAO 객체에 대한 의존성 주입(DI)을 위한 준비
     → 인터페이스 형태의 자료형을 속성으로 구성
     → setter 메소드 구성
===================================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EmployeeInsertController implements Controller
{
	private IEmployeeDAO dao;
	
	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		// 이전 페이지로부터 넘어온 데이터 수신
		String name = request.getParameter("name");
		String ssn1 = request.getParameter("ssn1");
		String ssn2 = request.getParameter("ssn2");
		String birthday = request.getParameter("birthday");
		int lunar = Integer.parseInt(request.getParameter("lunar"));
		String telephone = request.getParameter("telephone");
		String departmentId = request.getParameter("departmentId");
		String positionId = request.getParameter("positionId");
		String regionId = request.getParameter("regionId");
		int basicPay = Integer.parseInt(request.getParameter("basicPay"));
		int extraPay = Integer.parseInt(request.getParameter("extraPay"));
		
		try
		{
			Employee employee = new Employee();
			
			employee.setName(name);
			employee.setSsn1(ssn1);
			employee.setSsn2(ssn2);
			employee.setBirthday(birthday);
			employee.setLunar(lunar);
			employee.setTelephone(telephone);
			employee.setDepartmentId(departmentId);
			employee.setPositionId(positionId);
			employee.setRegionId(regionId);
			employee.setBasicPay(basicPay);
			employee.setExtraPay(extraPay);
			
			dao.employeeAdd(employee);
			
			mav.setViewName("redirect:employeelist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}

}
