package longdh.utils;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longdh.registration.RegistrationDAO;
import longdh.registration.RegistrationDTO;

@WebServlet("/login-google")
public class LoginGoogleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginGoogleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        RegistrationDAO dao = new RegistrationDAO();
        HttpSession session = request.getSession();
        String url = "login.jsp";

        if (code == null || code.isEmpty()) {
            request.getRequestDispatcher(url).forward(request, response);
        } else {
            try {
                String accessToken = GoogleUtils.getToken(code);
                RegistrationDTO dto = GoogleUtils.getUserInfo2(accessToken);
                RegistrationDTO userLogin = dao.checkLoginWithGoogle(dto.getEmail());
                if (userLogin == null) {
                    int idInsert = dao.insertAccountWithGoogle(dto.getEmail(), dto.getFullname(), 2, 1, dto.getAvatar());
                    if (idInsert != -1) {
                        RegistrationDTO user = dao.checkLoginWithGoogle(dto.getEmail());
                        session.setAttribute("USER", user );
                    }
                } else {
                    session.setAttribute("USER", userLogin);
                }
                request.getRequestDispatcher("DispatcherController").forward(request, response);
            } catch (SQLException ex) {
                log("Login with Google SQL Failed : " + ex.getMessage());
            } catch (NamingException ex) {
                log("Login with Google Failed : " + ex.getMessage());
            }
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
