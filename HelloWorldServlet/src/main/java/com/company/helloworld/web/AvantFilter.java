package com.company.helloworld.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class AuthentificationFilter
 */
public class AvantFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String nom = request.getParameter("nom");
		if(nom != null && !nom.isEmpty()){
			chain.doFilter(request, response);
		} else {
			//response.getWriter().println("Le nom n'est pas renseigné");
			request.getRequestDispatcher("/pages/ErreurFiltrage_1.html").forward(request, response);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
