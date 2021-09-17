/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longdh.booking.BookingDAO;
import longdh.registration.RegistrationDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "FinishPayMomoController", urlPatterns = {"/FinishPayMomoController"})
public class FinishPayMomoController extends HttpServlet {

    private final String HISTORY_PAGE = "DispatcherController?btAction=History";
    private final String LOGIN_PAGE = "login.html";

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
        BookingDAO bookingDAO = new BookingDAO();
        String url = LOGIN_PAGE;
        String txtErrorCode = request.getParameter("errorCode");
        String bookingCode = request.getParameter("requestId");
        String[] id = bookingCode.split("\\s", 2);
        int bookingID = Integer.parseInt(id[0]);
        HttpSession session = request.getSession();
        RegistrationDTO userDto = (RegistrationDTO) session.getAttribute("USER");
        BookingDAO dao = new BookingDAO();

        try {
            if (bookingID > 0) {
                bookingDAO.updateStatusBooking(bookingID, bookingID + " YellowMoonSayHi", "Momo Pay");
                if (userDto == null) {
                    userDto = dao.getUserByBookingID(bookingID);
                    if (userDto != null) {
                        session.setAttribute("USER", userDto);
                        session.removeAttribute("CART");
                    }
                } else {
                    url = HISTORY_PAGE;
                }
            }
        } catch (SQLException ex) {
            log("Error FinishPayMomo SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error FinishPayMomo Namming: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
