/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Lenovo
 */
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.*;
import model.Spending;
import model.User;
import util.JPAUtil;

public class UserDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PerfumeShopPU");
    private EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void insertUser(String userName, String fullName, String password, int roleID, String email, String birthdate, String phone) {
    em.getTransaction().begin();
    try {
        User existingUser = em.find(User.class, userName);
        if (existingUser != null) {
            if (existingUser.getStatus() == 0) {
                // Khôi phục tài khoản đã bị xóa
                existingUser.setFullName(fullName);
                existingUser.setPassword(password);
                existingUser.setRoleID(roleID);
                existingUser.setEmail(email);
                existingUser.setBirthdate(birthdate);
                existingUser.setPhone(phone);
                existingUser.setImage("images/users/user.png"); // giữ nguyên ảnh mặc định
                existingUser.setAddress(""); // địa chỉ rỗng
                existingUser.setStatus(1);
                em.merge(existingUser);
            } else {
                // Nếu đã tồn tại và đang active, không làm gì
                em.getTransaction().rollback();
                return;
            }
        } else {
            // Tài khoản mới hoàn toàn
            User user = new User();
            user.setUserName(userName);
            user.setFullName(fullName);
            user.setPassword(password);
            user.setRoleID(roleID);
            user.setEmail(email);
            user.setBirthdate(birthdate);
            user.setPhone(phone);
            user.setImage("images/users/user.png"); // ảnh mặc định
            user.setStatus(1);
            user.setAddress(""); // address mặc định
            em.persist(user);
        }
        em.getTransaction().commit();
    } catch (Exception e) {
        e.printStackTrace();
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}


    public User check(String username, String password) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.userName = :username AND u.password = :password AND u.status = 1", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u WHERE u.status = 1 ORDER BY u.roleID", User.class).getResultList();
    }

    public boolean checkUserNameDuplicate(String username) {
    try {
        User u = em.find(User.class, username);
        return u != null && u.getStatus() == 1;
    } catch (Exception e) {
        return false;
    }
}


    public void updateImage(String image, String userName) {
        em.getTransaction().begin();
        User user = em.find(User.class, userName);
        if (user != null) {
            user.setImage(image);
            em.merge(user);
        }
        em.getTransaction().commit();
    }

    public void update(String name, String address, String phone, String email, String dob, String userName) {
        em.getTransaction().begin();
        User user = em.find(User.class, userName);
        if (user != null) {
            user.setFullName(name);
            user.setAddress(address);
            user.setPhone(phone);
            user.setEmail(email);
            user.setBirthdate(dob);
            em.merge(user);
        }
        em.getTransaction().commit();
    }

    public void update(User user) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            em.flush(); // ép Hibernate ghi xuống DB
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }
    }

    public User getUserByUserName(String userName) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.userName = :username AND u.status = 1", User.class)
                    .setParameter("username", userName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

//    public void insertUser(User user) {
//        em.getTransaction().begin();
//        em.persist(user);
//        em.getTransaction().commit();
//    }

    public void deleteUser(String username) {
        em.getTransaction().begin();
        User user = em.find(User.class, username);
        if (user != null) {
            user.setStatus(0);
            em.merge(user);
        }
        em.getTransaction().commit();
    }

    public List<Spending> getTop5Customers() {
        List<Spending> list = new ArrayList<>();
        try {
            String jpql = "SELECT new model.Spending(o.user, SUM(o.total)) "
                    + "FROM Order o "
                    + "WHERE o.status = true AND o.user.status = true "
                    + "GROUP BY o.user "
                    + "ORDER BY SUM(o.total) DESC";
            list = em.createQuery(jpql, Spending.class)
                    .setMaxResults(5)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> getUsersBySearchName(String txtSearch) {
        return em.createQuery("SELECT u FROM User u WHERE u.userName LIKE :txt AND u.status = 1", User.class)
                .setParameter("txt", "%" + txtSearch + "%")
                .getResultList();
    }

    public void changePassword(String username, String newPass) {
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            User user = em.find(User.class, username);
            if (user != null) {
                user.setPassword(newPass);
                em.merge(user);
            }
            tran.commit();
        } catch (Exception e) {
            if (tran.isActive()) {
                tran.rollback();
            }
            e.printStackTrace();
        }
    }

    public String checkEmailExist(String email) {
        List<String> result = em.createQuery(
                "SELECT u.email FROM User u WHERE u.email = :email AND u.status = 1", String.class)
                .setParameter("email", email)
                .getResultList();

        // Trả về email đầu tiên nếu có, nếu không thì null
        return result.isEmpty() ? null : result.get(0);
    }

    public String getUserNameByEmail(String email) {
        try {
            return em.createQuery("SELECT u.userName FROM User u WHERE u.email = :email AND u.status = 1", String.class)
                    .setParameter("email", email)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void updatePassByUserName(String pass, String username) {
        em.getTransaction().begin();
        User user = em.find(User.class, username);
        if (user != null) {
            user.setPassword(pass);
            em.merge(user);
        }
        em.getTransaction().commit();
    }

    public int countAllUser() {
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.status = 1", Long.class).getSingleResult();
        return count.intValue();
    }

    public User getUserByUsernameAndEmail(String username, String email) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.userName = :username AND u.email = :email", User.class)
                    .setParameter("username", username)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean insertUser(User user) {
    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    try {
        em.getTransaction().begin();
        User existingUser = em.find(User.class, user.getUserName());

        if (existingUser != null) {
            if (existingUser.getStatus() == 0) {
                // Tài khoản tồn tại nhưng đã bị deactive → kích hoạt lại
                existingUser.setStatus(1);
                existingUser.setFullName(user.getFullName());
                existingUser.setPassword(user.getPassword());
                existingUser.setEmail(user.getEmail());
                existingUser.setAddress(user.getAddress());
                existingUser.setPhone(user.getPhone());
                existingUser.setRoleID(user.getRoleID());
                existingUser.setImage(user.getImage());
                existingUser.setBirthdate(user.getBirthdate());
                em.merge(existingUser);
                em.getTransaction().commit();
                return true;
            } else {
                // Tài khoản đã tồn tại và đang active → không cho thêm
                em.getTransaction().rollback();
                return false;
            }
        } else {
            // Thêm mới
            em.persist(user);
            em.getTransaction().commit();
            return true;
        }

    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
        return false;
    } finally {
        em.close();
    }
}
    public boolean isEmailExist(String email) {
    try {
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
        query.setParameter("email", email);
        Long count = query.getSingleResult();
        return count > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    }

