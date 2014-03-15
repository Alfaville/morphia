package br.com.livraria.morphia.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livraria.morphia.controller.LoginController;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
		LoginController login = (LoginController) ( (HttpServletRequest) request ).getSession().getAttribute( "loginController" );
		if ( login == null || !login.isLoggedIn() ) {
			String contexPath = ( (HttpServletRequest) request ).getContextPath();
			( (HttpServletResponse) response ).sendRedirect( contexPath + "/login.xhtml" );
		} else
			chain.doFilter(request, response);
	}

	@Override
	public void init( FilterConfig arg0 ) throws ServletException {

	}

}
