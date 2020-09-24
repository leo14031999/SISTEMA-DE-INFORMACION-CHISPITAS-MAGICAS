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
import entidades.Evento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Pagos;
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
public class EventoJpaController implements Serializable {

    public EventoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Evento evento) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (evento.getPagosCollection() == null) {
            evento.setPagosCollection(new ArrayList<Pagos>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Pagos> attachedPagosCollection = new ArrayList<Pagos>();
            for (Pagos pagosCollectionPagosToAttach : evento.getPagosCollection()) {
                pagosCollectionPagosToAttach = em.getReference(pagosCollectionPagosToAttach.getClass(), pagosCollectionPagosToAttach.getIdpagos());
                attachedPagosCollection.add(pagosCollectionPagosToAttach);
            }
            evento.setPagosCollection(attachedPagosCollection);
            em.persist(evento);
            for (Pagos pagosCollectionPagos : evento.getPagosCollection()) {
                Evento oldIdeventoOfPagosCollectionPagos = pagosCollectionPagos.getIdevento();
                pagosCollectionPagos.setIdevento(evento);
                pagosCollectionPagos = em.merge(pagosCollectionPagos);
                if (oldIdeventoOfPagosCollectionPagos != null) {
                    oldIdeventoOfPagosCollectionPagos.getPagosCollection().remove(pagosCollectionPagos);
                    oldIdeventoOfPagosCollectionPagos = em.merge(oldIdeventoOfPagosCollectionPagos);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEvento(evento.getIdevento()) != null) {
                throw new PreexistingEntityException("Evento " + evento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Evento evento) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Evento persistentEvento = em.find(Evento.class, evento.getIdevento());
            Collection<Pagos> pagosCollectionOld = persistentEvento.getPagosCollection();
            Collection<Pagos> pagosCollectionNew = evento.getPagosCollection();
            List<String> illegalOrphanMessages = null;
            for (Pagos pagosCollectionOldPagos : pagosCollectionOld) {
                if (!pagosCollectionNew.contains(pagosCollectionOldPagos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagos " + pagosCollectionOldPagos + " since its idevento field is not nullable.");
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
            evento.setPagosCollection(pagosCollectionNew);
            evento = em.merge(evento);
            for (Pagos pagosCollectionNewPagos : pagosCollectionNew) {
                if (!pagosCollectionOld.contains(pagosCollectionNewPagos)) {
                    Evento oldIdeventoOfPagosCollectionNewPagos = pagosCollectionNewPagos.getIdevento();
                    pagosCollectionNewPagos.setIdevento(evento);
                    pagosCollectionNewPagos = em.merge(pagosCollectionNewPagos);
                    if (oldIdeventoOfPagosCollectionNewPagos != null && !oldIdeventoOfPagosCollectionNewPagos.equals(evento)) {
                        oldIdeventoOfPagosCollectionNewPagos.getPagosCollection().remove(pagosCollectionNewPagos);
                        oldIdeventoOfPagosCollectionNewPagos = em.merge(oldIdeventoOfPagosCollectionNewPagos);
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
                Integer id = evento.getIdevento();
                if (findEvento(id) == null) {
                    throw new NonexistentEntityException("The evento with id " + id + " no longer exists.");
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
            Evento evento;
            try {
                evento = em.getReference(Evento.class, id);
                evento.getIdevento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Pagos> pagosCollectionOrphanCheck = evento.getPagosCollection();
            for (Pagos pagosCollectionOrphanCheckPagos : pagosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Evento (" + evento + ") cannot be destroyed since the Pagos " + pagosCollectionOrphanCheckPagos + " in its pagosCollection field has a non-nullable idevento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(evento);
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

    public List<Evento> findEventoEntities() {
        return findEventoEntities(true, -1, -1);
    }

    public List<Evento> findEventoEntities(int maxResults, int firstResult) {
        return findEventoEntities(false, maxResults, firstResult);
    }

    private List<Evento> findEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Evento.class));
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

    public Evento findEvento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Evento.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Evento> rt = cq.from(Evento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
