/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ProfleServlet", urlPatterns = {"/profile"})
public class ProfleServlet extends HttpServlet {

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
            out.println("<title>Servlet ProfleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfleServlet at " + request.getContextPath() + "</h1>");
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
        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        UserDAO ud = new UserDAO(em);
        String imageUpdate = request.getParameter("imagelink");
        String userId = request.getParameter("uid");
        String link = "";
        HttpSession session = request.getSession();

        if (imageUpdate != null) {
            String[] arr = imageUpdate.split("_");
            for (String s : arr) {
                link += s + "/";
            }
        }

        if (link.endsWith("/")) {
            link = link.substring(0, link.length() - 1);
        }

        if (userId != null && !link.isEmpty()) {
            ud.updateImage(link, userId); // dùng JPA trong DAO
        }

        User account = null;
        if (userId != null) {
            account = ud.getUserByUserName(userId);
        }

        if (account != null) {
            session.setAttribute("imageUser", account.getImage());
        }

        request.setAttribute("acceptUpdate", 0);
        request.setAttribute("link", link);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        UserDAO u = new UserDAO(em);

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String birthdate = request.getParameter("birthday");
        HttpSession session = request.getSession();

        // Gọi hàm update theo JPA
        u.update(name, address, phone, email, birthdate, username);

        // Sau cập nhật lấy lại User mới
        User account = u.getUserByUserName(username);

        if (account != null) {
            session.setAttribute("name", account.getFullName());
            session.setAttribute("address", account.getAddress());
            session.setAttribute("phone", account.getPhone());
            session.setAttribute("email", account.getEmail());
            session.setAttribute("birthdate", account.getBirthdate());
        }

        request.setAttribute("acceptUpdate", 1);
        response.sendRedirect("profile");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles profile update and image";
    }// </editor-fold>

}
