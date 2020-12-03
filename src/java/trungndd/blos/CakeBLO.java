/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.blos;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungndd.entities.Cake;

/**
 *
 * @author Admin
 */
public class CakeBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("J3LP0011_YellowMoonPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public boolean addCake(Cake cake) {
        EntityManager em = emf.createEntityManager();

        Cake checker = em.find(Cake.class, cake.getIdCake());
        if (checker == null) {
            em.getTransaction().begin();
            em.persist(cake);
            em.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }

    public List getAllCakes() {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT c FROM Cake c ORDER BY c.createDate DESC";

        Query query = em.createQuery(jpql);

        return query.getResultList();
    }

    public Cake getCakeById(String idCake) {
        EntityManager em = emf.createEntityManager();

        String jpql = "Cake.findByIdCake";

        Query query = em.createNamedQuery(jpql);
        query.setParameter("idCake", idCake);

        return (Cake) query.getSingleResult();
    }

    public boolean updateCake(Cake cake) {
        EntityManager em = emf.createEntityManager();

        if (em.find(Cake.class, cake.getIdCake()) != null) {
            em.getTransaction().begin();
            em.merge(cake);
            em.getTransaction().commit();
            return true;
        }

        return false;
    }

    public List getAllAvailableCakes() {
        EntityManager em = emf.createEntityManager();
        
        String jpql = "Cake.getAllAvailable";
        Query query = em.createNamedQuery(jpql);

        return query.getResultList();
    }

    public List getAllCakesByCategory(String idCategory, String role) {
        EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT c FROM Cake c WHERE c.idCategory.idCategory = :idCategory ";
        
        if (role != "admin") {
            jpql += "AND c.status = 'active' AND c.quantity > 0";
        }
        
        Query query = em.createQuery(jpql);
        query.setParameter("idCategory", idCategory);
        
        return query.getResultList();
    }

    public List getAllCakesByName(String txtSearch, String role) {
        EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT c FROM Cake c WHERE c.nameCake LIKE :nameCake ";
        
        if (role != "admin") {
            jpql += "AND c.status = 'active' AND c.quantity > 0";
        }
        
        Query query = em.createQuery(jpql);
        query.setParameter("nameCake", "%" + txtSearch + "%");
        
        return query.getResultList();
    }

    public List getAllCakesByPrice(double from, double to, String role) {
        EntityManager em = emf.createEntityManager();
        
        String jpql = "SELECT c FROM Cake c WHERE c.price BETWEEN :from AND :to ";
        
        if (role != "admin") {
            jpql += "AND c.status = 'active' AND c.quantity > 0";
        }
        
        Query query = em.createQuery(jpql);
        query.setParameter("from", from);
        query.setParameter("to", to);
        
        return query.getResultList();
    }
}
