package com.bookStore.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;


import com.bookStore.commons.beans.User;

public class loginTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		PageContext context = (PageContext) this.getJspContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getResponse();
		
		
		User login_user = (User) context.getSession().getAttribute("login_user");
		if(login_user == null){
			response.sendRedirect(request.getContextPath()+"/client/error/privilege.jsp");
		}
	}
	
}
