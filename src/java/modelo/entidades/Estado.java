/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "estados")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
	@NamedQuery(name = "Estado.findByIdEstado", query = "SELECT e FROM Estado e WHERE e.idEstado = :idEstado"),
	@NamedQuery(name = "Estado.findByDescripcionEstado", query = "SELECT e FROM Estado e WHERE e.descripcionEstado = :descripcionEstado")})
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_estado")
	private Integer idEstado;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 50)
        @Column(name = "descripcion_estado")
	private String descripcionEstado;
	@OneToMany(mappedBy = "idEstado")
	private List<Asistencia> asistenciaList;

	public Estado() {
	}

	public Estado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Estado(Integer idEstado, String descripcionEstado) {
		this.idEstado = idEstado;
		this.descripcionEstado = descripcionEstado;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	@XmlTransient
	public List<Asistencia> getAsistenciaList() {
		return asistenciaList;
	}

	public void setAsistenciaList(List<Asistencia> asistenciaList) {
		this.asistenciaList = asistenciaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEstado != null ? idEstado.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Estado)) {
			return false;
		}
		Estado other = (Estado) object;
		if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.Estado[ idEstado=" + idEstado + " ]";
	}
	
}
