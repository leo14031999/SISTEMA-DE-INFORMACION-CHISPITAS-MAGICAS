/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "cotizador")
@NamedQueries({
    @NamedQuery(name = "Cotizador.findAll", query = "SELECT c FROM Cotizador c"),
    @NamedQuery(name = "Cotizador.findByNombredelcliente", query = "SELECT c FROM Cotizador c WHERE c.nombredelcliente = :nombredelcliente"),
    @NamedQuery(name = "Cotizador.findByCondici\u00f3ndepago", query = "SELECT c FROM Cotizador c WHERE c.condici\u00f3ndepago = :condici\u00f3ndepago"),
    @NamedQuery(name = "Cotizador.findByFechadeentrega", query = "SELECT c FROM Cotizador c WHERE c.fechadeentrega = :fechadeentrega"),
    @NamedQuery(name = "Cotizador.findByValidesdelaoferta", query = "SELECT c FROM Cotizador c WHERE c.validesdelaoferta = :validesdelaoferta"),
    @NamedQuery(name = "Cotizador.findByTipodeevento", query = "SELECT c FROM Cotizador c WHERE c.tipodeevento = :tipodeevento"),
    @NamedQuery(name = "Cotizador.findByPetici\u00f3n", query = "SELECT c FROM Cotizador c WHERE c.petici\u00f3n = :petici\u00f3n"),
    @NamedQuery(name = "Cotizador.findByIdcotizador", query = "SELECT c FROM Cotizador c WHERE c.idcotizador = :idcotizador")})
public class Cotizador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Nombre del cliente")
    private String nombredelcliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Condici\u00f3n de pago")
    private String condicióndepago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha de entrega")
    @Temporal(TemporalType.DATE)
    private Date fechadeentrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valides de la oferta")
    @Temporal(TemporalType.DATE)
    private Date validesdelaoferta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Tipo de evento")
    private String tipodeevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Petici\u00f3n")
    private String petición;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Idcotizador")
    private Integer idcotizador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcotizador")
    private Collection<Pagos> pagosCollection;

    public Cotizador() {
    }

    public Cotizador(Integer idcotizador) {
        this.idcotizador = idcotizador;
    }

    public Cotizador(Integer idcotizador, String nombredelcliente, String condicióndepago, Date fechadeentrega, Date validesdelaoferta, String tipodeevento, String petición) {
        this.idcotizador = idcotizador;
        this.nombredelcliente = nombredelcliente;
        this.condicióndepago = condicióndepago;
        this.fechadeentrega = fechadeentrega;
        this.validesdelaoferta = validesdelaoferta;
        this.tipodeevento = tipodeevento;
        this.petición = petición;
    }

    public String getNombredelcliente() {
        return nombredelcliente;
    }

    public void setNombredelcliente(String nombredelcliente) {
        this.nombredelcliente = nombredelcliente;
    }

    public String getCondicióndepago() {
        return condicióndepago;
    }

    public void setCondicióndepago(String condicióndepago) {
        this.condicióndepago = condicióndepago;
    }

    public Date getFechadeentrega() {
        return fechadeentrega;
    }

    public void setFechadeentrega(Date fechadeentrega) {
        this.fechadeentrega = fechadeentrega;
    }

    public Date getValidesdelaoferta() {
        return validesdelaoferta;
    }

    public void setValidesdelaoferta(Date validesdelaoferta) {
        this.validesdelaoferta = validesdelaoferta;
    }

    public String getTipodeevento() {
        return tipodeevento;
    }

    public void setTipodeevento(String tipodeevento) {
        this.tipodeevento = tipodeevento;
    }

    public String getPetición() {
        return petición;
    }

    public void setPetición(String petición) {
        this.petición = petición;
    }

    public Integer getIdcotizador() {
        return idcotizador;
    }

    public void setIdcotizador(Integer idcotizador) {
        this.idcotizador = idcotizador;
    }

    public Collection<Pagos> getPagosCollection() {
        return pagosCollection;
    }

    public void setPagosCollection(Collection<Pagos> pagosCollection) {
        this.pagosCollection = pagosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcotizador != null ? idcotizador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizador)) {
            return false;
        }
        Cotizador other = (Cotizador) object;
        if ((this.idcotizador == null && other.idcotizador != null) || (this.idcotizador != null && !this.idcotizador.equals(other.idcotizador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cotizador[ idcotizador=" + idcotizador + " ]";
    }
    
}
