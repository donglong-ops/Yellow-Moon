/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longdh.booking.BookingDTO;
import longdh.utils.MomoResponse;
import longdh.utils.MomoUtils;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "MomoPayController", urlPatterns = {"/MomoPayController"})
public class MomoPayController extends HttpServlet {
    private final String URL_LOGIN_PAGE = "login.html";
    private final String ERROR_PAGE = "errorPage.jsp";
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
        String url = URL_LOGIN_PAGE;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                session.removeAttribute("USER");
            }
            BookingDTO booking = (BookingDTO) request.getAttribute("BOOKING_CONFIRM"); 
            if (booking != null) {
                String orderInfo = "Cakes Moon Payment";
                MomoResponse momoRes = MomoUtils.requestPayment(
                        (int)booking.getTotal()+ "",
                        booking.getId()+ " YellowMoonSayHi", 
                        orderInfo,
                        "userId=" + booking.getUserId() + ";" + "discountId=0"
                );
                url = momoRes.getPayUrl();
            }
        } catch (Exception ex) {
            url = ERROR_PAGE;
            log("Error Momopay Controller: " + ex.getMessage());
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
