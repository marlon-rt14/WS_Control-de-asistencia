/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mjavi
 */
@Entity
@Table(name = "tipos")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Tipo.findAll", query = "SELECT t FROM Tipo t"),
	@NamedQuery(name = "Tipo.findByIdTipo", query = "SELECT t FROM Tipo t WHERE t.idTipo = :idTipo"),
	@NamedQuery(name = "Tipo.findByDescripcionTipo", query = "SELECT t FROM Tipo t WHERE t.descripcionTipo = :descripcionTipo")})
public class Tipo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_tipo")
	private Integer idTipo;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 50)
        @Column(name = "descripcion_tipo")
	private String descripcionTipo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipo")
	private List<FechaHabil> fechaHabilList;
	@OneToMany(mappedBy = "idTipo")
	private List<TipoEmpleado> tipoEmpleadoList;

	public Tipo() {
	}

	public Tipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public Tipo(Integer idTipo, String descripcionTipo) {
		this.idTipo = idTipo;
		this.descripcionTipo = descripcionTipo;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcionTipo() {
		return descripcionTipo;
	}

	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}

	@XmlTransient
	public List<FechaHabil> getFechaHabilList() {
		return fechaHabilList;
	}

	public void setFechaHabilList(List<FechaHabil> fechaHabilList) {
		this.fechaHabilList = fechaHabilList;
	}

	@XmlTransient
	public List<TipoEmpleado> getTipoEmpleadoList() {
		return tipoEmpleadoList;
	}

	public void setTipoEmpleadoList(List<TipoEmpleado> tipoEmpleadoList) {
		this.tipoEmpleadoList = tipoEmpleadoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTipo != null ? idTipo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Tipo)) {
			return false;
		}
		Tipo other = (Tipo) object;
		if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.Tipo[ idTipo=" + idTipo + " ]";
	}
	
}
