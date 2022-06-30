/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.service;

import com.example.hospitalQCMback.entity.ReponsesUser;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.example.hospitalQCMback.entity.UserConnect;
import com.example.hospitalQCMback.service.exceptions.NonexistentEntityException;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author y.goudmant
 */
@Service
public class ReponsesUserJpaController implements Serializable {

    public ReponsesUserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReponsesUser reponsesUser) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserConnect idUser = reponsesUser.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                reponsesUser.setIdUser(idUser);
            }
            em.persist(reponsesUser);
            if (idUser != null) {
                idUser.getReponsesUserSet().add(reponsesUser);
                idUser = em.merge(idUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReponsesUser reponsesUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReponsesUser persistentReponsesUser = em.find(ReponsesUser.class, reponsesUser.getIdReponseUser());
            UserConnect idUserOld = persistentReponsesUser.getIdUser();
            UserConnect idUserNew = reponsesUser.getIdUser();
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                reponsesUser.setIdUser(idUserNew);
            }
            reponsesUser = em.merge(reponsesUser);
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getReponsesUserSet().remove(reponsesUser);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getReponsesUserSet().add(reponsesUser);
                idUserNew = em.merge(idUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reponsesUser.getIdReponseUser();
                if (findReponsesUser(id) == null) {
                    throw new NonexistentEntityException("The reponsesUser with id " + id + " no longer exists.");
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
            ReponsesUser reponsesUser;
            try {
                reponsesUser = em.getReference(ReponsesUser.class, id);
                reponsesUser.getIdReponseUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reponsesUser with id " + id + " no longer exists.", enfe);
            }
            UserConnect idUser = reponsesUser.getIdUser();
            if (idUser != null) {
                idUser.getReponsesUserSet().remove(reponsesUser);
                idUser = em.merge(idUser);
            }
            em.remove(reponsesUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReponsesUser> findReponsesUserEntities() {
        return findReponsesUserEntities(true, -1, -1);
    }

    public List<ReponsesUser> findReponsesUserEntities(int maxResults, int firstResult) {
        return findReponsesUserEntities(false, maxResults, firstResult);
    }

    private List<ReponsesUser> findReponsesUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReponsesUser.class));
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

    public ReponsesUser findReponsesUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReponsesUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getReponsesUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReponsesUser> rt = cq.from(ReponsesUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
