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
import trungndd.entities.Account;
import trungndd.entities.ShoppingCart;
import trungndd.entities.TblOrder;

/**
 *
 * @author Admin
 */
public class TblOrderBLO implements Serializable{

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
    
    public void saveOrder(TblOrder order){
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }
    
    public List getAllOrder(Account email){
        EntityManager em = emf.createEntityManager();
        
        String jpql = "TblOrder.findByEmail";
        
        Query query = em.createNamedQuery(jpql);
        query.setParameter("email", email);
        
        return query.getResultList();
    }
}
