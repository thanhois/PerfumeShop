/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Category;
import model.Product;
import model.Supplier;
import util.JPAUtil;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "AddProductControl", urlPatterns = {"/addproduct"})
public class AddProductControl extends HttpServlet {

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
        String pname = request.getParameter("name");
        String[] pimage = request.getParameterValues("image");
        String pprice_raw = request.getParameter("price");
        String pdescribe = request.getParameter("describe");
        String pquantity_raw = request.getParameter("quantity");
        String pquantityunit = request.getParameter("quantityunit");
        String pdate = request.getParameter("date");
        String pdiscount_raw = request.getParameter("discount");
        String psupplier_raw = request.getParameter("supplier");
        String pcategory_raw = request.getParameter("category");

        double pprice = 0, pdiscount = 0;
        int pquantity = 0, psupplier = 0, pcategory = 0;
        String image = "";
        try {
            pprice = Double.parseDouble(pprice_raw);

           
            pdiscount_raw = pdiscount_raw.replace("%", "").trim();
            pdiscount = Double.parseDouble(pdiscount_raw);

            pquantity = Integer.parseInt(pquantity_raw);
            psupplier = Integer.parseInt(psupplier_raw);
            pcategory = Integer.parseInt(pcategory_raw);

            // Gộp image đường dẫn
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
                    default:
                        path += "Others/";
                        break;
                }
                image += path + img + ",";
            }
            if (image.endsWith(",")) {
                image = image.substring(0, image.length() - 1);
            }

            // Tạo Product
            Product p = new Product();
            p.setName(pname);
            p.setDescribe(pdescribe);
            p.setPrice(pprice);
            p.setDiscount(pdiscount);
            p.setQuantity(pquantity);
            String[] imageArray = image.split(",");
            p.setImage(imageArray);
            p.setClassifyStr(pquantityunit);

            // Parse ngày từ chuỗi
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date releaseDate = sdf.parse(pdate);
                p.setDate(releaseDate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            // Set category & supplier
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            Category category = em.find(Category.class, pcategory);
            Supplier supplier = em.find(Supplier.class, psupplier);
            em.close();
            p.setCategory(category);
            p.setSupplier(supplier);
            p.setStatus(true);

            // Thêm sản phẩm
            EntityManager em2 = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction tx = em2.getTransaction();
            try {
                tx.begin();
                em2.persist(p);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
            } finally {
                em2.close();
            }
            request.setAttribute("mess", "Product Added!");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
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
        return "Add product servlet";
    }// </editor-fold>

}
