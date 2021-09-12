/*
 * To change this license header, choose Lic  ense Headers in Project Properties.
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
import longdh.cake.CakeDAO;
import longdh.cake.CakeDTO;
import longdh.category.CategoryDAO;
import longdh.status.StatusDAO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "EditCakeController", urlPatterns = {"/EditCakeController"})
public class EditCakeController extends HttpServlet {

    private final String ERROR_PAGE = "errorPage.jsp";
    private final String SUCCESS_PAGE = "editCake.jsp";

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

        String url = ERROR_PAGE;
        String cakeId = request.getParameter("ID");
        HttpSession session = request.getSession();
        try {
            if (cakeId != null) {
                CakeDAO dao = new CakeDAO();
                CakeDTO dto = dao.getCakeByID(Integer.parseInt(cakeId));
                request.setAttribute("CAKEDTO", dto);
                session.setAttribute("LISTSTATUS", new StatusDAO().getAllStatus());
                session.setAttribute("LISTCATE", new CategoryDAO().getAllCategory());
                url = SUCCESS_PAGE;
            }
        } catch (SQLException ex) {
            log("Error EditCake SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error EditCake Naming: " + ex.getMessage());
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
