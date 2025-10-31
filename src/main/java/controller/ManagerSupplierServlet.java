/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.SupplierDAO;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Supplier;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ManagerSupplierServlet", urlPatterns = {"/managersupplier"})
public class ManagerSupplierServlet extends HttpServlet {

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
            out.println("<title>Servlet ManagerSupplierServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerSupplierServlet at " + request.getContextPath() + "</h1>");
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
        try {
            SupplierDAO daoS = new SupplierDAO(em);
            List<Supplier> listAllSupplier = daoS.getAll();
            request.setAttribute("listAllSupplier", listAllSupplier);
            request.getRequestDispatcher("dashboard/supplier.jsp").forward(request, response);
        } finally {
            em.close();
        }
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
        String txtSearch = request.getParameter("valueSearch");

        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            SupplierDAO daoS = new SupplierDAO(em);
            CategoryDAO daoC = new CategoryDAO(em); // nếu chưa JPA, giữ nguyên

            List<Supplier> listAllSupplier = daoS.getSuppliersBySearch(txtSearch);
            List<Category> listAllCategory = daoC.getAll();

            request.setAttribute("listAllSupplier", listAllSupplier);
            request.setAttribute("listAllCategory", listAllCategory);
            request.setAttribute("searchValue", txtSearch);

            request.getRequestDispatcher("dashboard/supplier.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Manage suppliers (view + search)";
    }// </editor-fold>

}
