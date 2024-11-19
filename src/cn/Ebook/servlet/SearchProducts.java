package cn.Ebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.Ebook.dao.ProductDao;

/**
 * Servlet implementation class SearchProducts
 */
@WebServlet("/searchproducts")
public class SearchProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取搜索框的内容
		response.setContentType("text/html;charset=utf-8");
		String searchfield = request.getParameter("textfield");

		// 根据搜索框的内容进行查询
		ProductDao dao = new ProductDao();
		List list = dao.SearchProductByName(searchfield);

		// 将查询结果存入request对象中
		request.setAttribute("list", list);

		// 使用服务器端请求转发跳转到前台订单显示页面
		request.getRequestDispatcher("product/product_list.jsp").forward(request, response);

}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
