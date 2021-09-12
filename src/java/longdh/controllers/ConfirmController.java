/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import longdh.category.CategoryDAO;
import longdh.registration.RegistrationDAO;
import longdh.registration.RegistrationDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "ConfirmController", urlPatterns = {"/ConfirmController"})
public class ConfirmController extends HttpServlet {

    private final String VIEW_CART = "view.jsp";
    private final String CONFIRM_CART = "confirm.jsp";
    private final String CONTROLLER_PAY_MOMO = "DispatcherController?btAction=Momo payment";

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
       
        Timestamp now = new Timestamp(System.currentTimeMillis());
        BookingDetailDAO bookingItemDAO = new BookingDetailDAO();
        BookingDAO bookingDAO = new BookingDAO();
        CakeDAO cakeDAO = new CakeDAO();
        RegistrationDAO resDAO = new RegistrationDAO();
        int userID = -1;
        try {
            HttpSession session = request.getSession();
            session.setAttribute("LISTCATE", new CategoryDAO().getAllCategory()); 
            RegistrationDTO userDTO = (RegistrationDTO) session.getAttribute("USER");
            CartObject cart = (CartObject) session.getAttribute("CART");

            String paymentType = request.getParameter("txtPaymentType");
            String customerName = request.getParameter("txtCustomerName");
            String customerPhone = request.getParameter("txtCustomerPhone");
            String customerAddress = request.getParameter("txtCustomerAddress");

            if (session.getAttribute("USER") == null) { // user chưa login
                System.out.println("session null");
                if (customerName == null || customerPhone.length() < 9 || customerAddress == null) {
                    request.setAttribute("CHECKOUTERROR", "All infomation of user required!");
                    url = VIEW_CART;
                    return;
                }
                // infomation of User
                RegistrationDTO userDto = new RegistrationDTO();
                userDto.setEmail("guess@gmail.com");
                userDto.setFullname(customerName);
                userDto.setPhone(customerPhone);
                userDto.setAddress(customerAddress);
                
                userID = resDAO.insertAccount(userDto); // insert vô và trả lại userID đã insert
                request.setAttribute("GUESSINFO", userDto);
            }else{
                userID = userDTO.getId();
                System.out.println("userID của session nè: " + userID);
            }
            System.out.println("userID ra ngoai nè : " + userID);
            int notBookedYet = -1;
            if (userID > 0 && cart.getItems().size() > 0) {
                if (cart.getCake() != null) {
                    Map<Integer, Integer> items = cart.getItems();
                    Map<Integer, CakeDTO> cakeItems = cart.getCake();

                    String confirmError = null;
                    for (Integer cakeId : items.keySet()) {
                        CakeDTO cakeInfo = cakeItems.get(cakeId);
                        int amount = items.get(cakeId);
                        int cakeQuantity = cakeDAO.getCakeQuantity(cakeId);
                        notBookedYet = cakeQuantity - amount;

                        if (notBookedYet < 0) {
                            confirmError = ("Cake: " + cakeInfo.getCakeName() + " is invalid! ( Remainings: " + cakeQuantity + " amount )");
                            request.setAttribute("CONFIRM_ERROR", confirmError);
                        }

                    }

                    if (confirmError == null) {
                        BookingDTO dto = new BookingDTO(userID, new Date(now.getTime()), cart.getTotalPrice());

                        if (items.size() > 0) {
                            int idBookingInsert = bookingDAO.insertBookingCake(dto);
                            dto.setId(idBookingInsert);
                            dto.setPayment("CASH");
                            dto.setPaymentStatus("Delivery Success");

                            if (idBookingInsert != -1) {
                                Set<Integer> keyList = items.keySet();
                                for (Integer cakeId : keyList) {
                                    BookingDetailDTO bookingItemDTO = new BookingDetailDTO(dto.getId(), cakeId, items.get(cakeId));
                                    bookingItemDAO.insertBookingItem(bookingItemDTO);
                                    if (notBookedYet >= 0) {
                                        boolean result = cakeDAO.updateCakeQuantity(cakeId, notBookedYet);
                                        if (result) {
                                            System.out.println("Đã update quantity cake dưới DB ");
                                        }
                                    }
                                }
                                if (paymentType.equals("CASH")) {
                                    url = CONFIRM_CART;
                                    request.setAttribute("CART", cart);
                                    session.removeAttribute("CART");
                                }
                                if (paymentType.equals("MOMO")) {
                                    url = CONTROLLER_PAY_MOMO;
                                    request.setAttribute("BOOKING_CONFIRM", dto);
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
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
