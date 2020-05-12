package com.springmvc.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
//默认bean的id为首字母小写对应的类名
@Component
public class MyView implements View{
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return "text/html";
	}
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		response.getWriter().println("<h2>hello world</h2>");
	}

}
