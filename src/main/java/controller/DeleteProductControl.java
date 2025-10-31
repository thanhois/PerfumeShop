/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Item;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "DeleteProductControl", urlPatterns = {"/deleteproduct"})
public class DeleteProductControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }

        String pid = request.getParameter("pid");
        int id = Integer.parseInt(pid);
        String msg = "";

        // Sử dụng JPA EntityManager
        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        ProductDAO dao = new ProductDAO(em);

        try {
            em.getTransaction().begin();
            dao.deleteProduct(id); // DAO xử lý JPA xoá
            em.getTransaction().commit();

            // Xoá trong giỏ hàng session nếu có
            cart.removeItem(id);
            List<Item> list = cart.getListItems();
            session.setAttribute("cart", cart);
            session.setAttribute("listItemsInCart", list);
            session.setAttribute("cartSize", list.size());

            msg = "Product " + pid + " deleted successfully";

        } catch (Exception e) {
            em.getTransaction().rollback();
            msg = "Failed to delete product: " + e.getMessage();
            e.printStackTrace();
        } finally {
            em.close();
        }

        request.setAttribute("mess", msg);
        request.getRequestDispatcher("manager").forward(request, response);
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
        return "delete product";
    }// </editor-fold>

}
