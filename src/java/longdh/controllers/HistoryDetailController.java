/*
 * To change this license header, choose Lic  ense Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longdh.booking.BookingDAO;
import longdh.booking.BookingDTO;
import longdh.bookingdetail.BookingDetailDAO;
import longdh.bookingdetail.BookingDetailDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "HistoryDetailController", urlPatterns = {"/HistoryDetailController"})
public class HistoryDetailController extends HttpServlet {

    private final String VIEW_HISTORY_DETAIL = "historyDetail.jsp";

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
        String url = VIEW_HISTORY_DETAIL;
        BookingDetailDAO bookingItemDAO = new BookingDetailDAO();
        BookingDAO dao = new BookingDAO();
        int bookingID = 0;
        String booking_ID = request.getParameter("ID");
        if(!booking_ID.equals("")){
            bookingID = Integer.parseInt(request.getParameter("ID"));
        }

        try {
            List<BookingDetailDTO> listDetail = bookingItemDAO.itemByBookingID(bookingID);
            BookingDTO dto = dao.getBooking(bookingID);
            request.setAttribute("DETAILBYID", listDetail);
            request.setAttribute("DTO", dto);
        } catch (SQLException ex) {
            log("Error HisDetail SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error HisDetail Naming: " + ex.getMessage());
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
