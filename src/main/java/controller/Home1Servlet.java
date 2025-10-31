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
@WebServlet(name = "Home1Servlet", urlPatterns = {"/home1"})
public class Home1Servlet extends HttpServlet {

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
            out.println("<title>Servlet Home1Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Home1Servlet at " + request.getContextPath() + "</h1>");
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
        List<Product> productsYear = productDAO.getAll();
        List<Product> productsTop5Sellers = productDAO.getTopBestSellers(5);
        List<Product> giftSets = productDAO.getGiftSets();
        List<Product> listAll = productDAO.getAll();
        List<Product> productFooter1 = productDAO.getFeaturedProducts();
        List<Product> productFooter2 = productDAO.getFeaturedProducts();

        String cidYearRaw = request.getParameter("cidYear");
        int cidYear = 0;
        Boolean[] chid = new Boolean[categories.size() + 1];

        if (cidYearRaw != null) {
            try {
                cidYear = Integer.parseInt(cidYearRaw);
                Category category = em.find(Category.class, cidYear);
                if (category != null) {
                    productsYear = productDAO.getProductsBrandByInYear(2023, category);
                }
            } catch (NumberFormatException ignored) {
            }
        }

        int page = 1, numPerPage = 9;
        int size = listAll.size();
        int numberpage = (size % numPerPage == 0) ? (size / numPerPage) : (size / numPerPage + 1);
        String xpage = request.getParameter("page");
        if (xpage != null) {
            try {
                page = Integer.parseInt(xpage);
            } catch (NumberFormatException ignored) {
            }
        }
        int start = (page - 1) * numPerPage;
        int end = Math.min(page * numPerPage, size);

        Product hotDeal = productDAO.getHotDeal();

        chid[0] = cidYearRaw == null;

        List<Product> listByPage = productDAO.getListByPage(listAll, start, end);

        request.setAttribute("listAll", listAll);
        request.setAttribute("cidYear", cidYearRaw);
        request.setAttribute("category", categories);
        request.setAttribute("productsYear", productsYear);
        request.setAttribute("hotDeal", hotDeal);
        request.setAttribute("productPage", listByPage);
        request.setAttribute("page", page);
        request.setAttribute("chid", chid);
        request.setAttribute("numberpage", numberpage);
        request.setAttribute("productsTopSellers", productsTop5Sellers);
        request.setAttribute("giftSets", giftSets);
        request.setAttribute("productFooter1", productFooter1);
        request.setAttribute("productFooter2", productFooter2);

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
        processRequest(request, response);
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
