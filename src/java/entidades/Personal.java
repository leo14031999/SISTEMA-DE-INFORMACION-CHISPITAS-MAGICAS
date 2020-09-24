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
@Table(name = "personal")
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
    @NamedQuery(name = "Personal.findByIdpersonal", query = "SELECT p FROM Personal p WHERE p.idpersonal = :idpersonal"),
    @NamedQuery(name = "Personal.findByNombres", query = "SELECT p FROM Personal p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Personal.findByApellidos", query = "SELECT p FROM Personal p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Personal.findByDireccion", query = "SELECT p FROM Personal p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Personal.findByCelular1", query = "SELECT p FROM Personal p WHERE p.celular1 = :celular1"),
    @NamedQuery(name = "Personal.findByCelular2", query = "SELECT p FROM Personal p WHERE p.celular2 = :celular2"),
    @NamedQuery(name = "Personal.findByCorreoelectronico", query = "SELECT p FROM Personal p WHERE p.correoelectronico = :correoelectronico"),
    @NamedQuery(name = "Personal.findByTipoidentificacion", query = "SELECT p FROM Personal p WHERE p.tipoidentificacion = :tipoidentificacion"),
    @NamedQuery(name = "Personal.findByNumeroidentificacion", query = "SELECT p FROM Personal p WHERE p.numeroidentificacion = :numeroidentificacion"),
    @NamedQuery(name = "Personal.findByCargo", query = "SELECT p FROM Personal p WHERE p.cargo = :cargo"),
    @NamedQuery(name = "Personal.findByGenero", query = "SELECT p FROM Personal p WHERE p.genero = :genero"),
    @NamedQuery(name = "Personal.findByFechanacimiento", query = "SELECT p FROM Personal p WHERE p.fechanacimiento = :fechanacimiento")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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
    @Column(name = "Direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Celular1")
    private String celular1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Celular2")
    private String celular2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Correoelectronico")
    private String correoelectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Tipoidentificacion")
    private String tipoidentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Numeroidentificacion")
    private String numeroidentificacion;
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
    @Column(name = "Fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;

    public Personal() {
    }

    public Personal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public Personal(Integer idpersonal, String nombres, String apellidos, String direccion, String celular1, String celular2, String correoelectronico, String tipoidentificacion, String numeroidentificacion, String cargo, String genero, Date fechanacimiento) {
        this.idpersonal = idpersonal;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.celular1 = celular1;
        this.celular2 = celular2;
        this.correoelectronico = correoelectronico;
        this.tipoidentificacion = tipoidentificacion;
        this.numeroidentificacion = numeroidentificacion;
        this.cargo = cargo;
        this.genero = genero;
        this.fechanacimiento = fechanacimiento;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getTipoidentificacion() {
        return tipoidentificacion;
    }

    public void setTipoidentificacion(String tipoidentificacion) {
        this.tipoidentificacion = tipoidentificacion;
    }

    public String getNumeroidentificacion() {
        return numeroidentificacion;
    }

    public void setNumeroidentificacion(String numeroidentificacion) {
        this.numeroidentificacion = numeroidentificacion;
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

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
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
