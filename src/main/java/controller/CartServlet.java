/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;
import util.JPAUtil;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

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
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        String role = request.getParameter("role");
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            ProductDAO pd = new ProductDAO(em);
            if ("add".equals(role)) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    Product product = pd.getProductByID(id);
                    if (product != null) {
                        int available = product.getQuantity();

                        // Kiểm tra nếu số lượng mua vượt quá tồn kho
                        if (quantity > available) {
                            session.setAttribute("cartMessage", "Sản phẩm \"" + product.getName() + "\" chỉ còn " + available + " sản phẩm trong kho.");
                        } else {
                            cart.addItem(new Item(product, quantity));
                            session.setAttribute("cartMessage", "Đã thêm vào giỏ: " + quantity + " sản phẩm \"" + product.getName() + "\"");
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            if ("remove".equals(role)) {
                try {
                    int id = Integer.parseInt(request.getParameter("rid"));
                    cart.removeItem(id);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            List<Item> items = cart.getListItems();
            session.setAttribute("cart", cart);
            session.setAttribute("listItemsInCart", items);
            session.setAttribute("cartSize", items.size());

        } finally {
            em.close();
        }

        String requestedWith = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(requestedWith);

        if (isAjax) {
            request.getRequestDispatcher("ajax/header_right_ajax.jsp").forward(request, response);
        } else {
            response.sendRedirect("home"); // hoặc "viewcart" nếu bạn muốn chuyển đến đó
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
        doGet(request, response); // Nếu cần xử lý tương tự GET
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles adding and removing items from cart";
    }// </editor-fold>

}
