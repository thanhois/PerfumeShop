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
import java.util.Random;
import model.Email;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ForgotPassServlet", urlPatterns = {"/forgot"})
public class ForgotPassServlet extends HttpServlet {

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
            out.println("<title>Servlet ForgotPassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPassServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("forgot.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String emailInput = request.getParameter("email");
        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        UserDAO ud = new UserDAO(em);
        Email handleEmail = new Email();

        String email = ud.checkEmailExist(emailInput);
        String message = "";
        String check = null;

        if (email != null) {
            Random random = new Random();
            String userName = ud.getUserNameByEmail(email);
            int code = 100000 + random.nextInt(900000);
            String code_str = String.valueOf(code);

            String subject = handleEmail.subjectForgotPass();
            String msgEmail = handleEmail.messageForgotPass(userName, code);

            boolean sent = handleEmail.sendEmail(subject, msgEmail, email);
            if (sent) {
                session.setAttribute("code", code_str);
                session.setAttribute("email", emailInput);
                request.setAttribute("check", "true");
                request.setAttribute("message", "EXIST - valid email, check your email to have reset code");
            } else {
                request.setAttribute("check", "false");
                request.setAttribute("message", "Lỗi khi gửi email. Vui lòng thử lại sau.");
                System.out.println(" Không gửi được email đến: " + email);
            }
            request.getRequestDispatcher("forgot.jsp").forward(request, response);
        }
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
