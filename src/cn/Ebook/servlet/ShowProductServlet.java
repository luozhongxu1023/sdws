package cn.Ebook.servlet;

import java.awt.TextField;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.Ebook.dao.ProductDao;



/**
 * Servlet implementation class ShowProductServlet
 */
@WebServlet("/showproductservlet")
public class ShowProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		// 获取客户订单请求类别
		
		ProductDao dao = new ProductDao();
		String selectAction = "all";
		String _selectAction = request.getParameter("selectAction");
		if (_selectAction !="") {
			selectAction = _selectAction;
		}
		String searchfield = request.getParameter("textfield");
		System.out.println("searchfield:"+searchfield);
		System.out.println("selectAction:"+selectAction);
		System.out.println("textfield:"+ request.getParameter("textfield"));

		try {
				List list = new ArrayList();//存放商品列表
				String i = request.getParameter("i");// i用于存放当前显示页号
				if (i != null)
					request.setAttribute("i", i);
				if(selectAction.equals("all"))  list=dao.selectAllProducts();//将查询结果存入List对象productlist */
				else if(selectAction.isEmpty())  { 
					System.out.println("1");
					list=dao.SearchProductByName(searchfield);}
				else  list=dao.selectProductsByCategory(selectAction);
				request.setAttribute("list", list);//将订单存至request
				//服务器端请求转发，跳转至前台订单显示页面，request不发生变化
				request.getRequestDispatcher("product/product_list.jsp").forward(request, response);
			out.flush();
			out.close();
		} catch (Exception e) {// 没有登录用户，退至主页面
			out.println("<script>parent.location.href='/Ebook/index.jsp';</script>");
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
