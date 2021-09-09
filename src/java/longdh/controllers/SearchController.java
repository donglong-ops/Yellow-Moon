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
import longdh.category.CategoryDAO;
import longdh.cake.CakeDAO;
import longdh.cake.CakeDTO;

/**
 *
 * @author Dong Long
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private final String URL_SEARCH_PAGE = "search.jsp";

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        
        HttpSession session = request.getSession();
        String url = URL_SEARCH_PAGE;
        String searchName = new String(request.getParameter("txtSearchName").getBytes("iso-8859-1"), "UTF-8");
        String searchCate = request.getParameter("txtSearchCategory");
        String searchFromPrice = request.getParameter("txtFromPrice");
        String searchToPrice = request.getParameter("txtToPrice");
        String pageNum = request.getParameter("pageNum");
        try {
            if (searchCate.isEmpty() && searchFromPrice.isEmpty() && searchToPrice.isEmpty()) {
                url = URL_SEARCH_PAGE;
            } else {
                int cate_id;
                if (searchCate.equals("")) {
                    cate_id = 0;
                } else {
                    cate_id = Integer.parseInt(searchCate);
                }
                
                float fromPrice;
                if (searchFromPrice.equals("")) {
                    fromPrice = -1;
                } else {
                    fromPrice = Float.parseFloat(searchFromPrice);
                }

                float toPrice;
                if (searchToPrice.equals("")) {
                    toPrice = -1;
                } else {
                    toPrice = Float.parseFloat(searchToPrice);
                }

                String cake_name; 
                if (searchName.equals("")) {
                    cake_name = null;
                }else{
                    cake_name = searchName;
                }

                CakeDAO dao = new CakeDAO();
                CategoryDAO cateDao = new CategoryDAO();
                List<CakeDTO> listCakes;
                if (fromPrice == -1 && toPrice == -1 && searchCate.isEmpty()) {
                    //do nothing
                } else {
                    int numberCake = dao.countTotalCake(cake_name,cate_id, toPrice, fromPrice);
                    int page = (int) (Math.ceil((double) numberCake / 5));
                    request.setAttribute("PAGENUMBER", page);
                    int pageIndex = 1;
                    if (pageNum != null && !pageNum.equals("")){
                        pageIndex = Integer.parseInt(pageNum);
                    }
                    listCakes = dao.searchCakePaging(cake_name, cate_id, toPrice, fromPrice, pageIndex);
                    request.setAttribute("SEARCH_RESULT", listCakes);
                    session.setAttribute("LISTCATE", cateDao.getAllCategory());
                }
            }
        } catch (SQLException ex) {
            log("Error SearchCake SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Error SearchCake Naming: " + ex.getMessage());
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
