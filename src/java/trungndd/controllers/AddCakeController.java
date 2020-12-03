/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import trungndd.blos.CakeBLO;
import trungndd.entities.Account;
import trungndd.entities.Cake;
import trungndd.entities.Category;
import trungndd.resources.MyProperties;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddCakeController", urlPatterns = {"/AddCakeController"})
public class AddCakeController extends HttpServlet {

    private static final String INDEX = "indexPage";
    private static final String ADD_CAKE_PAGE = "addCakePage";

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
        MyProperties myProperties = new MyProperties(account.getRole());
        String url = myProperties.getMyProperty(ADD_CAKE_PAGE);

        Cake cake = null;

        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (isMultiPart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }

                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String filename = null;

                // prototype for img attribute of this post
                String img = null;

                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) { // get other params
                        params.put(item.getFieldName(), item.getString());
                    } else { // get file
                        try {
                            // get file name
                            String itemName = item.getName();
                            System.out.println("itemName: " + itemName);
                            if (itemName.isEmpty()) {
                                continue;
                            }
                            filename = itemName.substring(
                                    itemName.lastIndexOf("\\") + 1);

                            filename = FilenameUtils.getBaseName(filename) + System.currentTimeMillis() + '.'
                                    + FilenameUtils.getExtension(filename);

                            // get path to save file
                            String servletContextPath = getServletContext().getRealPath("/");
                            String pathToSave = servletContextPath.substring(0, servletContextPath.lastIndexOf("build"));
                            String realPath = pathToSave + "web\\public\\images\\" + filename;
                            System.out.println("RealPath: " + realPath);
                            System.out.println("BuildPath: " + servletContextPath + "public\\images\\" + filename);

                            // save file to folder (outside deployed folder)
                            File savedFile = new File(realPath);
                            item.write(savedFile);

                            // copy uploaded file to build (deployed folder)
                            FileUtils.copyFile(savedFile, new File(servletContextPath + "public\\images\\" + filename));

                            // get img path attributes
                            img = "public\\images\\" + filename;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                // get params
                String idCake = (String) params.get("idCake");
                String nameCake = (String) params.get("nameCake");
                String imageCake = img;
                double price = Double.parseDouble((String) params.get("price"));

                // remember to include "" or this will cause exception
                String descCake = (String) params.get("descCake") + "";
                String createDate = (String) params.get("createDate");
                String expirationDate = (String) params.get("expirationDate");
                int quantity = Integer.parseInt((String) params.get("quantity"));
                String idCategory = (String) params.get("idCategory");

                cake = new Cake(idCake, nameCake, imageCake, price,
                        descCake, createDate, expirationDate, quantity, "active", new Category(idCategory));

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                cake.setLastUpdateDate(formatter.format(date));

                cake.setLastUpdateUser(account.getEmail());
                CakeBLO blo = new CakeBLO();

                if (blo.addCake(cake)) {
                    request.setAttribute("MESSAGE", "Added!");
                } else {
                    request.setAttribute("MESSAGE", "Duplicate IdCake");
                }
                request.setAttribute("CAKE", cake);
            }
        } catch (Exception e) {
            log("ERROR at AddCakeController: " + e.getMessage());
            request.setAttribute("CAKE", cake);
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
