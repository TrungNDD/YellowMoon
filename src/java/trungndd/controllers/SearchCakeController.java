/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trungndd.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungndd.blos.CakeBLO;
import trungndd.entities.Account;
import trungndd.entities.Cake;
import trungndd.entities.Category;

/**
 *
 * @author Admin
 */
@WebServlet(name="SearchCakeController", urlPatterns={"/SearchCakeController"})
public class SearchCakeController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("ACCOUNT");
        String role = "guest";

        if (account != null) {
            role = account.getRole();
        }
               
        try {
            String searchBy = request.getParameter("searchBy");
            
            CakeBLO blo = new CakeBLO();
            List cakes = null;
            
            switch(searchBy){
                case("name"):
                    String txtSearch = request.getParameter("txtSearch");
                    cakes = blo.getAllCakesByName(txtSearch, role);
                    break;
                    
                case("price"):
                    double from = Double.parseDouble(request.getParameter("from"));
                    double to = Double.parseDouble(request.getParameter("to"));
                    cakes = blo.getAllCakesByPrice(from, to, role);
                    break;
                    
                case ("category"):
                    String idCategory = request.getParameter("idCategory");
                    cakes = blo.getAllCakesByCategory(idCategory, role);
                    break;
            }
            
            // remember to set this orderDetailsCollection null
            for (Object cake : cakes) {
                ((Cake) cake).setOrderDetailsCollection(null);
            }

            // register GsonBuilder
            GsonBuilder builder = new GsonBuilder();
            builder = builder.registerTypeAdapter(Category.class, new Category());

            Gson gson = builder.create();
            String json = gson.toJson(cakes);
            
            out.write(json);
        } catch (Exception e) {
            log("ERROR at SearchCakeController: " + e.getMessage());
        } finally {
            //request.getRequestDispatcher(url).forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
