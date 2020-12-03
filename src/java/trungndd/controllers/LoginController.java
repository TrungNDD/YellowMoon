/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungndd.blos.AccountBLO;
import trungndd.entities.Account;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    private static final String LOGIN_PAGE = "loginPage";
    private static final String INDEX = "indexPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        MyProperties myProperties = new MyProperties("member");
        String url = myProperties.getMyProperty(LOGIN_PAGE);
        Account account = null;
        
        try {
            String action = request.getParameter("action") + "";
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            AccountBLO blo = new AccountBLO();
            if (action.matches("google")) {
                String name = request.getParameter("name");
                account = blo.checkLoginAsGoogle(email, password, name);
            } else {
                password = request.getParameter("password");
                account = blo.checkLogin(email, password);
            }

            System.out.println(account);
            session.setAttribute("ACCOUNT", account);
            url = myProperties.getMyProperty(INDEX);

            // cancel the cart if admin is login
            if (account.getRole().matches("admin")) {
                session.removeAttribute("CART");
            }

            // return the showroom page imediately
            response.sendRedirect(INDEX);
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());

            if (e instanceof NoResultException) {
                request.setAttribute("MESSAGE", "Username or Password is invalid");
            } else {
                request.setAttribute("MESSAGE", "Login failed, try again later");
            }
            request.setAttribute("ACCOUNT", account);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
