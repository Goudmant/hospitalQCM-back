/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.service;

import com.example.hospitalQCMback.entity.ResultatValeurQcm;
import com.example.hospitalQCMback.service.exceptions.NonexistentEntityException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author y.goudmant
 */
@Service
public class ResultatValeurQcmJpaController implements Serializable {

    public ResultatValeurQcmJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResultatValeurQcm resultatValeurQcm) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(resultatValeurQcm);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResultatValeurQcm resultatValeurQcm) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            resultatValeurQcm = em.merge(resultatValeurQcm);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultatValeurQcm.getIdResultatValeur();
                if (findResultatValeurQcm(id) == null) {
                    throw new NonexistentEntityException("The resultatValeurQcm with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResultatValeurQcm resultatValeurQcm;
            try {
                resultatValeurQcm = em.getReference(ResultatValeurQcm.class, id);
                resultatValeurQcm.getIdResultatValeur();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultatValeurQcm with id " + id + " no longer exists.", enfe);
            }
            em.remove(resultatValeurQcm);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResultatValeurQcm> findResultatValeurQcmEntities() {
        return findResultatValeurQcmEntities(true, -1, -1);
    }

    public List<ResultatValeurQcm> findResultatValeurQcmEntities(int maxResults, int firstResult) {
        return findResultatValeurQcmEntities(false, maxResults, firstResult);
    }

    private List<ResultatValeurQcm> findResultatValeurQcmEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResultatValeurQcm.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ResultatValeurQcm findResultatValeurQcm(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResultatValeurQcm.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultatValeurQcmCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResultatValeurQcm> rt = cq.from(ResultatValeurQcm.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
