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

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ConfirmPassServlet", urlPatterns = {"/confirmpass"})
public class ConfirmPassServlet extends HttpServlet {

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
            out.println("<title>Servlet ConfirmPassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmPassServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("newpassword.jsp").forward(request, response);
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
        String code_raw = request.getParameter("code");
        String newPass = request.getParameter("password");
        String cfPass = request.getParameter("cfpassword");
        String userName = request.getParameter("userName"); // lấy từ form

        HttpSession session = request.getSession();
        Object codeObj = session.getAttribute("resetCode");
        Object resetUserObj = session.getAttribute("resetUser");

        // Kiểm tra session còn tồn tại không
        if (codeObj == null || resetUserObj == null) {
            request.setAttribute("error", "Phiên đặt lại mật khẩu đã hết hạn!");
            request.getRequestDispatcher("forgot.jsp").forward(request, response);
            return;
        }

        int realCode = (Integer) codeObj;
        int inputCode = Integer.parseInt(code_raw);
        String resetUser = String.valueOf(resetUserObj);

        // Kiểm tra thông tin hợp lệ
        if (!resetUser.equalsIgnoreCase(userName)) {
            request.setAttribute("error", "Sai username!");
        } else if (realCode != inputCode) {
            request.setAttribute("error", "Mã xác nhận không đúng!");
        } else if (!newPass.equals(cfPass)) {
            request.setAttribute("error", "Mật khẩu không khớp!");
        } else {
            // Đổi mật khẩu
            EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
            try {
                UserDAO dao = new UserDAO(em);
                dao.changePassword(userName, newPass);
                session.removeAttribute("resetCode");
                session.removeAttribute("resetUser");
                request.setAttribute("mess", "Mật khẩu đã được đặt lại thành công!");
            } catch (Exception e) {
                request.setAttribute("error", "Lỗi khi cập nhật mật khẩu: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (em.isOpen()) {
                    em.close(); // Đảm bảo đóng EntityManager
                }
            }
        }

        // Trả lại dữ liệu cho form
        request.setAttribute("uName", userName);
        request.setAttribute("uPass", newPass);
        request.getRequestDispatcher("newpassword.jsp").forward(request, response);
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
