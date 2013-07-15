package com.suntf.pkmserver.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.suntf.pkmserver.model.User;
import com.suntf.pkmserver.service.UserService;


public class LoginServlet extends HttpServlet {

	private UserService use = new UserService(); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=UTF-8");  
		int i = 0;
		
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		
		name = name == null ? null : name.trim();
		pwd = pwd == null ? null : pwd.trim();
		if(pwd!=null&&pwd!=""&&name!=null&&name!="")
			i = use.check_login(name, pwd);
		
		if(i>0)
		{
			HttpSession session = req.getSession();
			session.setAttribute("name", name);
			session.setAttribute("pwd", pwd);
			session.setAttribute("id", Integer.valueOf(i));
			try{
				User user = use.getUInfo(name);
				JSONObject jo = new JSONObject()
					.put("status", "success")
					.put("name", name)
					.put("password", user.getPassword())
					.put("dairy_count",user.getDairy_count())
					.put("sh_count", user.getSh_count());
				resp.getWriter().println(jo.toString());
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else
		{
				try {
					JSONObject jo = new JSONObject()
						.put("status", "failed");
					resp.getWriter().println(jo.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
		}
	}

}
