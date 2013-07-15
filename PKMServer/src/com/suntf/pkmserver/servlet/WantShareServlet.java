package com.suntf.pkmserver.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suntf.pkmserver.model.ShDairy;
import com.suntf.pkmserver.service.ShDairyService;

public class WantShareServlet extends HttpServlet {

	private ShDairyService shdairyservice;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=UTF-8");  
		
		HttpSession session = req.getSession();
		String title = URLDecoder.decode((String)req.getParameter("title"),"utf-8");
		String content = URLDecoder.decode(req.getParameter("content"),"utf-8");
		System.out.println(req.getParameter("title")+"    "+req.getParameter("content"));
		if(session.getAttribute("name")==null||session.getAttribute("name")=="")
		{
			resp.getWriter().println("error");
			return;
		}
		if(title==""||content==""||title==null||content=="")
		{
			resp.getWriter().println("failed");
			return;
		}
		String name = (String)session.getAttribute("name");
		ShDairy sd = new ShDairy(0,name,title,content,makeTime(),new Date().getTime(),0);
		shdairyservice = new ShDairyService();
		int i = shdairyservice.addShDairy(sd);
		if(i==1)
		{
			resp.getWriter().println("success");
		}else
		{
			resp.getWriter().println("failed");
		}
	}
	
	public String makeTime()
	{
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);//年
		int month = calendar.get(Calendar.MONTH)+1;//月
		int day = calendar.get(Calendar.DAY_OF_MONTH);//日
		int hour = calendar.get(Calendar.HOUR_OF_DAY);//时
		int minute = calendar.get(Calendar.MINUTE);//分
		return ""+year+"-"+month+"-"+day+" / "+hour+":"+minute;
	}
	
}
