package com.suntf.pkmserver.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.suntf.pkmserver.service.ShDairyService;

public class ShareServlet extends HttpServlet {

	private ShDairyService shdairyservice = new ShDairyService();
	
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
		
		if(req.getParameter("count")!=null&&req.getParameter("etime")!=null)
		{
			int count = Integer.valueOf(req.getParameter("count"));
			long etime = Long.valueOf(req.getParameter("etime"));
				
			JSONArray shja = shdairyservice.getShDairyCT(count, etime);
			resp.getWriter().println(shja.toString());
		}
		else
		{
			resp.getWriter().println("error");
		}
	}

}
