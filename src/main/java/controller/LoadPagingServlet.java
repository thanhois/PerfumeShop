/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "LoadPagingServlet", urlPatterns = {"/loadpaging"})
public class LoadPagingServlet extends HttpServlet {

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
            out.println("<title>Servlet LoadPagingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadPagingServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");

        String amount = request.getParameter("exits");
        int iamount = 0;
        try {
            iamount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            iamount = 0;
        }

        EntityManager em = Persistence
                .createEntityManagerFactory("PerfumeShopPU")
                .createEntityManager();

        // JPA paging query: lấy 9 sản phẩm tiếp theo
        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.status = 1 ORDER BY p.id", Product.class);
        query.setFirstResult(iamount);
        query.setMaxResults(9);
        List<Product> list = query.getResultList();

        request.setAttribute("productPage", list);
        request.setAttribute("col", 4);
        request.getRequestDispatcher("ajax/search_ajax.jsp").forward(request, response);

        em.close();
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
        return "Tải 9 sản phẩm tiếp theo bằng cách sử dụng phân trang";
    }// </editor-fold>

}
