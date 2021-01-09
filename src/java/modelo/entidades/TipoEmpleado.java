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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mjavi
 */
@Entity
@Table(name = "tipo_empleado")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TipoEmpleado.findAll", query = "SELECT t FROM TipoEmpleado t"),
	@NamedQuery(name = "TipoEmpleado.findByIdTipoEmpleado", query = "SELECT t FROM TipoEmpleado t WHERE t.idTipoEmpleado = :idTipoEmpleado")})
public class TipoEmpleado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_tipo_empleado")
	private Integer idTipoEmpleado;
	@JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
        @ManyToOne
	private Empleado idEmpleado;
	@JoinColumn(name = "id_jornada", referencedColumnName = "id_jornada")
        @ManyToOne
	private Jornada idJornada;
	@JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
        @ManyToOne
	private Tipo idTipo;
	@OneToMany(mappedBy = "idTipoEmpleado")
	private List<Asistencia> asistenciaList;

	public TipoEmpleado() {
	}

	public TipoEmpleado(Integer idTipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
	}

	public Integer getIdTipoEmpleado() {
		return idTipoEmpleado;
	}

	public void setIdTipoEmpleado(Integer idTipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
	}

	public Empleado getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Empleado idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Jornada getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(Jornada idJornada) {
		this.idJornada = idJornada;
	}

	public Tipo getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Tipo idTipo) {
		this.idTipo = idTipo;
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
		hash += (idTipoEmpleado != null ? idTipoEmpleado.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TipoEmpleado)) {
			return false;
		}
		TipoEmpleado other = (TipoEmpleado) object;
		if ((this.idTipoEmpleado == null && other.idTipoEmpleado != null) || (this.idTipoEmpleado != null && !this.idTipoEmpleado.equals(other.idTipoEmpleado))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.TipoEmpleado[ idTipoEmpleado=" + idTipoEmpleado + " ]";
	}
	
}
