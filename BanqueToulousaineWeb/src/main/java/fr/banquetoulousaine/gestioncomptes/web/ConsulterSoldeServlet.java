package fr.banquetoulousaine.gestioncomptes.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.banquetoulousaine.gestioncomptes.exceptions.CompteIntrouvableException;
import fr.banquetoulousaine.gestioncomptes.exceptions.ProblemeTechniqueException;
import fr.banquetoulousaine.gestioncomptes.service.ICompteService;
import fr.banquetoulousaine.gestioncomptes.service.impl.CompteServiceImpl;

/**
 * Servlet implementation class ConsulterSoldeServlet
 */
public class ConsulterSoldeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ICompteService compteService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsulterSoldeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String numeroCompte = request.getParameter("numerocompte");
			int solde = compteService.consulterSolde(numeroCompte);
			response.getWriter().append("Solde du compte numéro: ")
								.append(numeroCompte)
								.append(" est: ")
								.append(String.valueOf(solde));
		} catch(ProblemeTechniqueException e){
			response.getWriter().append("Problème interne");
		} catch (CompteIntrouvableException e) {
			response.getWriter().append("Compte introuvable.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		compteService = new CompteServiceImpl();
	}
	

}
