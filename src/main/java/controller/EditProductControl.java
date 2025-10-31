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
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Category;
import model.Product;
import model.Supplier;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "EditProductControl", urlPatterns = {"/editproduct"})
public class EditProductControl extends HttpServlet {

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

        String pid_raw = request.getParameter("id");
        String pname = request.getParameter("name");
        String[] pimage = request.getParameterValues("image");
        String pprice_raw = request.getParameter("price");
        String pdescribe = request.getParameter("describe");
        String pquantity_raw = request.getParameter("quantity");
        String pdate_raw = request.getParameter("date");
        String pdiscount_raw = request.getParameter("discount");
        String pquantityunit = request.getParameter("quantityperunit");
        String pcategory_raw = request.getParameter("category");
        String psupplier_raw = request.getParameter("supplier");

        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        ProductDAO dao = new ProductDAO(em);

        try {
            int pid = Integer.parseInt(pid_raw);
            double pprice = Double.parseDouble(pprice_raw);
            double pdiscount = Double.parseDouble(pdiscount_raw);
            int pquantity = Integer.parseInt(pquantity_raw);
            int psupplier = Integer.parseInt(psupplier_raw);
            int pcategory = Integer.parseInt(pcategory_raw);
            String image = "";
            
                for (String img : pimage) {
                String path = "images/products/";
                switch (pcategory) {
                    case 1:
                        path += "Men/";
                        break;
                    case 2:
                        path += "Women/";
                        break;
                    case 3:
                        path += "Kids/";
                        break;
                    case 4:
                        path += "Unisex/";
                        break;
                    case 5:
                        path += "Gift/";
                        break;
                }
                image += path + img + ",";
            }
            if (image.endsWith(",")) {
                image = image.substring(0, image.length() - 1);
            }

            // Parse ngày
            Date pdate = new SimpleDateFormat("yyyy-MM-dd").parse(pdate_raw);

            // Tìm product cũ và cập nhật
            em.getTransaction().begin();
            Product p = em.find(Product.class, pid);
            if (p != null) {
                p.setName(pname);
                p.setImageRaw(image);
                p.setPrice(pprice);
                p.setDescribe(pdescribe);
                p.setQuantity(pquantity);
                p.setClassifyStr(pquantityunit);
                p.setDate(pdate);
                p.setDiscount(pdiscount);

                Category c = em.find(Category.class, pcategory);
                Supplier s = em.find(Supplier.class, psupplier);
                p.setCategory(c);
                p.setSupplier(s);
                em.merge(p);
            }
            em.getTransaction().commit();

            request.setAttribute("mess", "Edit successfully!");
            request.getRequestDispatcher("manager").forward(request, response);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            request.setAttribute("mess", "Error: " + e.getMessage());
            request.getRequestDispatcher("manager").forward(request, response);
        } finally {
            em.close();
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
        return "edit ";
    }// </editor-fold>

}
