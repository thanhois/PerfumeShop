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
@WebServlet(name = "ManagerProductServlet", urlPatterns = {"/manager"})
public class ManagerProductServlet extends HttpServlet {

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

        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        ProductDAO daoP = new ProductDAO(em);
        CategoryDAO daoC = new CategoryDAO(em);
        SupplierDAO daoS = new SupplierDAO(em);

        List<Product> list = daoP.getAll();
        List<Category> listC = daoC.getAll();

        int page = 1, numPerPage = 6;
        int size = list.size();
        int numberpage = ((size % numPerPage == 0) ? (size / 6) : (size / 6) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * 6;
        end = Math.min(page * numPerPage, size);

        List<Product> listByPage = daoP.getListByPage(list, start, end);
        List<Supplier> listSup = daoS.getAll();

        request.setAttribute("page", page);
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        request.setAttribute("numberpage", numberpage);
        request.setAttribute("listCC", listC);
        request.setAttribute("listByPage", listByPage);
        request.setAttribute("list", listSup);

        em.close();
        request.getRequestDispatcher("dashboard/mnproduct.jsp").forward(request, response);
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
        processRequest(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        ProductDAO daoP = new ProductDAO(em);
        CategoryDAO daoC = new CategoryDAO(em);
        SupplierDAO daoS = new SupplierDAO(em);

        String txtSearch = request.getParameter("valueSearch");
        List<Product> list = daoP.searchByName(txtSearch);
        List<Category> listC = daoC.getAll();

        int page = 1, numPerPage = 6;
        int size = list.size();
        int numberpage = ((size % numPerPage == 0) ? (size / 6) : (size / 6) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * 6;
        end = Math.min(page * numPerPage, size);

        List<Product> listByPage = daoP.getListByPage(list, start, end);
        List<Supplier> listSup = daoS.getAll();

        request.setAttribute("page", page);
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        request.setAttribute("numberpage", numberpage);
        request.setAttribute("listCC", listC);
        request.setAttribute("listByPage", listByPage);
        request.setAttribute("list", listSup);
        request.setAttribute("searchValue", txtSearch);

        em.close();
        request.getRequestDispatcher("dashboard/mnproduct.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "manage product";
    }// </editor-fold>

}
