/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.sessions;

import entidades.Pagos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class PagosFacade extends AbstractFacade<Pagos> {

    @PersistenceContext(unitName = "CHISPITASMAGICASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagosFacade() {
        super(Pagos.class);
    }
    
}
