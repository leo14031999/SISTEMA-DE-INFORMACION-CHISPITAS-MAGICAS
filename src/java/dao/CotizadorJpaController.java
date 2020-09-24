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
import entidades.Cotizador;
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
public class CotizadorJpaController implements Serializable {

    public CotizadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cotizador cotizador) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (cotizador.getPagosCollection() == null) {
            cotizador.setPagosCollection(new ArrayList<Pagos>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Pagos> attachedPagosCollection = new ArrayList<Pagos>();
            for (Pagos pagosCollectionPagosToAttach : cotizador.getPagosCollection()) {
                pagosCollectionPagosToAttach = em.getReference(pagosCollectionPagosToAttach.getClass(), pagosCollectionPagosToAttach.getIdpagos());
                attachedPagosCollection.add(pagosCollectionPagosToAttach);
            }
            cotizador.setPagosCollection(attachedPagosCollection);
            em.persist(cotizador);
            for (Pagos pagosCollectionPagos : cotizador.getPagosCollection()) {
                Cotizador oldIdcotizadorOfPagosCollectionPagos = pagosCollectionPagos.getIdcotizador();
                pagosCollectionPagos.setIdcotizador(cotizador);
                pagosCollectionPagos = em.merge(pagosCollectionPagos);
                if (oldIdcotizadorOfPagosCollectionPagos != null) {
                    oldIdcotizadorOfPagosCollectionPagos.getPagosCollection().remove(pagosCollectionPagos);
                    oldIdcotizadorOfPagosCollectionPagos = em.merge(oldIdcotizadorOfPagosCollectionPagos);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCotizador(cotizador.getIdcotizador()) != null) {
                throw new PreexistingEntityException("Cotizador " + cotizador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cotizador cotizador) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cotizador persistentCotizador = em.find(Cotizador.class, cotizador.getIdcotizador());
            Collection<Pagos> pagosCollectionOld = persistentCotizador.getPagosCollection();
            Collection<Pagos> pagosCollectionNew = cotizador.getPagosCollection();
            List<String> illegalOrphanMessages = null;
            for (Pagos pagosCollectionOldPagos : pagosCollectionOld) {
                if (!pagosCollectionNew.contains(pagosCollectionOldPagos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagos " + pagosCollectionOldPagos + " since its idcotizador field is not nullable.");
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
            cotizador.setPagosCollection(pagosCollectionNew);
            cotizador = em.merge(cotizador);
            for (Pagos pagosCollectionNewPagos : pagosCollectionNew) {
                if (!pagosCollectionOld.contains(pagosCollectionNewPagos)) {
                    Cotizador oldIdcotizadorOfPagosCollectionNewPagos = pagosCollectionNewPagos.getIdcotizador();
                    pagosCollectionNewPagos.setIdcotizador(cotizador);
                    pagosCollectionNewPagos = em.merge(pagosCollectionNewPagos);
                    if (oldIdcotizadorOfPagosCollectionNewPagos != null && !oldIdcotizadorOfPagosCollectionNewPagos.equals(cotizador)) {
                        oldIdcotizadorOfPagosCollectionNewPagos.getPagosCollection().remove(pagosCollectionNewPagos);
                        oldIdcotizadorOfPagosCollectionNewPagos = em.merge(oldIdcotizadorOfPagosCollectionNewPagos);
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
                Integer id = cotizador.getIdcotizador();
                if (findCotizador(id) == null) {
                    throw new NonexistentEntityException("The cotizador with id " + id + " no longer exists.");
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
            Cotizador cotizador;
            try {
                cotizador = em.getReference(Cotizador.class, id);
                cotizador.getIdcotizador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cotizador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Pagos> pagosCollectionOrphanCheck = cotizador.getPagosCollection();
            for (Pagos pagosCollectionOrphanCheckPagos : pagosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cotizador (" + cotizador + ") cannot be destroyed since the Pagos " + pagosCollectionOrphanCheckPagos + " in its pagosCollection field has a non-nullable idcotizador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cotizador);
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

    public List<Cotizador> findCotizadorEntities() {
        return findCotizadorEntities(true, -1, -1);
    }

    public List<Cotizador> findCotizadorEntities(int maxResults, int firstResult) {
        return findCotizadorEntities(false, maxResults, firstResult);
    }

    private List<Cotizador> findCotizadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cotizador.class));
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

    public Cotizador findCotizador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cotizador.class, id);
        } finally {
            em.close();
        }
    }

    public int getCotizadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cotizador> rt = cq.from(Cotizador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
