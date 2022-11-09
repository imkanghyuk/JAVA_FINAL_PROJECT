package com.example.demo.seller.security;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.savedrequest.*;
import org.springframework.stereotype.*;

@Component
public class SellerSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private RequestCache cache = new HttpSessionRequestCache();

	private RedirectStrategy rs = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
			
		if(request.getParameter("sPassword").length()>=15) {
			HttpSession session = request.getSession();
			session.setAttribute("msg", "임시비밀번호 로그인");
			
			
			rs.sendRedirect(request, response, "/seller/seller/home");
			
		} else {
			
			SavedRequest req = cache.getRequest(request, response);
			
			if(req!=null) {
				rs.sendRedirect(request, response, req.getRedirectUrl());
			} else {
				
				rs.sendRedirect(request, response, "/seller/seller/home");
			}
		}
	}
}
