/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Cotizador.findByIdcotizador", query = "SELECT c FROM Cotizador c WHERE c.idcotizador = :idcotizador"),
    @NamedQuery(name = "Cotizador.findByNombrecliente", query = "SELECT c FROM Cotizador c WHERE c.nombrecliente = :nombrecliente"),
    @NamedQuery(name = "Cotizador.findByCondicionpago", query = "SELECT c FROM Cotizador c WHERE c.condicionpago = :condicionpago"),
    @NamedQuery(name = "Cotizador.findByFechaentrega", query = "SELECT c FROM Cotizador c WHERE c.fechaentrega = :fechaentrega"),
    @NamedQuery(name = "Cotizador.findByValidesoferta", query = "SELECT c FROM Cotizador c WHERE c.validesoferta = :validesoferta"),
    @NamedQuery(name = "Cotizador.findByTipoevento", query = "SELECT c FROM Cotizador c WHERE c.tipoevento = :tipoevento"),
    @NamedQuery(name = "Cotizador.findByPeticion", query = "SELECT c FROM Cotizador c WHERE c.peticion = :peticion")})
public class Cotizador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Idcotizador")
    private Integer idcotizador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "Nombrecliente")
    private String nombrecliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "condicionpago")
    private int condicionpago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fechaentrega")
    @Temporal(TemporalType.DATE)
    private Date fechaentrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Validesoferta")
    private int validesoferta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipoevento")
    private int tipoevento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Peticion")
    private int peticion;

    public Cotizador() {
    }

    public Cotizador(Integer idcotizador) {
        this.idcotizador = idcotizador;
    }

    public Cotizador(Integer idcotizador, String nombrecliente, int condicionpago, Date fechaentrega, int validesoferta, int tipoevento, int peticion) {
        this.idcotizador = idcotizador;
        this.nombrecliente = nombrecliente;
        this.condicionpago = condicionpago;
        this.fechaentrega = fechaentrega;
        this.validesoferta = validesoferta;
        this.tipoevento = tipoevento;
        this.peticion = peticion;
    }

    public Integer getIdcotizador() {
        return idcotizador;
    }

    public void setIdcotizador(Integer idcotizador) {
        this.idcotizador = idcotizador;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public int getCondicionpago() {
        return condicionpago;
    }

    public void setCondicionpago(int condicionpago) {
        this.condicionpago = condicionpago;
    }

    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public int getValidesoferta() {
        return validesoferta;
    }

    public void setValidesoferta(int validesoferta) {
        this.validesoferta = validesoferta;
    }

    public int getTipoevento() {
        return tipoevento;
    }

    public void setTipoevento(int tipoevento) {
        this.tipoevento = tipoevento;
    }

    public int getPeticion() {
        return peticion;
    }

    public void setPeticion(int peticion) {
        this.peticion = peticion;
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
