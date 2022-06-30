/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.service;

import com.example.hospitalQCMback.entity.ReponseQuestions;
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
public class ReponseQuestionsJpaController implements Serializable {

    public ReponseQuestionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReponseQuestions reponseQuestions) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reponseQuestions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReponseQuestions reponseQuestions) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reponseQuestions = em.merge(reponseQuestions);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reponseQuestions.getIdReponsesQuestions();
                if (findReponseQuestions(id) == null) {
                    throw new NonexistentEntityException("The reponseQuestions with id " + id + " no longer exists.");
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
            ReponseQuestions reponseQuestions;
            try {
                reponseQuestions = em.getReference(ReponseQuestions.class, id);
                reponseQuestions.getIdReponsesQuestions();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reponseQuestions with id " + id + " no longer exists.", enfe);
            }
            em.remove(reponseQuestions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReponseQuestions> findReponseQuestionsEntities() {
        return findReponseQuestionsEntities(true, -1, -1);
    }

    public List<ReponseQuestions> findReponseQuestionsEntities(int maxResults, int firstResult) {
        return findReponseQuestionsEntities(false, maxResults, firstResult);
    }

    private List<ReponseQuestions> findReponseQuestionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReponseQuestions.class));
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

    public ReponseQuestions findReponseQuestions(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReponseQuestions.class, id);
        } finally {
            em.close();
        }
    }

    public int getReponseQuestionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReponseQuestions> rt = cq.from(ReponseQuestions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
