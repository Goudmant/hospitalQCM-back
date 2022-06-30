/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.service;

import com.example.hospitalQCMback.entity.Medecin;
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
public class MedecinJpaController implements Serializable {

    public MedecinJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medecin medecin) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(medecin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medecin medecin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            medecin = em.merge(medecin);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medecin.getIdMedecin();
                if (findMedecin(id) == null) {
                    throw new NonexistentEntityException("The medecin with id " + id + " no longer exists.");
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
            Medecin medecin;
            try {
                medecin = em.getReference(Medecin.class, id);
                medecin.getIdMedecin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medecin with id " + id + " no longer exists.", enfe);
            }
            em.remove(medecin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medecin> findMedecinEntities() {
        return findMedecinEntities(true, -1, -1);
    }

    public List<Medecin> findMedecinEntities(int maxResults, int firstResult) {
        return findMedecinEntities(false, maxResults, firstResult);
    }

    private List<Medecin> findMedecinEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medecin.class));
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

    public Medecin findMedecin(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medecin.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedecinCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medecin> rt = cq.from(Medecin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
