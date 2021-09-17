/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longdh.cake.CakeDAO;
import longdh.cake.CakeDTO;
import longdh.registration.RegistrationDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "ManageCakeController", urlPatterns = {"/ManageCakeController"})
public class ManageCakeController extends HttpServlet {

    private final String ERROR_PAGE = "errorPage.jsp";
    private final String LOST_SESSION_PAGE = "login.html";
    private final String URL_MANAGER_PAGE = "managerCake.jsp";

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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String url = ERROR_PAGE;
        int pageSize = 5;

        HttpSession session = request.getSession();
        RegistrationDTO userDto = (RegistrationDTO) session.getAttribute("USER");
        String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
            pageNum = "1";
        }
        try {
            if (userDto == null || userDto.getRole().getName().equals("User")) {
                url = LOST_SESSION_PAGE;
                session.removeAttribute("USER");
            } else if (userDto.getRole().getName().equals("Admin")) {
                CakeDAO dao = new CakeDAO();
                List<CakeDTO> listCakes;
                request.removeAttribute("AllPRODUCT");
                int numberFood = dao.countTotalCake();
                int page = (int) (Math.ceil((double) numberFood / 5));
                request.setAttribute("PAGENUMBER", page);
                int pageIndex = 1;
                if (pageNum.length() > 0) {
                    pageIndex = Integer.parseInt(pageNum);
                }
                listCakes = dao.cakePaging(pageIndex, pageSize);
                request.setAttribute("AllPRODUCT", listCakes);
                url = URL_MANAGER_PAGE;
            }
        } catch (SQLException ex) {
            log("Error ManagerCake SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error ManagerCake Naming: " + ex.getMessage());
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
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
