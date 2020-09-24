/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Cliente;
import entidades.Cotizador;
import entidades.Evento;
import entidades.Pagos;
import entidades.Personal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Administrador
 */
public class PagosJpaController implements Serializable {

    public PagosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagos pagos) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cliente idcliente = pagos.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getIdcliente());
                pagos.setIdcliente(idcliente);
            }
            Cotizador idcotizador = pagos.getIdcotizador();
            if (idcotizador != null) {
                idcotizador = em.getReference(idcotizador.getClass(), idcotizador.getIdcotizador());
                pagos.setIdcotizador(idcotizador);
            }
            Evento idevento = pagos.getIdevento();
            if (idevento != null) {
                idevento = em.getReference(idevento.getClass(), idevento.getIdevento());
                pagos.setIdevento(idevento);
            }
            Personal idpersonal = pagos.getIdpersonal();
            if (idpersonal != null) {
                idpersonal = em.getReference(idpersonal.getClass(), idpersonal.getIdpersonal());
                pagos.setIdpersonal(idpersonal);
            }
            em.persist(pagos);
            if (idcliente != null) {
                idcliente.getPagosCollection().add(pagos);
                idcliente = em.merge(idcliente);
            }
            if (idcotizador != null) {
                idcotizador.getPagosCollection().add(pagos);
                idcotizador = em.merge(idcotizador);
            }
            if (idevento != null) {
                idevento.getPagosCollection().add(pagos);
                idevento = em.merge(idevento);
            }
            if (idpersonal != null) {
                idpersonal.getPagosCollection().add(pagos);
                idpersonal = em.merge(idpersonal);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPagos(pagos.getIdpagos()) != null) {
                throw new PreexistingEntityException("Pagos " + pagos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagos pagos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pagos persistentPagos = em.find(Pagos.class, pagos.getIdpagos());
            Cliente idclienteOld = persistentPagos.getIdcliente();
            Cliente idclienteNew = pagos.getIdcliente();
            Cotizador idcotizadorOld = persistentPagos.getIdcotizador();
            Cotizador idcotizadorNew = pagos.getIdcotizador();
            Evento ideventoOld = persistentPagos.getIdevento();
            Evento ideventoNew = pagos.getIdevento();
            Personal idpersonalOld = persistentPagos.getIdpersonal();
            Personal idpersonalNew = pagos.getIdpersonal();
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdcliente());
                pagos.setIdcliente(idclienteNew);
            }
            if (idcotizadorNew != null) {
                idcotizadorNew = em.getReference(idcotizadorNew.getClass(), idcotizadorNew.getIdcotizador());
                pagos.setIdcotizador(idcotizadorNew);
            }
            if (ideventoNew != null) {
                ideventoNew = em.getReference(ideventoNew.getClass(), ideventoNew.getIdevento());
                pagos.setIdevento(ideventoNew);
            }
            if (idpersonalNew != null) {
                idpersonalNew = em.getReference(idpersonalNew.getClass(), idpersonalNew.getIdpersonal());
                pagos.setIdpersonal(idpersonalNew);
            }
            pagos = em.merge(pagos);
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getPagosCollection().remove(pagos);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getPagosCollection().add(pagos);
                idclienteNew = em.merge(idclienteNew);
            }
            if (idcotizadorOld != null && !idcotizadorOld.equals(idcotizadorNew)) {
                idcotizadorOld.getPagosCollection().remove(pagos);
                idcotizadorOld = em.merge(idcotizadorOld);
            }
            if (idcotizadorNew != null && !idcotizadorNew.equals(idcotizadorOld)) {
                idcotizadorNew.getPagosCollection().add(pagos);
                idcotizadorNew = em.merge(idcotizadorNew);
            }
            if (ideventoOld != null && !ideventoOld.equals(ideventoNew)) {
                ideventoOld.getPagosCollection().remove(pagos);
                ideventoOld = em.merge(ideventoOld);
            }
            if (ideventoNew != null && !ideventoNew.equals(ideventoOld)) {
                ideventoNew.getPagosCollection().add(pagos);
                ideventoNew = em.merge(ideventoNew);
            }
            if (idpersonalOld != null && !idpersonalOld.equals(idpersonalNew)) {
                idpersonalOld.getPagosCollection().remove(pagos);
                idpersonalOld = em.merge(idpersonalOld);
            }
            if (idpersonalNew != null && !idpersonalNew.equals(idpersonalOld)) {
                idpersonalNew.getPagosCollection().add(pagos);
                idpersonalNew = em.merge(idpersonalNew);
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
                Integer id = pagos.getIdpagos();
                if (findPagos(id) == null) {
                    throw new NonexistentEntityException("The pagos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pagos pagos;
            try {
                pagos = em.getReference(Pagos.class, id);
                pagos.getIdpagos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagos with id " + id + " no longer exists.", enfe);
            }
            Cliente idcliente = pagos.getIdcliente();
            if (idcliente != null) {
                idcliente.getPagosCollection().remove(pagos);
                idcliente = em.merge(idcliente);
            }
            Cotizador idcotizador = pagos.getIdcotizador();
            if (idcotizador != null) {
                idcotizador.getPagosCollection().remove(pagos);
                idcotizador = em.merge(idcotizador);
            }
            Evento idevento = pagos.getIdevento();
            if (idevento != null) {
                idevento.getPagosCollection().remove(pagos);
                idevento = em.merge(idevento);
            }
            Personal idpersonal = pagos.getIdpersonal();
            if (idpersonal != null) {
                idpersonal.getPagosCollection().remove(pagos);
                idpersonal = em.merge(idpersonal);
            }
            em.remove(pagos);
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

    public List<Pagos> findPagosEntities() {
        return findPagosEntities(true, -1, -1);
    }

    public List<Pagos> findPagosEntities(int maxResults, int firstResult) {
        return findPagosEntities(false, maxResults, firstResult);
    }

    private List<Pagos> findPagosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagos.class));
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

    public Pagos findPagos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagos> rt = cq.from(Pagos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
