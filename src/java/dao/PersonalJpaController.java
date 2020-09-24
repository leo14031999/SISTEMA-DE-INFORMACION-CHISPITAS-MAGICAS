/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Pagos;
import entidades.Personal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Administrador
 */
public class PersonalJpaController implements Serializable {

    public PersonalJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personal personal) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (personal.getPagosCollection() == null) {
            personal.setPagosCollection(new ArrayList<Pagos>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Pagos> attachedPagosCollection = new ArrayList<Pagos>();
            for (Pagos pagosCollectionPagosToAttach : personal.getPagosCollection()) {
                pagosCollectionPagosToAttach = em.getReference(pagosCollectionPagosToAttach.getClass(), pagosCollectionPagosToAttach.getIdpagos());
                attachedPagosCollection.add(pagosCollectionPagosToAttach);
            }
            personal.setPagosCollection(attachedPagosCollection);
            em.persist(personal);
            for (Pagos pagosCollectionPagos : personal.getPagosCollection()) {
                Personal oldIdpersonalOfPagosCollectionPagos = pagosCollectionPagos.getIdpersonal();
                pagosCollectionPagos.setIdpersonal(personal);
                pagosCollectionPagos = em.merge(pagosCollectionPagos);
                if (oldIdpersonalOfPagosCollectionPagos != null) {
                    oldIdpersonalOfPagosCollectionPagos.getPagosCollection().remove(pagosCollectionPagos);
                    oldIdpersonalOfPagosCollectionPagos = em.merge(oldIdpersonalOfPagosCollectionPagos);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPersonal(personal.getIdpersonal()) != null) {
                throw new PreexistingEntityException("Personal " + personal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personal personal) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personal persistentPersonal = em.find(Personal.class, personal.getIdpersonal());
            Collection<Pagos> pagosCollectionOld = persistentPersonal.getPagosCollection();
            Collection<Pagos> pagosCollectionNew = personal.getPagosCollection();
            List<String> illegalOrphanMessages = null;
            for (Pagos pagosCollectionOldPagos : pagosCollectionOld) {
                if (!pagosCollectionNew.contains(pagosCollectionOldPagos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagos " + pagosCollectionOldPagos + " since its idpersonal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Pagos> attachedPagosCollectionNew = new ArrayList<Pagos>();
            for (Pagos pagosCollectionNewPagosToAttach : pagosCollectionNew) {
                pagosCollectionNewPagosToAttach = em.getReference(pagosCollectionNewPagosToAttach.getClass(), pagosCollectionNewPagosToAttach.getIdpagos());
                attachedPagosCollectionNew.add(pagosCollectionNewPagosToAttach);
            }
            pagosCollectionNew = attachedPagosCollectionNew;
            personal.setPagosCollection(pagosCollectionNew);
            personal = em.merge(personal);
            for (Pagos pagosCollectionNewPagos : pagosCollectionNew) {
                if (!pagosCollectionOld.contains(pagosCollectionNewPagos)) {
                    Personal oldIdpersonalOfPagosCollectionNewPagos = pagosCollectionNewPagos.getIdpersonal();
                    pagosCollectionNewPagos.setIdpersonal(personal);
                    pagosCollectionNewPagos = em.merge(pagosCollectionNewPagos);
                    if (oldIdpersonalOfPagosCollectionNewPagos != null && !oldIdpersonalOfPagosCollectionNewPagos.equals(personal)) {
                        oldIdpersonalOfPagosCollectionNewPagos.getPagosCollection().remove(pagosCollectionNewPagos);
                        oldIdpersonalOfPagosCollectionNewPagos = em.merge(oldIdpersonalOfPagosCollectionNewPagos);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personal.getIdpersonal();
                if (findPersonal(id) == null) {
                    throw new NonexistentEntityException("The personal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personal personal;
            try {
                personal = em.getReference(Personal.class, id);
                personal.getIdpersonal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Pagos> pagosCollectionOrphanCheck = personal.getPagosCollection();
            for (Pagos pagosCollectionOrphanCheckPagos : pagosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personal (" + personal + ") cannot be destroyed since the Pagos " + pagosCollectionOrphanCheckPagos + " in its pagosCollection field has a non-nullable idpersonal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(personal);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personal> findPersonalEntities() {
        return findPersonalEntities(true, -1, -1);
    }

    public List<Personal> findPersonalEntities(int maxResults, int firstResult) {
        return findPersonalEntities(false, maxResults, firstResult);
    }

    private List<Personal> findPersonalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personal.class));
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

    public Personal findPersonal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personal> rt = cq.from(Personal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
