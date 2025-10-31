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
import model.Product;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ViewWishListServlet", urlPatterns = {"/viewwishlist"})
public class ViewWishListServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewWishListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewWishListServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Cart wishList = (Cart) session.getAttribute("wishList");
        if (wishList == null) {
            wishList = new Cart();
        }

        String tRid = request.getParameter("rid");
        try {
            int rid = Integer.parseInt(tRid);
            wishList.removeItem(rid);
        } catch (Exception e) {
            // ignore
        }

        List<Item> listItemsInWishList = wishList.getListItems();
        session.setAttribute("wishList", wishList);
        session.setAttribute("listItemsInWishList", listItemsInWishList);
        session.setAttribute("wishListSize", listItemsInWishList.size());

        request.getRequestDispatcher("viewwishlist.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        ProductDAO pd = new ProductDAO(em);

        String role = request.getParameter("role");
        if ("add".equals(role)) {
            try {
                int num = Integer.parseInt(request.getParameter("quantity"));
                int id = Integer.parseInt(request.getParameter("id"));
                Product p = pd.getProductByID(id);
                Item t = new Item(p, num);
                cart.addItem(t);
            } catch (Exception e) {
                // ignore
            }

            session.setAttribute("cart", cart);
            session.setAttribute("listItemsInCart", cart.getListItems());
            session.setAttribute("cartSize", cart.getListItems().size());

            Cart wishList = (Cart) session.getAttribute("wishList");
            if (wishList == null) {
                wishList = new Cart();
            }

            try {
                int rid = Integer.parseInt(request.getParameter("id"));
                wishList.removeItem(rid);
            } catch (Exception e) {
                // ignore
            }

            session.setAttribute("wishList", wishList);
            session.setAttribute("listItemsInWishList", wishList.getListItems());
            session.setAttribute("wishListSize", wishList.getListItems().size());

            request.getRequestDispatcher("viewwishlist.jsp").forward(request, response);

        } else if ("remove".equals(role)) {
            try {
                int rid = Integer.parseInt(request.getParameter("rid"));
                cart.removeItem(rid);
            } catch (Exception e) {
                // ignore
            }

            session.setAttribute("cart", cart);
            session.setAttribute("listItemsInCart", cart.getListItems());
            session.setAttribute("cartSize", cart.getListItems().size());

            request.getRequestDispatcher("viewwishlist.jsp").forward(request, response);
        }

        em.close();
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
