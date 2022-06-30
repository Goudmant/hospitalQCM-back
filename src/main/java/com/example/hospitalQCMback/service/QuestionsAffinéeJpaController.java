/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.service;

import com.example.hospitalQCMback.entity.QuestionsAffinée;
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
public class QuestionsAffinéeJpaController implements Serializable {

    public QuestionsAffinéeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(QuestionsAffinée questionsAffinée) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(questionsAffinée);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(QuestionsAffinée questionsAffinée) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            questionsAffinée = em.merge(questionsAffinée);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = questionsAffinée.getIdQuestionAffinée();
                if (findQuestionsAffinée(id) == null) {
                    throw new NonexistentEntityException("The questionsAffin\u00e9e with id " + id + " no longer exists.");
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
            QuestionsAffinée questionsAffinée;
            try {
                questionsAffinée = em.getReference(QuestionsAffinée.class, id);
                questionsAffinée.getIdQuestionAffinée();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The questionsAffin\u00e9e with id " + id + " no longer exists.", enfe);
            }
            em.remove(questionsAffinée);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<QuestionsAffinée> findQuestionsAffinéeEntities() {
        return findQuestionsAffinéeEntities(true, -1, -1);
    }

    public List<QuestionsAffinée> findQuestionsAffinéeEntities(int maxResults, int firstResult) {
        return findQuestionsAffinéeEntities(false, maxResults, firstResult);
    }

    private List<QuestionsAffinée> findQuestionsAffinéeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(QuestionsAffinée.class));
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

    public QuestionsAffinée findQuestionsAffinée(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(QuestionsAffinée.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuestionsAffinéeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<QuestionsAffinée> rt = cq.from(QuestionsAffinée.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
