/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trungndd.blos.TblOrderBLO;
import trungndd.entities.Account;
import trungndd.entities.Cake;
import trungndd.entities.OrderDetails;
import trungndd.entities.ShoppingCart;
import trungndd.entities.TblOrder;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SaveCartController", urlPatterns = {"/SaveCartController"})
public class SaveCartController extends HttpServlet {

    private static final String INDEX = "indexPage";
    private static final String CART_PAGE = "viewCartPage";

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
            ShoppingCart cart = (ShoppingCart) session.getAttribute("CART");
            
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String payment = request.getParameter("payment");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String idOrder = "Order" + sdf.format(new Date(System.currentTimeMillis()));
            
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            String orderDate = sdf.format(System.currentTimeMillis());
            
            TblOrder order = new TblOrder(fullname, phone, address, payment, cart.getTotal(), account);
            order.setIdOrder(idOrder);
            order.setPaymentStatus("not yet");
            order.setOrderStatus("pending");
            order.setOrderDate(orderDate);
            
            TblOrderBLO blo = new TblOrderBLO();
            
            for (Cake cake : cart.values()) {
                OrderDetails details = new OrderDetails(idOrder, cake.getIdCake(), cake, cake.getQuantity(), cake.getPrice());
                order.addOrderDetails(details);
            }
            
            blo.saveOrder(order);
            
            request.setAttribute("MESSAGE", "Your cart has been saved to databased (" + idOrder + ")");
            url = myProperties.getMyProperty(CART_PAGE);
            // after save remove cart from session
            session.removeAttribute("CART");
        } catch (Exception e) {
            log("ERROR at SaveCartController: " + e.getMessage());
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
