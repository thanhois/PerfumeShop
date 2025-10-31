/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Supplier;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "AddSupplierControl", urlPatterns = {"/addsupplier"})
public class AddSupplierControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Lấy thông tin từ form
        String companyName = request.getParameter("CompanyName");
        String contactName = request.getParameter("ContactName");
        String country = request.getParameter("Country");
        String phone = request.getParameter("Phone");
        String homePage = request.getParameter("HomePage");

        // Tạo đối tượng Supplier
        Supplier supplier = new Supplier();
        supplier.setCompanyName(companyName);
        supplier.setConTactName(contactName);
        supplier.setCountry(country);
        supplier.setPhone(phone);
        supplier.setHomepage(homePage);

        // Thêm vào DB bằng JPA
        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(supplier);
            em.getTransaction().commit();

            String msg = "Supplier " + supplier.getCompanyName() + " added successfully!";
            request.setAttribute("mess", msg);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            request.setAttribute("mess", "Failed to add supplier: " + e.getMessage());
        } finally {
            em.close();
        }

        response.sendRedirect("managersupplier");
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Add supplier";
    }// </editor-fold>

}
