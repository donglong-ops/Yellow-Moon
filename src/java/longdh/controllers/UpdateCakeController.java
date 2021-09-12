/*
 * To change this license header, choose Lic  ense Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import longdh.utils.Util;
import longdh.cake.CakeDAO;
import longdh.cake.CakeDTO;
import longdh.cake.CakeCreateError;
import longdh.registration.RegistrationDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "UpdateCakeController", urlPatterns = {"/UpdateCakeController"})
public class UpdateCakeController extends HttpServlet {

    private final String UPDATE_FAIL_PAGE = "editCake.jsp";
    private final String LOST_SESSION_PAGE = "login.html";
    private final String UPDATE_SUCCESS_PAGE = "DispatcherController?btAction=Manager";

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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String url = UPDATE_FAIL_PAGE;
        HttpSession session = request.getSession();
        RegistrationDTO userDto = (RegistrationDTO) session.getAttribute("USER");

        if (userDto == null || userDto.getRole().getName().equals("User")) {
            url = LOST_SESSION_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        CakeCreateError errors = new CakeCreateError();
        boolean fourdErr = false;
        try {
            boolean isMutilpart = ServletFileUpload.isMultipartContent(request);
            if (isMutilpart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
                Map<String, String> parameters = new HashMap<>();
                FileItem fileItem = null;
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        parameters.put(item.getFieldName(), item.getString());
                    }
                    if (item.getFieldName().equals("fileImage")) {
                        fileItem = item;
                    }
                }
                Timestamp now = new Timestamp(System.currentTimeMillis());
                Date updateDate = new Date(now.getTime());
                String cakeId = parameters.get("txtCakeId");
                String cakename = parameters.get("txtCake");
                String cakeprice = parameters.get("txtPrice");
                String cakequantity = parameters.get("txtQuantity");
                String categori_id = parameters.get("txtCategory");
                String description = parameters.get("txtDes");
                String status = parameters.get("txtStatus");
                String currImageLink = parameters.get("txtImage");
                String exprirationDate = parameters.get("txtExprirationDate");

                Date expriraDate = null;
                try {
                    if(exprirationDate.length() > 9){
                        expriraDate = Util.formatDate(exprirationDate);
                    }else{
                        fourdErr = true;
                        errors.setExpriraDateErr("Select Expriration date for cake");
                    }
                } catch (ParseException ex) {
                    log("Error UpdateCake ParseDate Expriration: " + ex.getMessage());
                }

                String imageLink = null;
                try {
                    String uploadPath = request.getServletContext().getRealPath("/") + "img" + File.separator;
                    String fileName = Util.randomFileName(15);
                    String extension = Util.getFileExtension(fileItem.getName());
                    File fileUpload = new File(uploadPath + fileName + extension);
                    fileItem.write(fileUpload);
                    imageLink = "img/" + fileName + extension;
                } catch (Exception ex) {
                    errors.setImageErr("Failed to upload image!");
                }

                if (cakename.trim().length() < 2 || cakename.trim().length() > 70) {
                    fourdErr = true;
                    errors.setCakenameErr("Cake name requred from 2 to 70 characters");
                }
                float price = 0;
                try {
                    price = Float.parseFloat(cakeprice);
                } catch (Exception e) {

                }
                if (price < 0) {
                    fourdErr = true;
                    errors.setCakepriceErr("Price must be number");
                }
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(cakequantity);
                } catch (Exception e) {
                    
                }
                if (quantity == 0 || quantity < 0) {
                    fourdErr = true;
                    errors.setQuantityErr("Quantity must number and greate than 0");
                }

                if (description.trim().length() < 2 || description.trim().length() > 300) {
                    fourdErr = true;
                    errors.setDescriptionErr("Description required from 2 to 300 characters");
                }

                int categoryId = 0;
                try {
                    categoryId = Integer.parseInt(categori_id);
                } catch (Exception e) {

                }
                String imageUp;
                if (imageLink != null) {
                    imageUp = imageLink;
                } else {
                    imageUp = currImageLink;
                }
                if (fourdErr) {
                    CakeDTO dto = new CakeDTO(Integer.parseInt(cakeId),Integer.parseInt(status), cakename, price, quantity, categoryId, description, imageUp, updateDate, expriraDate, userDto.getId());
                    request.setAttribute("CAKEDTO", dto);
                    request.setAttribute("CREATEERROR", errors);
                } else {
                    CakeDAO dao = new CakeDAO();
                    if (session.getAttribute("USER") != null) {
                        CakeDTO dto = new CakeDTO(Integer.parseInt(cakeId),Integer.parseInt(status), cakename, price, quantity, categoryId, description, imageUp, updateDate, expriraDate, userDto.getId());
                        boolean result = dao.updateCake(dto);
                        if (result) {
                            url = UPDATE_SUCCESS_PAGE;
                            request.removeAttribute("AllPRODUCT");
                        }
                    } else {
                        session.removeAttribute("USER");
                        url = LOST_SESSION_PAGE;
                    }
                }
            }

        } catch (SQLException ex) {
            log("Error UpdateCake SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error UpdateCake Naming: " + ex.getMessage());
        } catch (FileUploadException ex) {
            log("Error UpdateCake File Upload: " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            out.close();
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
