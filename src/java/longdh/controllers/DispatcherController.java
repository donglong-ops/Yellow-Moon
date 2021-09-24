/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dong Long
 */
public class DispatcherController extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String HOME_CONTROLLER = "HomeController";
    private final String LOGIN_CONTROLLER = "LoginController";
    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountController";
    private final String LOGIN_GOOGLE_CONTROLLER = "LoginGoogleController";
    private final String LOGOUT_CONTROLLER = "LogoutController";
    private final String SEARCH_CONTROLLER = "SearchController";
    private final String CREATE_CAKE_CONTROLLER ="CreateCakeController";
    private final String DELETE_CAKE_CONTROLLER = "DeleteCakeController";
    private final String UPDATE_CAKE_CONTROLLER = "UpdateCakeController";
    private final String MANAGE_CAKE_CONTROLLER = "ManageCakeController";
    private final String EDIT_CAKE_CONTROLLER = "EditCakeController";
    private final String ADD_CART_CONTROLLER = "AddCartController";
    private final String VIEW_CART_CONTROLLER = "ViewCartController";
    private final String DELETE_CART_CONTROLLER = "DeleteCartController";
    private final String UPDATE_CART_CONTROLLER = "UpdateCartController";
    private final String CONFIRM_CART_CONTROLLER = "ConfirmController";
    private final String HISTORY_CONTROLLER = "HistoryController";
    private final String HISTORY_DETAIL_CONTROLLER = "HistoryDetailController";
    private final String CONFIRM_CONTROLLER = "ConfirmController";
    private final String SEARCH_HISTORY_CONTROLLER = "SearchHistoryController";
    private final String FINISH_PAY_MOMO_CONTROLLER = "FinishPayMomoController";
    private final String MOMO_PAY_CONTROLLER = "MomoPayController";
    private final String PAYPAL_CONTROLLER = "AuthorizePaymentController";

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
        String button = request.getParameter("btAction"); 
        String url = LOGIN_PAGE;
        try {
            if (button == null) {
                url = HOME_CONTROLLER;
            } else if(button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if(button.equals("Create Account")) {
                url = CREATE_ACCOUNT_CONTROLLER;
            } else if(button.equals("LoginGoogle")) {
                url = LOGIN_GOOGLE_CONTROLLER;
            } else if(button.equals("Add New Cake")){
                url = CREATE_CAKE_CONTROLLER;
            } else if(button.equals("Search")){
                url = SEARCH_CONTROLLER;
            } else if(button.equals("Delete")){
                url = DELETE_CAKE_CONTROLLER;
            } else if(button.equals("Update") ){
                url= UPDATE_CAKE_CONTROLLER;
            } else if(button.equals("Edit") ){
                url= EDIT_CAKE_CONTROLLER;
            } else if(button.equals("Manager") ){
                url= MANAGE_CAKE_CONTROLLER;
            } else if(button.equals("LogOut")){
                url= LOGOUT_CONTROLLER;
            } else if(button.equals("Add Cart")){
                url= ADD_CART_CONTROLLER;
            } else if(button.equals("ViewCart")){
                url= VIEW_CART_CONTROLLER;
            } else if(button.equals("DeleteItem")){
                url= DELETE_CART_CONTROLLER;
            } else if(button.equals("Confirm")){
                url= CONFIRM_CART_CONTROLLER;
            }else if(button.equals("Update Item")){
                url= UPDATE_CART_CONTROLLER;
            }else if(button.equals("History")){
                url= HISTORY_CONTROLLER;
            }else if(button.equals("ViewDetail")){
                url= HISTORY_DETAIL_CONTROLLER;          
            }else if(button.equals("Confirm Booking")){
                url= CONFIRM_CONTROLLER;
            }else if(button.equals("Search History")){
                url= SEARCH_HISTORY_CONTROLLER;
            }else if(button.equals("FinishPay")){
                url= FINISH_PAY_MOMO_CONTROLLER;
            }else if(button.equals("Momo payment")){
                url= MOMO_PAY_CONTROLLER;
            }else if(button.equals("Paypal payment")){
                url= PAYPAL_CONTROLLER;
            }
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
