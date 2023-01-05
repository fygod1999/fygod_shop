package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/index/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
			request.setAttribute("msg", "请登录后再提交订单！");
			request.getRequestDispatcher("/index/login.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/index/order.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
