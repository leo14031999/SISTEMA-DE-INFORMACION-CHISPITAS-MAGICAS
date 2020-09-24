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
@Table(name = "personal")
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
    @NamedQuery(name = "Personal.findByIdpersonal", query = "SELECT p FROM Personal p WHERE p.idpersonal = :idpersonal"),
    @NamedQuery(name = "Personal.findByNombres", query = "SELECT p FROM Personal p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Personal.findByApellidos", query = "SELECT p FROM Personal p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Personal.findByDirecci\u00f3n", query = "SELECT p FROM Personal p WHERE p.direcci\u00f3n = :direcci\u00f3n"),
    @NamedQuery(name = "Personal.findByCelular1", query = "SELECT p FROM Personal p WHERE p.celular1 = :celular1"),
    @NamedQuery(name = "Personal.findByCelular2", query = "SELECT p FROM Personal p WHERE p.celular2 = :celular2"),
    @NamedQuery(name = "Personal.findByCorreoElectronico", query = "SELECT p FROM Personal p WHERE p.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "Personal.findByTipodeIdentificaci\u00f3n", query = "SELECT p FROM Personal p WHERE p.tipodeIdentificaci\u00f3n = :tipodeIdentificaci\u00f3n"),
    @NamedQuery(name = "Personal.findByN\u00famerodeIdentificaci\u00f3n", query = "SELECT p FROM Personal p WHERE p.n\u00famerodeIdentificaci\u00f3n = :n\u00famerodeIdentificaci\u00f3n"),
    @NamedQuery(name = "Personal.findByCargo", query = "SELECT p FROM Personal p WHERE p.cargo = :cargo"),
    @NamedQuery(name = "Personal.findByGenero", query = "SELECT p FROM Personal p WHERE p.genero = :genero"),
    @NamedQuery(name = "Personal.findByFechadeNacimiento", query = "SELECT p FROM Personal p WHERE p.fechadeNacimiento = :fechadeNacimiento")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Idpersonal")
    private Integer idpersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Direcci\u00f3n")
    private String dirección;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Celular 1")
    private String celular1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Celular 2")
    private String celular2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Correo Electronico")
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Tipo de Identificaci\u00f3n")
    private String tipodeIdentificación;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "N\u00famero de Identificaci\u00f3n")
    private String númerodeIdentificación;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Cargo")
    private String cargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Genero")
    private String genero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha de Nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechadeNacimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpersonal")
    private Collection<Pagos> pagosCollection;

    public Personal() {
    }

    public Personal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public Personal(Integer idpersonal, String nombres, String apellidos, String dirección, String celular1, String celular2, String correoElectronico, String tipodeIdentificación, String númerodeIdentificación, String cargo, String genero, Date fechadeNacimiento) {
        this.idpersonal = idpersonal;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dirección = dirección;
        this.celular1 = celular1;
        this.celular2 = celular2;
        this.correoElectronico = correoElectronico;
        this.tipodeIdentificación = tipodeIdentificación;
        this.númerodeIdentificación = númerodeIdentificación;
        this.cargo = cargo;
        this.genero = genero;
        this.fechadeNacimiento = fechadeNacimiento;
    }

    public Integer getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTipodeIdentificación() {
        return tipodeIdentificación;
    }

    public void setTipodeIdentificación(String tipodeIdentificación) {
        this.tipodeIdentificación = tipodeIdentificación;
    }

    public String getNúmerodeIdentificación() {
        return númerodeIdentificación;
    }

    public void setNúmerodeIdentificación(String númerodeIdentificación) {
        this.númerodeIdentificación = númerodeIdentificación;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechadeNacimiento() {
        return fechadeNacimiento;
    }

    public void setFechadeNacimiento(Date fechadeNacimiento) {
        this.fechadeNacimiento = fechadeNacimiento;
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
        hash += (idpersonal != null ? idpersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.idpersonal == null && other.idpersonal != null) || (this.idpersonal != null && !this.idpersonal.equals(other.idpersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Personal[ idpersonal=" + idpersonal + " ]";
    }
    
}
