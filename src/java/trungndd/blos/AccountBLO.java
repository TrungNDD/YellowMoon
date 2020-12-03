/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.blos;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungndd.entities.Account;

/**
 *
 * @author Admin
 */
public class AccountBLO implements Serializable {

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

    public Account checkLogin(String email, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT a FROM Account a WHERE a.email = :email AND a.password = :password";

        Query query = em.createQuery(jpql);
        query.setParameter("email", email);
        query.setParameter("password", password);

        return (Account) query.getSingleResult();
    }

    public Account checkLoginAsGoogle(String email, String password, String name) {
        EntityManager em = emf.createEntityManager();

        Account account = em.find(Account.class, email);
        if (account == null) {
            account = new Account(email, "member");
            account.setPassword(password);
            account.setFullname(name);
            account.setStatus("active");

            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
        }

        return account;
    }
}
