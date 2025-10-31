/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import dao.WalletDAO;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.Wallet;
import util.JPAUtil;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        Cookie arr[] = request.getCookies();
        if (arr != null) {
            for (Cookie cookie : arr) {
                switch (cookie.getName()) {
                    case "cUName":
                        request.setAttribute("uName", cookie.getValue());
                        break;
                    case "pUName":
                        request.setAttribute("uPass", cookie.getValue());
                        break;
                    case "reMem":
                        request.setAttribute("reMem", cookie.getValue());
                        break;
                }
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
    request.setCharacterEncoding("UTF-8");
    String uName = request.getParameter("username");
    String uPass = request.getParameter("password");
    String remember = request.getParameter("remember");

    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    UserDAO ud = new UserDAO(em);
    WalletDAO wd = new WalletDAO(em);
    User user = ud.check(uName, uPass);
    HttpSession session = request.getSession();

    if (user == null) {
        request.setAttribute("error", "Username or password invalid!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } else {
        Wallet wallet = wd.getWalletByUserName(uName);
        session.setAttribute("wallet", wallet);
        session.setAttribute("account", user);
        session.setAttribute("imageUser", user.getImage());
        session.setAttribute("address", user.getAddress());
        session.setAttribute("name", user.getFullName());
        session.setAttribute("phone", user.getPhone());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("birthdate", user.getBirthdate());

        // Cookie
        Cookie u = new Cookie("cUName", uName);
        Cookie p = new Cookie("pUName", uPass);
        Cookie r = new Cookie("reMem", remember);
        u.setMaxAge(60 * 60 * 24 * 30 * 3); //luu trong 3 thang
        if (remember != null) {
            p.setMaxAge(60 * 60 * 24 * 30 * 3);
            r.setMaxAge(60 * 60 * 24 * 30 * 3);
        } else {
            p.setMaxAge(0);
            r.setMaxAge(0);
        }
        response.addCookie(u);
        response.addCookie(p);
        response.addCookie(r);

        // Redirect sang HomeServlet thay vì forward đến JSP trực tiếp
        response.sendRedirect("home");
    }
}


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles user logout by invalidating the session and redirecting to home page";
    }// </editor-fold>

}
