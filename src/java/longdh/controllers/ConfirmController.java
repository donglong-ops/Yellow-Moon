/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longdh.booking.BookingDAO;
import longdh.booking.BookingDTO;
import longdh.bookingdetail.BookingDetailDAO;
import longdh.bookingdetail.BookingDetailDTO;
import longdh.cart.CartObject;
import longdh.cake.CakeDAO;
import longdh.cake.CakeDTO;
import longdh.registration.RegistrationDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "ConfirmController", urlPatterns = {"/ConfirmController"})
public class ConfirmController extends HttpServlet {

    private final String VIEW_CART = "view.jsp";
    private final String CONFIRM_CART = "confirm.jsp";

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
        String url = VIEW_CART;
        try {
            HttpSession session = request.getSession();
            RegistrationDTO userDTO = (RegistrationDTO) session.getAttribute("USER");
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (userDTO.getId() > 0 && cart.getTotalPrice() != 0) {
                if (cart.getCake() != null) {
                    Map<Integer, Integer> items = cart.getItems();
                    Map<Integer, CakeDTO> cakeItems = cart.getCake();
                    BookingDetailDAO bookingItemDAO = new BookingDetailDAO();
                    BookingDAO bookingDAO = new BookingDAO();

                    List<String> confirmError = new ArrayList<>();
                    for (Integer cakeId : items.keySet()) {
                        int totalBooked = bookingItemDAO.countTotalBookedCake(cakeId);
                        CakeDTO cakeInfo = cakeItems.get(cakeId);
                        int amount = items.get(cakeId);
                        CakeDAO dao = new CakeDAO();
                        int cakeQuan = dao.getCakeQuantity(cakeId);
                        int notBookedYet = cakeQuan - totalBooked;

                        if (notBookedYet - amount < 0) {
                            confirmError.add("Cake: " + cakeInfo.getCakeName()+ " is invalid! (Remainings: " + notBookedYet + "!)");
                        }
                    }

                    if (confirmError.isEmpty()) {
//                        userDTO = (RegistrationDTO) session.getAttribute("USER");
                        System.out.println("UserId nè ở session: " + userDTO.getId());
                        BookingDTO dto = new BookingDTO(userDTO.getId(), new Timestamp(System.currentTimeMillis()), cart.getTotalPrice());
                        if (items != null) {
                            int idBookingInsert = bookingDAO.insertBookingFood(dto);
                            dto.setId(idBookingInsert);
                            if (idBookingInsert != -1) {
                                Set<Integer> keyList = items.keySet();
                                for (Integer cakeId : keyList) {
                                    BookingDetailDTO bookingItemDTO = new BookingDetailDTO(dto.getId(), cakeId, items.get(cakeId));
                                    bookingItemDAO.insertBookingItem(bookingItemDTO);
                                }
                                url = CONFIRM_CART;
                                request.setAttribute("CART", cart);
                                session.removeAttribute("CART");
                            }
                        }
                    } else {
                        request.setAttribute("CONFIRM_ERROR", confirmError);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log("Error Confirm SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error Naming: " + ex.getMessage());
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
    }
}
