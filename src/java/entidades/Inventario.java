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
import javax.validation.constraints.Size;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "inventario")
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i"),
    @NamedQuery(name = "Inventario.findByIdinventario", query = "SELECT i FROM Inventario i WHERE i.idinventario = :idinventario"),
    @NamedQuery(name = "Inventario.findByNombre", query = "SELECT i FROM Inventario i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Inventario.findByMarca", query = "SELECT i FROM Inventario i WHERE i.marca = :marca"),
    @NamedQuery(name = "Inventario.findByAccesorios", query = "SELECT i FROM Inventario i WHERE i.accesorios = :accesorios"),
    @NamedQuery(name = "Inventario.findByProveedor", query = "SELECT i FROM Inventario i WHERE i.proveedor = :proveedor"),
    @NamedQuery(name = "Inventario.findByTelefono", query = "SELECT i FROM Inventario i WHERE i.telefono = :telefono"),
    @NamedQuery(name = "Inventario.findByUbicaci\u00f3n", query = "SELECT i FROM Inventario i WHERE i.ubicaci\u00f3n = :ubicaci\u00f3n"),
    @NamedQuery(name = "Inventario.findByCategoria", query = "SELECT i FROM Inventario i WHERE i.categoria = :categoria"),
    @NamedQuery(name = "Inventario.findByCantidad", query = "SELECT i FROM Inventario i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "Inventario.findByEstado", query = "SELECT i FROM Inventario i WHERE i.estado = :estado")})
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Idinventario")
    private Integer idinventario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Accesorios")
    private String accesorios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Proveedor")
    private String proveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Ubicaci\u00f3n")
    private String ubicación;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Categoria")
    private String categoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private boolean estado;

    public Inventario() {
    }

    public Inventario(Integer idinventario) {
        this.idinventario = idinventario;
    }

    public Inventario(Integer idinventario, String nombre, String marca, String accesorios, String proveedor, String telefono, String ubicación, String categoria, int cantidad, boolean estado) {
        this.idinventario = idinventario;
        this.nombre = nombre;
        this.marca = marca;
        this.accesorios = accesorios;
        this.proveedor = proveedor;
        this.telefono = telefono;
        this.ubicación = ubicación;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public Integer getIdinventario() {
        return idinventario;
    }

    public void setIdinventario(Integer idinventario) {
        this.idinventario = idinventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUbicación() {
        return ubicación;
    }

    public void setUbicación(String ubicación) {
        this.ubicación = ubicación;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinventario != null ? idinventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idinventario == null && other.idinventario != null) || (this.idinventario != null && !this.idinventario.equals(other.idinventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Inventario[ idinventario=" + idinventario + " ]";
    }
    
}
