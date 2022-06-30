/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.service;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.example.hospitalQCMback.entity.Contact;
import java.util.HashSet;
import java.util.Set;
import com.example.hospitalQCMback.entity.ReponsesUser;
import com.example.hospitalQCMback.entity.UserConnect;
import com.example.hospitalQCMback.service.exceptions.IllegalOrphanException;
import com.example.hospitalQCMback.service.exceptions.NonexistentEntityException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author y.goudmant
 */
@Service
public class UserConnectJpaController implements Serializable {

    public UserConnectJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserConnect userConnect) {
        if (userConnect.getContactSet() == null) {
            userConnect.setContactSet(new HashSet<Contact>());
        }
        if (userConnect.getReponsesUserSet() == null) {
            userConnect.setReponsesUserSet(new HashSet<ReponsesUser>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Contact> attachedContactSet = new HashSet<Contact>();
            for (Contact contactSetContactToAttach : userConnect.getContactSet()) {
                contactSetContactToAttach = em.getReference(contactSetContactToAttach.getClass(), contactSetContactToAttach.getIdContact());
                attachedContactSet.add(contactSetContactToAttach);
            }
            userConnect.setContactSet(attachedContactSet);
            Set<ReponsesUser> attachedReponsesUserSet = new HashSet<ReponsesUser>();
            for (ReponsesUser reponsesUserSetReponsesUserToAttach : userConnect.getReponsesUserSet()) {
                reponsesUserSetReponsesUserToAttach = em.getReference(reponsesUserSetReponsesUserToAttach.getClass(), reponsesUserSetReponsesUserToAttach.getIdReponseUser());
                attachedReponsesUserSet.add(reponsesUserSetReponsesUserToAttach);
            }
            userConnect.setReponsesUserSet(attachedReponsesUserSet);
            em.persist(userConnect);
            for (Contact contactSetContact : userConnect.getContactSet()) {
                UserConnect oldIdUserOfContactSetContact = contactSetContact.getIdUser();
                contactSetContact.setIdUser(userConnect);
                contactSetContact = em.merge(contactSetContact);
                if (oldIdUserOfContactSetContact != null) {
                    oldIdUserOfContactSetContact.getContactSet().remove(contactSetContact);
                    oldIdUserOfContactSetContact = em.merge(oldIdUserOfContactSetContact);
                }
            }
            for (ReponsesUser reponsesUserSetReponsesUser : userConnect.getReponsesUserSet()) {
                UserConnect oldIdUserOfReponsesUserSetReponsesUser = reponsesUserSetReponsesUser.getIdUser();
                reponsesUserSetReponsesUser.setIdUser(userConnect);
                reponsesUserSetReponsesUser = em.merge(reponsesUserSetReponsesUser);
                if (oldIdUserOfReponsesUserSetReponsesUser != null) {
                    oldIdUserOfReponsesUserSetReponsesUser.getReponsesUserSet().remove(reponsesUserSetReponsesUser);
                    oldIdUserOfReponsesUserSetReponsesUser = em.merge(oldIdUserOfReponsesUserSetReponsesUser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserConnect userConnect) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserConnect persistentUserConnect = em.find(UserConnect.class, userConnect.getIdUser());
            Set<Contact> contactSetOld = persistentUserConnect.getContactSet();
            Set<Contact> contactSetNew = userConnect.getContactSet();
            Set<ReponsesUser> reponsesUserSetOld = persistentUserConnect.getReponsesUserSet();
            Set<ReponsesUser> reponsesUserSetNew = userConnect.getReponsesUserSet();
            List<String> illegalOrphanMessages = null;
            for (Contact contactSetOldContact : contactSetOld) {
                if (!contactSetNew.contains(contactSetOldContact)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contact " + contactSetOldContact + " since its idUser field is not nullable.");
                }
            }
            for (ReponsesUser reponsesUserSetOldReponsesUser : reponsesUserSetOld) {
                if (!reponsesUserSetNew.contains(reponsesUserSetOldReponsesUser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReponsesUser " + reponsesUserSetOldReponsesUser + " since its idUser field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Contact> attachedContactSetNew = new HashSet<Contact>();
            for (Contact contactSetNewContactToAttach : contactSetNew) {
                contactSetNewContactToAttach = em.getReference(contactSetNewContactToAttach.getClass(), contactSetNewContactToAttach.getIdContact());
                attachedContactSetNew.add(contactSetNewContactToAttach);
            }
            contactSetNew = attachedContactSetNew;
            userConnect.setContactSet(contactSetNew);
            Set<ReponsesUser> attachedReponsesUserSetNew = new HashSet<ReponsesUser>();
            for (ReponsesUser reponsesUserSetNewReponsesUserToAttach : reponsesUserSetNew) {
                reponsesUserSetNewReponsesUserToAttach = em.getReference(reponsesUserSetNewReponsesUserToAttach.getClass(), reponsesUserSetNewReponsesUserToAttach.getIdReponseUser());
                attachedReponsesUserSetNew.add(reponsesUserSetNewReponsesUserToAttach);
            }
            reponsesUserSetNew = attachedReponsesUserSetNew;
            userConnect.setReponsesUserSet(reponsesUserSetNew);
            userConnect = em.merge(userConnect);
            for (Contact contactSetNewContact : contactSetNew) {
                if (!contactSetOld.contains(contactSetNewContact)) {
                    UserConnect oldIdUserOfContactSetNewContact = contactSetNewContact.getIdUser();
                    contactSetNewContact.setIdUser(userConnect);
                    contactSetNewContact = em.merge(contactSetNewContact);
                    if (oldIdUserOfContactSetNewContact != null && !oldIdUserOfContactSetNewContact.equals(userConnect)) {
                        oldIdUserOfContactSetNewContact.getContactSet().remove(contactSetNewContact);
                        oldIdUserOfContactSetNewContact = em.merge(oldIdUserOfContactSetNewContact);
                    }
                }
            }
            for (ReponsesUser reponsesUserSetNewReponsesUser : reponsesUserSetNew) {
                if (!reponsesUserSetOld.contains(reponsesUserSetNewReponsesUser)) {
                    UserConnect oldIdUserOfReponsesUserSetNewReponsesUser = reponsesUserSetNewReponsesUser.getIdUser();
                    reponsesUserSetNewReponsesUser.setIdUser(userConnect);
                    reponsesUserSetNewReponsesUser = em.merge(reponsesUserSetNewReponsesUser);
                    if (oldIdUserOfReponsesUserSetNewReponsesUser != null && !oldIdUserOfReponsesUserSetNewReponsesUser.equals(userConnect)) {
                        oldIdUserOfReponsesUserSetNewReponsesUser.getReponsesUserSet().remove(reponsesUserSetNewReponsesUser);
                        oldIdUserOfReponsesUserSetNewReponsesUser = em.merge(oldIdUserOfReponsesUserSetNewReponsesUser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userConnect.getIdUser();
                if (findUserConnect(id) == null) {
                    throw new NonexistentEntityException("The userConnect with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserConnect userConnect;
            try {
                userConnect = em.getReference(UserConnect.class, id);
                userConnect.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userConnect with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Contact> contactSetOrphanCheck = userConnect.getContactSet();
            for (Contact contactSetOrphanCheckContact : contactSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserConnect (" + userConnect + ") cannot be destroyed since the Contact " + contactSetOrphanCheckContact + " in its contactSet field has a non-nullable idUser field.");
            }
            Set<ReponsesUser> reponsesUserSetOrphanCheck = userConnect.getReponsesUserSet();
            for (ReponsesUser reponsesUserSetOrphanCheckReponsesUser : reponsesUserSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserConnect (" + userConnect + ") cannot be destroyed since the ReponsesUser " + reponsesUserSetOrphanCheckReponsesUser + " in its reponsesUserSet field has a non-nullable idUser field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(userConnect);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserConnect> findUserConnectEntities() {
        return findUserConnectEntities(true, -1, -1);
    }

    public List<UserConnect> findUserConnectEntities(int maxResults, int firstResult) {
        return findUserConnectEntities(false, maxResults, firstResult);
    }

    private List<UserConnect> findUserConnectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserConnect.class));
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

    public UserConnect findUserConnect(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserConnect.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserConnectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserConnect> rt = cq.from(UserConnect.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
