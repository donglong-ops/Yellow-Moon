/*
 * To change this license header, choose Lic  ense Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longdh.booking.BookingDAO;
import longdh.booking.BookingDTO;
import longdh.registration.RegistrationDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "SearchHistoryController", urlPatterns = {"/SearchHistoryController"})
public class SearchHistoryController extends HttpServlet {

    private final String VIEW_HISTORY = "history.jsp";

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
        String url = VIEW_HISTORY;
        BookingDAO bookDao = new BookingDAO();
        try {
            HttpSession session = request.getSession();
            RegistrationDTO userDTO = (RegistrationDTO) session.getAttribute("USER");
            String fromDate = request.getParameter("txtFromDate");
            String toDate = request.getParameter("txtToDate");
            Date fromdate = null;
            Date todate = null;
            List<BookingDTO> listSearch = null;
            if (fromDate.length() < 0 || toDate.length() < 0 ){
                listSearch = bookDao.allBookingUser(userDTO.getId());
                request.setAttribute("ALLHISTORY", listSearch);
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            try {
                long time = ((java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(fromDate)).getTime();
                fromdate = new Date(time);
                long time2 = ((java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(toDate)).getTime();
                todate = new Date(time2);
            } catch (ParseException ex) {
                request.setAttribute("DATEERR", "Date Must be format (yyyy-MM-dd) !!");
            }
            if (fromDate.length() > 0 && toDate.length() > 0) {
                listSearch = bookDao.searchHis(userDTO.getId(), fromdate, todate);
                request.setAttribute("ALLHISTORY", listSearch);
            }
            
        } catch (SQLException ex) {
            log("Error SearchHis SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error SearchHis Naming: " + ex.getMessage());
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
