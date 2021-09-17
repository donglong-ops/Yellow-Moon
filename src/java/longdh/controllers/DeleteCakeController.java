/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longdh.category.CategoryDAO;
import longdh.cake.CakeDAO;
import longdh.registration.RegistrationDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "DeleteCakeController", urlPatterns = {"/DeleteCakeController"})
public class DeleteCakeController extends HttpServlet {

    private final String ErrorPage = "errorPage.jsp";

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
        PrintWriter out = response.getWriter();

        String pageNum = request.getParameter("pageNum");
        if(pageNum == null || pageNum.length() > 0){
            pageNum = "1";
        }
        String url = ErrorPage;

        HttpSession session = request.getSession();
        RegistrationDTO dto = (RegistrationDTO) session.getAttribute("USER");

        try {
            CakeDAO dao = new CakeDAO();
            String id = request.getParameter("ID");
            
            boolean result = dao.deleteCake(Integer.parseInt(id), Integer.parseInt(dto.getId()));
            if (result) {
                session.setAttribute("LISTCATE", new CategoryDAO().getAllCategory());
                url = "DispatcherController?btAction=Manager&pageNum=" + pageNum;
            }
        } catch (SQLException ex) {
            log("Error DeleteCake Exeption SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error DeleteCake Naming SQL: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
