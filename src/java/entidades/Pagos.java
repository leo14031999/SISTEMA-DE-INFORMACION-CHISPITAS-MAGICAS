/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "pagos")
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p"),
    @NamedQuery(name = "Pagos.findByIdpagos", query = "SELECT p FROM Pagos p WHERE p.idpagos = :idpagos"),
    @NamedQuery(name = "Pagos.findByNombrecliente", query = "SELECT p FROM Pagos p WHERE p.nombrecliente = :nombrecliente"),
    @NamedQuery(name = "Pagos.findByTipodocumento", query = "SELECT p FROM Pagos p WHERE p.tipodocumento = :tipodocumento"),
    @NamedQuery(name = "Pagos.findByNumeroidentificacion", query = "SELECT p FROM Pagos p WHERE p.numeroidentificacion = :numeroidentificacion"),
    @NamedQuery(name = "Pagos.findByTipoevento", query = "SELECT p FROM Pagos p WHERE p.tipoevento = :tipoevento"),
    @NamedQuery(name = "Pagos.findByPersonalencargado", query = "SELECT p FROM Pagos p WHERE p.personalencargado = :personalencargado"),
    @NamedQuery(name = "Pagos.findByValortotal", query = "SELECT p FROM Pagos p WHERE p.valortotal = :valortotal")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Idpagos")
    private Integer idpagos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Nombrecliente")
    private int nombrecliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipodocumento")
    private int tipodocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Numeroidentificacion")
    private int numeroidentificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipoevento")
    private int tipoevento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Personalencargado")
    private int personalencargado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valortotal")
    private int valortotal;

    public Pagos() {
    }

    public Pagos(Integer idpagos) {
        this.idpagos = idpagos;
    }

    public Pagos(Integer idpagos, int nombrecliente, int tipodocumento, int numeroidentificacion, int tipoevento, int personalencargado, int valortotal) {
        this.idpagos = idpagos;
        this.nombrecliente = nombrecliente;
        this.tipodocumento = tipodocumento;
        this.numeroidentificacion = numeroidentificacion;
        this.tipoevento = tipoevento;
        this.personalencargado = personalencargado;
        this.valortotal = valortotal;
    }

    public Integer getIdpagos() {
        return idpagos;
    }

    public void setIdpagos(Integer idpagos) {
        this.idpagos = idpagos;
    }

    public int getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(int nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public int getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(int tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public int getNumeroidentificacion() {
        return numeroidentificacion;
    }

    public void setNumeroidentificacion(int numeroidentificacion) {
        this.numeroidentificacion = numeroidentificacion;
    }

    public int getTipoevento() {
        return tipoevento;
    }

    public void setTipoevento(int tipoevento) {
        this.tipoevento = tipoevento;
    }

    public int getPersonalencargado() {
        return personalencargado;
    }

    public void setPersonalencargado(int personalencargado) {
        this.personalencargado = personalencargado;
    }

    public int getValortotal() {
        return valortotal;
    }

    public void setValortotal(int valortotal) {
        this.valortotal = valortotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpagos != null ? idpagos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.idpagos == null && other.idpagos != null) || (this.idpagos != null && !this.idpagos.equals(other.idpagos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pagos[ idpagos=" + idpagos + " ]";
    }
    
}
