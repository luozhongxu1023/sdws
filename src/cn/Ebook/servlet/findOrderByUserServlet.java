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
import javax.servlet.http.HttpSession;

import cn.Ebook.dao.OrderDao;
import cn.Ebook.dao.OrderItemDao;
import cn.Ebook.entity.Order;



/**
 * Servlet implementation class findOrderByUserServlet
 */
@WebServlet("/findOrderByUser")
public class findOrderByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findOrderByUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//根据用户查询订单
		// TODO Auto-generated method stub
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				HttpSession session = request.getSession(true);
				// 获取客户订单请求类别
				String orderAction = request.getParameter("orderAction").toString();
				OrderDao order = new OrderDao();
				String curUserName=null;
				try{
					curUserName = session.getAttribute("name").toString();
				}catch(Exception e){
					e.printStackTrace();
				}
				try {
					// 1.若orderAction中存放的是orderList，表示查看当前用户所有订单信息
					if (orderAction.equals("orderlist")) {//表示查看会员订单请求
						List list = new ArrayList();//存放订单信息链表
						String i = request.getParameter("i");// i用于存放当前订单显示页号
						if (i != null)
							request.setAttribute("i", i);
						// 取得当前在线用户的订单
						if(curUserName!=null)
							list = order.selectOrderByName(curUserName);//生成订单集合
						request.setAttribute("orderlist", list);//将订单存至request
						//服务器端请求转发，跳转至前台订单显示页面，request不发生变化
						request.getRequestDispatcher("client/myAccount.jsp")
								.forward(request, response);
					}
					//均不满足于，则显示主页面
					else {
						// 没有对应的action，显示主页面
						out.println("<script>parent.location.href='/Ebook/index.jsp';</script>");
					}
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
