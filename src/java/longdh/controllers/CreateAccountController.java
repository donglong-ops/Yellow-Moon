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
import longdh.registration.RegistrationCreateError;
import longdh.registration.RegistrationDAO;
import longdh.registration.RegistrationDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "CreateAccountController", urlPatterns = {"/CreateAccountController"})
public class CreateAccountController extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
    private final String SUCCESS_PAGE = "login.html";

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
        String url = ERROR_PAGE;
        RegistrationDAO resDAO = new RegistrationDAO();
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean fourdErr = false;
        try {
            String email = request.getParameter("txtEmail");
            String fullname = request.getParameter("txtFullname");
            String password = request.getParameter("txtPassword");
            String rePassword = request.getParameter("txtRepassword");
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");

            if (email.trim().length() < 2 || email.trim().length() > 20) {
                fourdErr = true;
                errors.setEmailLengthError("Email required from 2 to 20 characters");
            }

            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                fourdErr = true;
                errors.setFullnameLengthError("Fullname required from 2 to 50 characters");
            }

            if (password.trim().length() < 2 || password.trim().length() > 10) {
                fourdErr = true;
                errors.setPasswordLengthError("Password from 2 to 10 characters");
            } else if (!password.trim().equals(rePassword.trim())) {
                fourdErr = true;
                errors.setConfirmNotMatched("Confirm must match with password");
            }

            if (phone == null || phone.length() < 9) {
                fourdErr = true;
                errors.setPhoneLengthError("Phone must 10 numbers");
            }

            if (address.trim().length() < 2 || address.trim().length() > 300) {
                fourdErr = true;
                errors.setAddressLengthError("Address from 2 to 300 characters");
            }

            if (fourdErr) {
                request.setAttribute("CREATEERROR", errors);
            } else {
                String encryPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
                RegistrationDTO userDto = new RegistrationDTO();
                userDto.setEmail(email);
                userDto.setName(fullname);
                userDto.setPhone(phone);
                userDto.setAddress(address);
                userDto.setPassword(encryPassword);

                int userID = resDAO.createAccount(userDto);
                if (userID > 0) {
                    url = SUCCESS_PAGE;
                }
            }

        } catch (SQLException | NamingException ex) {
            log("Error CreateAccount SQL | Namming: " + ex.getMessage());
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
