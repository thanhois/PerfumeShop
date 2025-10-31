/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.UserDAO;
import dao.WalletDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import model.Wallet;
import util.JPAUtil;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        String fName = request.getParameter("name");
        String uName = request.getParameter("username");
        String uPass = request.getParameter("password");
        String uPho = request.getParameter("phone");
        String uEmail = request.getParameter("email");
        String birthDate = request.getParameter("dob");

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        UserDAO ud = new UserDAO(em);
        WalletDAO wd = new WalletDAO(em);

        String message;

        try {
            // Kiểm tra trùng username
            if (ud.checkUserNameDuplicate(uName)) {
                message = "Username already exists!";
                request.setAttribute("error", message);
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Kiểm tra trùng email
            if (ud.isEmailExist(uEmail)) {
                message = "Email already registered!";
                request.setAttribute("error", message);
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Đăng ký user mới
            ud.insertUser(uName, fName, uPass, 2, uEmail, birthDate, uPho);

            // Tạo ví với số dư = 0
            Wallet wallet = new Wallet(uName, 0);
            wd.addWallet(wallet);

            if (wd.getWalletByUserName(uName) != null) {
                message = "Register successfully. Please Login!";
                request.setAttribute("successfully", message);
            } else {
                message = "Wallet creation failed!";
                request.setAttribute("error", message);
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "Error during registration: " + e.getMessage();
            request.setAttribute("error", message);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        } finally {
            if (em.isOpen()) em.close();
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
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
