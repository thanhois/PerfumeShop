/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
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
import util.JPAUtil;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
    @PersistenceUnit(unitName = "PerfumeShopPU")
    private EntityManagerFactory emf;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
        CategoryDAO categoryDAO = new CategoryDAO(em);
        ProductDAO productDAO = new ProductDAO(em);

        List<Category> categories = categoryDAO.getAll();
        List<Product> allProducts = productDAO.getAll();
        List<Product> productsYear = productDAO.getProductByYear(2023);
        Boolean[] chid = new Boolean[categories.size() + 1];
        chid[0] = true;

        String cidYearRaw = request.getParameter("cidYear");
        if (cidYearRaw != null) {
            try {
                int cidYear = Integer.parseInt(cidYearRaw);
                Category category = em.find(Category.class, cidYear);
                if (category != null) {
                    productsYear = productDAO.getProductsBrandByInYear(2023, category);
                    chid[0] = false;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        int page = 1, numPerPage = 9;
        int total = allProducts.size();
        int numberPage = (total + numPerPage - 1) / numPerPage;
        String xpage = request.getParameter("page");
        if (xpage != null) {
            try {
                page = Integer.parseInt(xpage);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        int start = (page - 1) * numPerPage;
        int end = Math.min(page * numPerPage, total);

        List<Product> productPage = productDAO.getListByPage(allProducts, start, end);
        List<Product> topSellers = productDAO.getTopBestSellers(5);
        List<Product> giftSets = productDAO.getGiftSets();
        List<Product> featured1 = productDAO.getFeaturedProducts();
        List<Product> featured2 = productDAO.getFeaturedProducts();
        Product hotDeal = productDAO.getHotDeal();

        request.setAttribute("listAll", allProducts);
        request.setAttribute("cidYear", cidYearRaw);
        request.setAttribute("category", categories);
        request.setAttribute("productsYear", productsYear);
        request.setAttribute("chid", chid);
        request.setAttribute("productPage", productPage);
        request.setAttribute("page", page);
        request.setAttribute("numberpage", numberPage);
        request.setAttribute("productsTopSellers", topSellers);
        request.setAttribute("giftSets", giftSets);
        request.setAttribute("productFooter1", featured1);
        request.setAttribute("productFooter2", featured2);
        request.setAttribute("hotDeal", hotDeal);

        request.getRequestDispatcher("home.jsp").forward(request, response);
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
        return "Displays the home page with products and categories";
    }// </editor-fold>

}
