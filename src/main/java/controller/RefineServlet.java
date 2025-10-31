package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;
import util.JPAUtil;

@WebServlet(name = "RefineServlet", urlPatterns = {"/refine"})
public class RefineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        int page = 1, numPerPage = 12;
        String xpage = request.getParameter("page");
        if (xpage != null) {
            page = Integer.parseInt(xpage);
        }
        int start = (page - 1) * numPerPage;

        // Tham số lọc
        String categoryIdRaw = request.getParameter("cid_refine");
        String priceFromRaw = request.getParameter("pricefrom");
        String priceToRaw = request.getParameter("priceto");
        String numberStarRaw = request.getParameter("numberStar");
        String nameSearch = request.getParameter("nameSearch");
        String discountRaw = request.getParameter("discount");

        // Tạo JPQL động
        String jpql = "SELECT p FROM Product p WHERE p.status = true";
        List<String> conditions = new ArrayList<>();
        if (categoryIdRaw != null && !categoryIdRaw.isEmpty()) {
            conditions.add("p.category.id = :categoryId");
        }
        if (priceFromRaw != null && !priceFromRaw.isEmpty()) {
            conditions.add("p.price >= :priceFrom");
        }
        if (priceToRaw != null && !priceToRaw.isEmpty()) {
            conditions.add("p.price <= :priceTo");
        }
        if (numberStarRaw != null && !numberStarRaw.isEmpty()) {
            conditions.add("p.starRating >= :star");
        }
        if (discountRaw != null && !discountRaw.isEmpty()) {
            conditions.add("p.discount >= :discount");
        }
        if (nameSearch != null && !nameSearch.isEmpty()) {
            conditions.add("LOWER(p.name) LIKE :nameSearch");
        }

        if (!conditions.isEmpty()) {
            jpql += " AND " + String.join(" AND ", conditions);
        }
        jpql += " ORDER BY p.id";

        TypedQuery<Product> query = em.createQuery(jpql, Product.class);

        // Set tham số cho query
        if (categoryIdRaw != null && !categoryIdRaw.isEmpty()) {
            query.setParameter("categoryId", Integer.parseInt(categoryIdRaw));
        }
        if (priceFromRaw != null && !priceFromRaw.isEmpty()) {
            query.setParameter("priceFrom", Double.parseDouble(priceFromRaw));
        }
        if (priceToRaw != null && !priceToRaw.isEmpty()) {
            query.setParameter("priceTo", Double.parseDouble(priceToRaw));
        }
        if (numberStarRaw != null && !numberStarRaw.isEmpty()) {
            query.setParameter("star", Integer.parseInt(numberStarRaw));
        }
        if (discountRaw != null && !discountRaw.isEmpty()) {
            query.setParameter("discount", Double.parseDouble(discountRaw));
        }
        if (nameSearch != null && !nameSearch.isEmpty()) {
            query.setParameter("nameSearch", "%" + nameSearch.toLowerCase() + "%");
        }

        // Phân trang
        int size = query.getResultList().size();
        int numberpage = (size + numPerPage - 1) / numPerPage;
        query.setFirstResult(start);
        query.setMaxResults(numPerPage);
        List<Product> productPage = query.getResultList();

        // Load danh mục
        List<Category> categories = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();

        // Xử lý mảng checked category
        Boolean[] chid = new Boolean[categories.size() + 1];
        for (int i = 0; i <= categories.size(); i++) {
            chid[i] = false;
        }
        if (categoryIdRaw != null && !categoryIdRaw.isEmpty()) {
            try {
                int cid = Integer.parseInt(categoryIdRaw);
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getId() == cid) {
                        chid[i + 1] = true;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            chid[0] = true; // chọn "ALL"
        }

        // Gửi dữ liệu sang JSP
        request.setAttribute("productPage", productPage);
        request.setAttribute("category", categories);
        request.setAttribute("page", page);
        request.setAttribute("numberpage", numberpage);
        request.setAttribute("chid", chid);

        request.getRequestDispatcher("refine.jsp").forward(request, response);
        em.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Refine products by filters";
    }
}