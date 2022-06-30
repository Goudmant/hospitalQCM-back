/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.service;

import com.example.hospitalQCMback.entity.ReponseFinal;
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
public class ReponseFinalJpaController implements Serializable {

    public ReponseFinalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReponseFinal reponseFinal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reponseFinal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReponseFinal reponseFinal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reponseFinal = em.merge(reponseFinal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reponseFinal.getIdReponseFinal();
                if (findReponseFinal(id) == null) {
                    throw new NonexistentEntityException("The reponseFinal with id " + id + " no longer exists.");
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
            ReponseFinal reponseFinal;
            try {
                reponseFinal = em.getReference(ReponseFinal.class, id);
                reponseFinal.getIdReponseFinal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reponseFinal with id " + id + " no longer exists.", enfe);
            }
            em.remove(reponseFinal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReponseFinal> findReponseFinalEntities() {
        return findReponseFinalEntities(true, -1, -1);
    }

    public List<ReponseFinal> findReponseFinalEntities(int maxResults, int firstResult) {
        return findReponseFinalEntities(false, maxResults, firstResult);
    }

    private List<ReponseFinal> findReponseFinalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReponseFinal.class));
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

    public ReponseFinal findReponseFinal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReponseFinal.class, id);
        } finally {
            em.close();
        }
    }

    public int getReponseFinalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReponseFinal> rt = cq.from(ReponseFinal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
