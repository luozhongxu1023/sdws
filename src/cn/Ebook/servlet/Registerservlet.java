package cn.Ebook.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;


import cn.Ebook.dao.UserDao;
import cn.Ebook.entity.User;

/**

 * Servlet implementation class Register
   */
   @WebServlet("/registerservlet")
   public class Registerservlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**

    * @see HttpServlet#HttpServlet()
      */
      public Registerservlet() {
      super();
      // TODO Auto-generated constructor stub
      }

   /**

    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
      */
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //完成用户注册，并跳转注册成功页面
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      HttpSession session = request.getSession(true);
      UserDao dao =new UserDao();
      User user =new User();
      String username=request.getParameter("username");
      String password=request.getParameter("password");
      user.setUsername(username);
      user.setPASSWORD(password);
      user.setEmail(request.getParameter("email"));
      user.setTelephone(request.getParameter("telephone"));
      //添加用户信息
      	User formSelect=dao.selectUser(user.getUsername());
      	if (formSelect==null || formSelect.equals("")){
      		if (dao.addUser(user)){
      			request.setAttribute("registersuccess","注册成功!!!");
      			request.getRequestDispatcher("/client/registersuccess.jsp").forward(request, response);
      			session.setAttribute("user", user);
      			session.setAttribute("name", user.getUsername());
      			//response.sendRedirect(request.getContextPath() + "/client/registersuccess.jsp");
      			return;
      			}else{
      			request.setAttribute("registersuccess", "数据库操作失败，注册不成功！请重新注册！");
      			//request.getRequestDispatcher("/client/registersuccess.jsp").forward(request, response);
      			}
      		}else{
      			request.setAttribute("registersuccess", "该用户名称已经存在！！！！！！");
      			//request.getRequestDispatcher("/client/registersuccess.jsp").forward(request, response);
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

