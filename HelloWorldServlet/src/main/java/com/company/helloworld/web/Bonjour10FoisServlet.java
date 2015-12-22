package com.company.helloworld.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Bonjour10FoisServlet
 */
public class Bonjour10FoisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int compteur = 0; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bonjour10FoisServlet() {
        super();
    }
    
    

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.compteur = Integer.parseInt(config.getInitParameter("compteur"));
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		for (int i=0; i<compteur; i++) {
			response.getWriter().append("Bonjour<br>");
		}
		response.getWriter().append("<br>")
							.append("<a href='index.jsp'>Retour au menu</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
