/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungndd.blos.CakeBLO;
import trungndd.entities.Account;
import trungndd.entities.Cake;
import trungndd.entities.ShoppingCart;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShoppingCartController", urlPatterns = {"/ShoppingCartController"})
public class ShoppingCartController extends HttpServlet {

    private static final String VIEW_CART_PAGE = "viewCartPage";
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
        Account account = (Account) session.getAttribute("ACCOUNT");

        String role = "guest";
        if (account != null) {
            role = account.getRole();
        } else {
            account = new Account(role, role);
        }

        MyProperties myProperties = new MyProperties(role);

        String url = myProperties.getMyProperty(INDEX);

        try {
            String action = request.getParameter("action");
            String idCake = request.getParameter("idCake");

            CakeBLO blo = new CakeBLO();
            Cake cake = blo.getCakeById(idCake);

            ShoppingCart cart = (ShoppingCart) session.getAttribute("CART");

            // in case this is the first time user use cart
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("CART", cart);
            }

            switch (action) {
                case "add":
                    if (!cart.add(cake)){
                        request.setAttribute("MESSAGE", 
                                "The cake " + cake.getIdCake() + " has only " + cake.getQuantity() + " remained");
                    }
                    
                    url = myProperties.getMyProperty(INDEX);
                    break;

                case "Update":
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    
                    if(!cart.update(cake, quantity)){
                        request.setAttribute("MESSAGE", 
                                "The cake " + cake.getIdCake() + " has only " + cake.getQuantity() + " remained");
                    }
                    url = myProperties.getMyProperty(VIEW_CART_PAGE);
                    break;

                case "remove":
                    cart.remove(cake.getIdCake());
                    url = myProperties.getMyProperty(VIEW_CART_PAGE);
                    break;
            }
        } catch (Exception e) {
            log("ERROR at ShoppingCartController: " + e.getMessage());
        } finally {
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
