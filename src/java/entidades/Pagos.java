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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NamedQuery(name = "Pagos.findByTipodeDocumento", query = "SELECT p FROM Pagos p WHERE p.tipodeDocumento = :tipodeDocumento"),
    @NamedQuery(name = "Pagos.findByN\u00famerodeIdentificaci\u00f3n", query = "SELECT p FROM Pagos p WHERE p.n\u00famerodeIdentificaci\u00f3n = :n\u00famerodeIdentificaci\u00f3n"),
    @NamedQuery(name = "Pagos.findByTipodeevento", query = "SELECT p FROM Pagos p WHERE p.tipodeevento = :tipodeevento"),
    @NamedQuery(name = "Pagos.findByPersonalEncargado", query = "SELECT p FROM Pagos p WHERE p.personalEncargado = :personalEncargado"),
    @NamedQuery(name = "Pagos.findByValorTotal", query = "SELECT p FROM Pagos p WHERE p.valorTotal = :valorTotal")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Idpagos")
    private Integer idpagos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Nombre_cliente")
    private String nombrecliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Tipo de Documento")
    private String tipodeDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "N\u00famero de Identificaci\u00f3n")
    private String númerodeIdentificación;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Tipo de evento")
    private String tipodeevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Personal Encargado")
    private String personalEncargado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor Total")
    private int valorTotal;
    @JoinColumn(name = "Idcliente", referencedColumnName = "Idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "Idcotizador", referencedColumnName = "Idcotizador")
    @ManyToOne(optional = false)
    private Cotizador idcotizador;
    @JoinColumn(name = "Idevento", referencedColumnName = "Idevento")
    @ManyToOne(optional = false)
    private Evento idevento;
    @JoinColumn(name = "Idpersonal", referencedColumnName = "Idpersonal")
    @ManyToOne(optional = false)
    private Personal idpersonal;

    public Pagos() {
    }

    public Pagos(Integer idpagos) {
        this.idpagos = idpagos;
    }

    public Pagos(Integer idpagos, String nombrecliente, String tipodeDocumento, String númerodeIdentificación, String tipodeevento, String personalEncargado, int valorTotal) {
        this.idpagos = idpagos;
        this.nombrecliente = nombrecliente;
        this.tipodeDocumento = tipodeDocumento;
        this.númerodeIdentificación = númerodeIdentificación;
        this.tipodeevento = tipodeevento;
        this.personalEncargado = personalEncargado;
        this.valorTotal = valorTotal;
    }

    public Integer getIdpagos() {
        return idpagos;
    }

    public void setIdpagos(Integer idpagos) {
        this.idpagos = idpagos;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getTipodeDocumento() {
        return tipodeDocumento;
    }

    public void setTipodeDocumento(String tipodeDocumento) {
        this.tipodeDocumento = tipodeDocumento;
    }

    public String getNúmerodeIdentificación() {
        return númerodeIdentificación;
    }

    public void setNúmerodeIdentificación(String númerodeIdentificación) {
        this.númerodeIdentificación = númerodeIdentificación;
    }

    public String getTipodeevento() {
        return tipodeevento;
    }

    public void setTipodeevento(String tipodeevento) {
        this.tipodeevento = tipodeevento;
    }

    public String getPersonalEncargado() {
        return personalEncargado;
    }

    public void setPersonalEncargado(String personalEncargado) {
        this.personalEncargado = personalEncargado;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Cotizador getIdcotizador() {
        return idcotizador;
    }

    public void setIdcotizador(Cotizador idcotizador) {
        this.idcotizador = idcotizador;
    }

    public Evento getIdevento() {
        return idevento;
    }

    public void setIdevento(Evento idevento) {
        this.idevento = idevento;
    }

    public Personal getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(Personal idpersonal) {
        this.idpersonal = idpersonal;
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
