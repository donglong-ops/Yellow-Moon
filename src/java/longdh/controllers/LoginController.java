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
import longdh.registration.RegistrationDAO;
import longdh.registration.RegistrationDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private final String URL_LOGINFAIL_PAGE = "login.jsp";
    private final String URL_SUCCESS_PAGE = "search.jsp";

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
        String url = URL_LOGINFAIL_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
            String encryPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
            if (username != null && password != null && username.trim().length() > 0 && password.trim().length() > 0) {
                RegistrationDAO dao = new RegistrationDAO();
                //RegistrationDTO result = dao.checkLogin(username, encryPassword);
                RegistrationDTO result = dao.checkLogin(username, password);
                HttpSession session = request.getSession();
                if (result != null) {
                    session.setAttribute("USER", result);
                    url = URL_SUCCESS_PAGE;
                    CategoryDAO cateDao = new CategoryDAO();
                    session.setAttribute("LISTCATE", cateDao.getAllCategory());
                } else {
                    request.setAttribute("LOGINFAIL", "Invalid Email or Password !!!");
                }
            }

        } catch (SQLException ex) {
            log("Error Login SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error Login Naming: " + ex.getMessage());
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
