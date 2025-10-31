/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
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
import model.Product;
import model.Supplier;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ManageSearchProductServlet", urlPatterns = {"/searchProduct"})
public class ManageSearchProductServlet extends HttpServlet {

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
            out.println("<title>Servlet ManageSearchProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageSearchProductServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");

        String txtSearch = request.getParameter("txt");
        String xpage = request.getParameter("page");
        int page = (xpage == null) ? 1 : Integer.parseInt(xpage);
        int numPerPage = 6;

        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            ProductDAO daoP = new ProductDAO(em);
            CategoryDAO daoC = new CategoryDAO(em);
            SupplierDAO daoS = new SupplierDAO(em);

            List<Product> list = daoP.searchByName(txtSearch);
            List<Category> listC = daoC.getAll();
            List<Supplier> listSup = daoS.getAll();

            int size = list.size();
            int numberpage = (size % numPerPage == 0) ? (size / numPerPage) : (size / numPerPage) + 1;
            int start = (page - 1) * numPerPage;
            int end = Math.min(page * numPerPage, size);
            List<Product> listByPage = daoP.getListByPage(list, start, end);

            request.setAttribute("page", page);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.setAttribute("numberpage", numberpage);
            request.setAttribute("listCC", listC);
            request.setAttribute("listByPage", listByPage);
            request.setAttribute("list", listSup);
            request.setAttribute("searchValue", txtSearch);
            request.getRequestDispatcher("ajax/search_product_ajax.jsp").forward(request, response);
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
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Search and paginate products";
    }// </editor-fold>

}
