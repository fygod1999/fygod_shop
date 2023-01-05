package com.lanou.yoyo.web;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanou.yoyo.bean.Cart;
import com.lanou.yoyo.bean.Item;
import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.service.OrderService;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class PayServlet
 */
@WebServlet("/index/pay")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TypeService typeService = new TypeServiceImpl();

	private OrderService orderService = new OrderService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);

		Cart cart = (Cart) request.getSession().getAttribute("order");

//			BeanUtils.copyProperties(cart, request.getParameterMap());
		List<Item> itemList = new ArrayList<>();
		cart.setItemList(itemList);

//		cart.setName("");
//		cart.setPhone("");
//		cart.setAddress("");
//		cart.setPaytype(1);

		cart.setSystime(new Date());
		cart.setStatus(2);
		cart.setUser((User) request.getSession().getAttribute("user"));

		orderService.addOrder(cart);

		request.getSession().removeAttribute("order");

		request.setAttribute("msg", "订单支付成功！");

		request.getRequestDispatcher("/index/payok.jsp").forward(request, response);

	}

}
