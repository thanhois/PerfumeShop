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
import util.JPAUtil;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "DeleteSupplierControl", urlPatterns = {"/deletesupplier"})
public class DeleteSupplierControl extends HttpServlet {

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
            out.println("<title>Servlet DeleteSupplierControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteSupplierControl at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        String idSupplier_raw = request.getParameter("sid");
        String msg = "";

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            int idSup = Integer.parseInt(idSupplier_raw);
            Supplier supplier = em.find(Supplier.class, idSup);

            if (supplier != null) {
                em.getTransaction().begin();
                em.remove(supplier);
                em.getTransaction().commit();
                msg = "Supplier " + idSup + " deleted successfully";
            } else {
                msg = "Supplier with ID " + idSup + " not found";
            }

            request.setAttribute("mess", msg);
        } catch (NumberFormatException e) {
            msg = "Invalid supplier ID";
            request.setAttribute("mess", msg);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            request.setAttribute("mess", "Error deleting supplier: " + e.getMessage());
        } finally {
            em.close();
        }

        request.getRequestDispatcher("managersupplier").forward(request, response);
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
        return "Delete ";
    }// </editor-fold>

}
